--V999_999_0100__init_users
-- Profiles
insert into profile(id, niveau, code, description) values ('9000', 999, 'ADMIN', 'Profil Administrateur');
insert into profile(id, niveau, code, description) values ('1000', 100, 'INVESTISSEUR', 'Investisseur');
insert into profile(id, niveau, code, description) values ('2000', 200, 'BACK_OFFICE', 'Back Office');
insert into profile(id, niveau, code, description) values ('3000', 300, 'EQUIPE_SCORING', 'Prestataire');
insert into profile(id, niveau, code, description) values ('4000', 400, 'CRUI', 'CRUI');


-- Users
insert into users(id, login, password, status, type, current_profile)
    values ('9000', 'email@gmail.com', '$2a$10$y85wt3XXtB442wCcNSQjReQbMOb97IOldUUgka0OI1dbf9GSaFIxO', 'VALID', 'AGENT', '9000');
--insert into users_info(user_id, first_name, last_name, email, indicatif_pays, telephone)
--    values ('9000', 'First Name', 'Last Name', 'email@gmail.com', '+212', '0999999999');

insert into users(id, login, password, status, type, current_profile)
    values ('1000', 'inv@gmail.com', '$2a$10$y85wt3XXtB442wCcNSQjReQbMOb97IOldUUgka0OI1dbf9GSaFIxO', 'VALID', 'INVESTISSEUR', '1000');
--insert into users_info(user_id, first_name, last_name, email, indicatif_pays, telephone)
--    values ('1000', 'First Name', 'Last Name', 'inv@gmail.com', '+212', '0999999999');

insert into users(id, login, password, status, type, current_profile)
    values ('2000', 'bo@gmail.com', '$2a$10$y85wt3XXtB442wCcNSQjReQbMOb97IOldUUgka0OI1dbf9GSaFIxO', 'VALID', 'AGENT', '2000');
--insert into users_info(user_id, first_name, last_name, email, indicatif_pays, telephone)
--    values ('2000', 'First Name', 'Last Name', 'bo@gmail.com', '+212', '0999999999');

insert into users(id, login, password, status, type, current_profile)
    values ('3000', 'prestataire@gmail.com', '$2a$10$y85wt3XXtB442wCcNSQjReQbMOb97IOldUUgka0OI1dbf9GSaFIxO', 'VALID', 'AGENT', '3000');
--insert into users_info(user_id, first_name, last_name, email, indicatif_pays, telephone)
--    values ('3000', 'Prestataire', 'Prestataire', 'prestataire@gmail.com', '+212', '0999999999');

insert into users(id, login, password, status, type, current_profile)
    values ('4000', 'crui@gmail.com', '$2a$10$y85wt3XXtB442wCcNSQjReQbMOb97IOldUUgka0OI1dbf9GSaFIxO', 'VALID', 'AGENT', '4000');
--insert into users_info(user_id, first_name, last_name, email, indicatif_pays, telephone)
--    values ('4000', 'CRUI', 'CRUI', 'crui@gmail.com', '+212', '0999999999');

-----------------------------------------------
--V999_999_0110__init_user_profiles


-- Users Profiles
insert into users_profiles (user_id, profile_id) values ('9000', '9000');
insert into users_profiles (user_id, profile_id) values ('1000', '1000');
insert into users_profiles (user_id, profile_id) values ('2000', '2000');
insert into users_profiles (user_id, profile_id) values ('3000', '3000');
insert into users_profiles (user_id, profile_id) values ('4000', '4000');




-----------------------------------------------------
--V999_999_002000__init_menu

-- Dashboard

insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
    values ('00100', 'toolbox', 'dashboard', 'Dashboard', 'menu.dashboard', null, null, 1, 1, '001000');

-- Demandes Subventions
insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
    values ('00200', 'toolbox', 'mes_demandes_subventions', 'Mes demandes de subvention', 'menu.mes_demandes_subventions', null, null, 1, 2, '002000');

    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00210', 'dmd-sub-creer', 'je_cree_ma_demande', 'Je crée ma demande', 'menu.je_cree_ma_demande', '/app/demande-subvention', '00200', 2, 1, '002100');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00220', 'dmd-sub-consulter', 'je_consulte_mes_demandes', 'Je consulte mes demandes', 'menu.je_consulte_mes_demandes', '/app/demandes-subventions/consultation', '00200', 2, 2, '002200');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00230', 'dmd-sub-completer', 'je_complete_mes_demandes', 'Je compléte mes demandes', 'menu.je_complete_mes_demandes', '/app/demandes-subventions/completion', '00200', 2, 3, '002300');

