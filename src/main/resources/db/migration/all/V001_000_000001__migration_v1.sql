

---------------------------------------------------

create table entity_label
(
    id              varchar(36) not null,
    entity_type     varchar(100) NOT NULL,
    entity_id       varchar(100) NOT NULL,
    language        varchar(50) not null,
    label          varchar(800)
);

alter table entity_label
    add CONSTRAINT pk_entity_label_id PRIMARY KEY (id);

ALTER TABLE IF EXISTS entity_label ADD CONSTRAINT uk_entity_label_entity_id_type_language UNIQUE (entity_type, entity_id, language);

CREATE INDEX IF NOT EXISTS ix_entity_label_entity_type ON entity_label (entity_type);


-- Users
create table users
(
  id                    varchar(36) not null,
  login                 varchar(50) not null,
  password              varchar(500) not null,
  password_expiry_date  TIMESTAMP,
  status                varchar(20) not null,
  last_access            TIMESTAMP,
  type                  varchar(36) not null,
  current_profile       varchar(36),
  mre                     varchar(5),
  first_name              varchar(255),
  last_name               varchar(255),
  first_name_ar           varchar(500),
  last_name_ar            varchar(500),
  email                   varchar(255),
  nationalite             varchar(100),
  indicatif_pays          varchar(10),
  telephone               varchar(50),
  piece_identite          varchar(50),
  adresse_residence       varchar(500),
  pays_residence          varchar(100),
  region_residence          varchar(100),
  province_residence      varchar(36),
  commune_residence       varchar(36),
  created_by            varchar(36),
  created_at            TIMESTAMP,
  updated_by            varchar(36),
  updated_at            TIMESTAMP
);

alter table users add CONSTRAINT pk_Users PRIMARY KEY (id);
--index  users_login
CREATE INDEX IF NOT EXISTS ix_users_login ON users (login);

-- Users_Info

--create table users_info
--(
--    user_id                 varchar(36) not null,
--    first_name              varchar(255),
--    last_name               varchar(255),
--    first_name_ar           varchar(500),
--    last_name_ar            varchar(500),
--    email                   varchar(255),
--    indicatif_pays          varchar(10),
--    telephone               varchar(50),
--    nationalite             varchar(36),
--    piece_identite          varchar(50),
--    mre                     varchar(5),
--    adresse_residence       varchar(500),
--    created_by              varchar(36),
--    created_at              TIMESTAMP,
--    updated_by              varchar(36),
--    updated_at              TIMESTAMP
--);

--alter table users_info add CONSTRAINT pk_Users_info PRIMARY KEY (user_id);
--
--ALTER TABLE users_info
--    ADD CONSTRAINT fk_users_info_user
--        FOREIGN KEY (user_id) REFERENCES users (id);


-- Profile

create table profile
(
    id              varchar(36) not null,
    niveau          integer not null,
    code            varchar (50) not null,
    description     varchar (50) not null
);

alter table profile add CONSTRAINT pk_profile PRIMARY KEY (id);

-- User_Profile
create table users_profiles (
    user_id         varchar(36) not null ,
    profile_id      varchar(36) not null,
    created_by     varchar(36),
    created_at     TIMESTAMP,
    updated_by     varchar(36),
    updated_at     TIMESTAMP
) ;

alter table users_profiles add CONSTRAINT pk_users_profiles PRIMARY KEY (user_id, profile_id);

ALTER TABLE users_profiles
    ADD CONSTRAINT fk_users_profiles_profile
        FOREIGN KEY (profile_id) REFERENCES profile (id);

ALTER TABLE users_profiles
    ADD CONSTRAINT fk_users_profiles_user
        FOREIGN KEY (user_id) REFERENCES users (id);



-------------------------------------------

