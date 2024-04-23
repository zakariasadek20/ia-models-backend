package com.models.ai.domain.appClients.aimodels;

import com.models.ai.domain.appClients.aimodels.aiimage.AiImage;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageId;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageProvider;
import com.models.ai.domain.appClients.aimodels.commands.AiModelAddCommand;
import com.models.ai.domain.appClients.aimodels.exceptions.AiModelAlreadyExistException;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AiModelRunnerService {

    public static final String AI_IMAGES_BUCKETS = "ai-images";

    private final AIModelProvider aiModelProvider;
    private final AiImageProvider aiImageProvider;
    private final MinioClient minioClient;

    public ByteArrayOutputStream RunAiModel(AiModelId aiModelId, String options, MultipartFile file) {
        log.info("Begin RunAiModel Service ");
        AiModel checkAiModel = aiModelProvider.getBy(aiModelId).orElseThrow(AiModelAlreadyExistException::new);

        InputStream inputStream = null;

        try {
            String originalFilename = file.getOriginalFilename()
                    .replaceAll(" ", "_")
                    .replaceAll("\\(", "_")
                    .replaceAll("\\)", "_");

            String fileId = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);

            fileId = fileId.replaceAll(" ", "_");

            AiImage aiImage = AiImage.builder()
                    .id(new AiImageId())
                    .originalFilename(originalFilename)
                    .aiModelId(checkAiModel.getId())
                    .appClientId(checkAiModel.getAppClientId())
                    .fileId(fileId)
                    .build();

            aiImage = aiImageProvider.save(aiImage);

            String directoryInputFolder = checkAiModel.getDirectoryInputFolder();

            // Get the file name

            File destFile = copyFileToInputFolder(file, directoryInputFolder, originalFilename);

            String command = prepareComandeLine(checkAiModel, options, destFile);

            // Execute the command in the terminal
            executeCommandLine(command);

            File imageFile = getImageFile(checkAiModel, originalFilename);

            Map<String, String> metaData = Map.of(
                    "ai_model_id",
                    aiImage.getAiModelId().getValue(),
                    "app_client_Id",
                    aiImage.getAppClientId().getValue());

            // Create a FileInputStream for the image file and upload
            uploadImageToMinio(imageFile, metaData, fileId);

            // Retrieve the uploaded image file from MinIO bucket
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(AI_IMAGES_BUCKETS)
                    .object(fileId)
                    .build();

            try (InputStream obj = minioClient.getObject(getObjectArgs)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = obj.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                log.info("End RunAiModel Service ");

                return byteArrayOutputStream;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void uploadImageToMinio(File imageFile, Map<String, String> metaData, String fileId)
            throws IOException, ErrorResponseException, InsufficientDataException, InternalException,
                    InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException,
                    XmlParserException {
        try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
            // Upload the image file to MinIO bucket
            PutObjectArgs putObjectArgs =
                    PutObjectArgs.builder().bucket(AI_IMAGES_BUCKETS).userMetadata(metaData).object(fileId).stream(
                                    fileInputStream, imageFile.length(), -1)
                            .build();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        }
    }

    @NotNull
    private static File getImageFile(AiModel checkAiModel, String fileName) throws FileNotFoundException {
        String fullOutputFolder = checkAiModel.getFullOutputFolder();

        File imageFile = new File(fullOutputFolder + fileName.replaceFirst("\\.\\w+$", ".png"));

        // Check if the file exists
        if (!imageFile.exists()) {
            throw new FileNotFoundException("Image file not found" + imageFile);
        }
        return imageFile;
    }

    private static void executeCommandLine(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[] {"bash", "-c", command});

        // Read output from the process
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String stdLine;
        while ((stdLine = stdInput.readLine()) != null) {
            System.out.println(stdLine);
        }

        // Read error output from the process
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errLine;
        while ((errLine = stdError.readLine()) != null) {
            System.err.println(errLine);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        System.out.println("Process exited with code " + exitCode);
    }

    @NotNull
    private static String prepareComandeLine(AiModel checkAiModel, String options, File destFile) {
        // Command to execute the Python script using the specific Python interpreter
        String python_folder = checkAiModel.getPython_folder();
        String aiFolder = checkAiModel.getAiFolder();
        String aiName = checkAiModel.getName();
        //        String options = " -w 0.7";
        String imageLink = " -i " + destFile.getAbsolutePath();
        //            String output = "/Users/zakariasadek/CodeFormer/result";
        String output = checkAiModel.getDirectoryOutputFolder();

        String command =
                python_folder + " " + aiFolder + "" + aiName + " " + options + " " + imageLink + " -o " + output;
        //            String command = "/Users/zakariasadek/miniconda3/bin/python3
        // /Users/zakariasadek/CodeFormer/inference_codeformer.py -w 0.7 -i
        // /Users/zakariasadek/CodeFormer/inputs/cropped_faces/image.jpeg -o /Users/zakariasadek/CodeFormer/reslt";

        return command;
    }

    private static void checkDirectoryInputfolder(String directoryInputFolder) {
        File dir = new File(directoryInputFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @NotNull
    private static File copyFileToInputFolder(MultipartFile file, String directoryInputFolder, String fileName)
            throws IOException {
        checkDirectoryInputfolder(directoryInputFolder);

        // Create the file object with the specified directory and file name
        File destFile = new File(directoryInputFolder + fileName);

        // Save the file to the specified location
        file.transferTo(destFile);
        return destFile;
    }

    private void verifyAiModelDoesNotExist(AiModelAddCommand command) {

        Optional<AiModel> checkAiModel = aiModelProvider.getByName(command.getName());
        if (checkAiModel.isPresent()) {
            throw new AiModelAlreadyExistException();
        }
        if (!Objects.isNull(command.getCommandLine())) {
            checkAiModel = aiModelProvider.getByCommandLine(command.getCommandLine());
            if (checkAiModel.isPresent()) {
                throw new AiModelAlreadyExistException();
            }
        }
    }
}
