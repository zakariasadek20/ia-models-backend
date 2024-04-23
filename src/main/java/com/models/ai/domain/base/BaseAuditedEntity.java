package com.models.ai.domain.base;

import com.models.ai.domain.users.UserId;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseAuditedEntity<T extends Identity> extends BaseEntity<T> {

    @CreatedBy
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "created_by"))})
    private UserId createdBy;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "updated_by"))})
    private UserId updatedBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Optional<UserId> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable(createdAt);
    }

    public Optional<UserId> getUpdatedBy() {
        return Optional.ofNullable(updatedBy);
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }
}