-- Menu
create table menu
(
    id           varchar(36) not null,
    code         varchar(50) not null,
    description  varchar(50) not null,
    i18n_code    varchar(50) not null,
    icon         varchar(50),
    visible     boolean,
    link         varchar(50),
    parent       varchar(36),
    level_       integer,
    order_       integer,
    global_order varchar(36),
    created_by   varchar(36),
    created_at   TIMESTAMP,
    updated_by   varchar(36),
    updated_at   TIMESTAMP
);

alter table menu
    add CONSTRAINT pk_menu PRIMARY KEY (id);

-- Menu_Action

create table menu_action
(
    id         varchar(36)  not null,
    menu_id    varchar(36)  not null,
    code       varchar(50)  not null,
    resource   varchar(250) not null,
    method     varchar(10)  not null,
    status     varchar(50)  not null,
    created_by varchar(36),
    created_at TIMESTAMP,
    updated_by varchar(36),
    updated_at TIMESTAMP
);

alter table menu_action
    add CONSTRAINT pk_menu_action PRIMARY KEY (id);

ALTER TABLE menu_action
    ADD CONSTRAINT fk_menu_action_menu
        FOREIGN KEY (menu_id) REFERENCES menu(id);

-- profile_menu

create table profile_menu_action
(
    profile_id     varchar(36) not null,
    menu_action_id varchar(36) not null
);

alter table profile_menu_action
    add CONSTRAINT pk_profile_menu_action PRIMARY KEY (profile_id, menu_action_id);

ALTER TABLE profile_menu_action
    ADD CONSTRAINT fk_profile_menu_action_profile
        FOREIGN KEY (profile_id) REFERENCES profile (id);
ALTER TABLE profile_menu_action
    ADD CONSTRAINT fk_profile_menu_action_menu_act
        FOREIGN KEY (menu_action_id) REFERENCES menu_action (id);
-------------------------------------------

-- AppClient

create table app_clients
(
    id         varchar(36)  not null,
    name     varchar(250) not null,
    host     varchar(250) not null,
    enabled     boolean,
    store     varchar(250) not null,
    last_access            TIMESTAMP,
    created_by varchar(36),
    created_at TIMESTAMP,
    updated_by varchar(36),
    updated_at TIMESTAMP
);

alter table app_clients
    add CONSTRAINT pk_app_clients PRIMARY KEY (id);
-------


-- AiModel

create table ai_models
(
    id         varchar(36)  not null,
    name     varchar(250) not null,
    command_line     varchar(250) not null,
    ai_folder     varchar(250) not null,
    python_folder     varchar(250) not null,
    directory_input_folder  varchar(500) not null,
    directory_output_folder  varchar(500) not null,
    full_output_folder  varchar(500) not null,
    last_access            TIMESTAMP,
    app_client_id         varchar(36)  not null,
    created_by varchar(36),
    created_at TIMESTAMP,
    updated_by varchar(36),
    updated_at TIMESTAMP
);

alter table ai_models
    add CONSTRAINT pk_ai_models PRIMARY KEY (id);

ALTER TABLE ai_models
    ADD CONSTRAINT fk_app_clients_ai_models
        FOREIGN KEY (app_client_id) REFERENCES app_clients (id);

---

-- AiModel

create table ai_image
(
    id         varchar(36)  not null,
    original_filename     varchar(500) not null,
    file_id     varchar(500) not null,
    app_client_id         varchar(36)  not null,
    ai_model_id         varchar(36)  not null,
    created_by varchar(36),
    created_at TIMESTAMP,
    updated_by varchar(36),
    updated_at TIMESTAMP
);

alter table ai_image
    add CONSTRAINT pk_ai_image PRIMARY KEY (id);

ALTER TABLE ai_image
    ADD CONSTRAINT fk_app_clients_ai_image
        FOREIGN KEY (app_client_id) REFERENCES app_clients (id);

ALTER TABLE ai_image
    ADD CONSTRAINT fk_ai_model
        FOREIGN KEY (ai_model_id) REFERENCES ai_models (id);