-- Back Office/ Scoring /
insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
    values ('00300', 'toolbox', 'demandes_subventions', 'Demandes de subvention', 'menu.demandes_subventions', null, null, 1, 3, '002000');

    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00310', 'dmd-sub-consulter', 'consuler_les_demandes', 'Consulter les demandes', 'menu.dmd.demandes.consulter', '/app/demandes-subventions/consultation', '00300', 2, 1, '008100');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00320', 'dmd-sub-eligibilite', 'examiner_eligibilite', 'Examiner l''éligibilité', 'menu.dmd.examiner_eligibilite', '/app/demandes-subventions/eligibilite', '00300', 2, 2, '003200');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00330', 'dmd-sub-rejets', 'evaluer_les_rejets', 'Evaluer les rejets', 'menu.dmd.evaluer_les_rejets', '/app/demandes-subventions/evaluation_rejets', '00300', 2, 3, '003300');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00340', 'dmd-sub-scoring', 'scorning', 'Scoring', 'menu.dmd.scoring', '/app/demande-subvention/scoring', '00300', 2, 4, '003400');


-- Admin Referentiel
insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
    values ('00800', 'toolbox', 'referentiel', 'Referentiel', 'menu.referentiel', null, null, 1, 8, '008000');

    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00810', 'user-lock', 'regions', 'Regions', 'menu.adminnistration.regions', '/app/agent/referentiel/regions', '00800', 2, 1, '008100');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00820', 'book-open', 'provinces', 'Provinces', 'menu.adminnistration.provinces', '/app/agent/referentiel/provinces', '00800', 2, 2, '008200');
    insert into menu(id, icon, code, description, i18n_code, link, parent, level_, order_, global_order)
        values ('00830', 'user-lock', 'villes', 'Villes', 'menu.adminnistration.localite', '/app/agent/referentiel/localite', '00800', 2, 3, '008300');



--MENU_ACTION

-- Dashboard
insert into menu_action(id, menu_id, code, resource, method, status) values ('001001', '00100', 'VIEW', '/dashboard', 'GET', 'ENABLED');

----- Investisseur
insert into menu_action(id, menu_id, code, resource, method, status) values ('002101', '00210', 'VIEW', '/initiate', 'POST', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('002201', '00220', 'VIEW', '/search', 'POST', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('002301', '00230', 'VIEW', '/complete', 'POST', 'ENABLED');

-- Back Office/ Scoring /
insert into menu_action(id, menu_id, code, resource, method, status) values ('003101', '00310', 'VIEW', '/consulter', 'POST', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('003201', '00320', 'VIEW', '/examiner', 'POST', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('003301', '00330', 'VIEW', '/evaluer_rejets', 'POST', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('003401', '00340', 'VIEW', '/scoring', 'POST', 'ENABLED');

-----ADMIN Referentiel
insert into menu_action(id, menu_id, code, resource, method, status) values ('008101', '00810', 'VIEW', '/regions', 'GET', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('008201', '00820', 'VIEW', '/provinces', 'GET', 'ENABLED');
insert into menu_action(id, menu_id, code, resource, method, status) values ('008301', '00830', 'VIEW', '/localite', 'GET', 'ENABLED');

-----------------------------------------------------
--V999_999_002500__init_profile_menu
--Investisseur
insert into profile_menu_action(profile_id, menu_action_id) values ('1000', '001001');
insert into profile_menu_action(profile_id, menu_action_id) values ('1000', '002101');
insert into profile_menu_action(profile_id, menu_action_id) values ('1000', '002201');
insert into profile_menu_action(profile_id, menu_action_id) values ('1000', '002301');

insert into profile_menu_action(profile_id, menu_action_id) values ('2000', '001001');
insert into profile_menu_action(profile_id, menu_action_id) values ('2000', '003101');
insert into profile_menu_action(profile_id, menu_action_id) values ('2000', '003201');
insert into profile_menu_action(profile_id, menu_action_id) values ('2000', '003301');

insert into profile_menu_action(profile_id, menu_action_id) values ('3000', '001001');
insert into profile_menu_action(profile_id, menu_action_id) values ('3000', '003101');
insert into profile_menu_action(profile_id, menu_action_id) values ('3000', '003401');

insert into profile_menu_action(profile_id, menu_action_id) values ('4000', '001001');
insert into profile_menu_action(profile_id, menu_action_id) values ('4000', '003101');
insert into profile_menu_action(profile_id, menu_action_id) values ('4000', '003401');

-- Admin
insert into profile_menu_action(profile_id, menu_action_id) values ('9000', '008101');
insert into profile_menu_action(profile_id, menu_action_id) values ('9000', '008201');
--insert into profile_menu_action(profile_id, menu_action_id) values ('9000', '00831');


insert into app_clients(id, name,host,enabled,store) values ('APP_CLIENT_1', 'First Application','host 1',true,'Google Play');

insert into ai_models(id, name,command_line,python_folder,ai_folder,directory_input_folder,directory_output_folder,full_output_folder,app_client_id) values ('inference_codeformer', 'inference_codeformer.py','python3 inference_codeformer.py -w 0.7 -i inputs/cropped_faces/image.jpeg -o reslt','/Users/zakariasadek/miniconda3/bin/python3','/Users/zakariasadek/CodeFormer/','/Users/zakariasadek/CodeFormer/inputs/','/Users/zakariasadek/CodeFormer/result/','/Users/zakariasadek/CodeFormer/result/final_results/','APP_CLIENT_1');
