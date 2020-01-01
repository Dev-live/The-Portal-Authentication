create table if not exists oauth_client_details (
    client_id varchar(255) not null,
    client_secret varchar(255) not null,
    web_server_redirect_uri varchar(2048) default null,
    scope varchar(255) default null,
    access_token_validity int default null,
    refresh_token_validity int default null,
    resource_ids varchar(1024) default null,
    authorized_grant_types varchar(1024) default null,
    authorities varchar(1024) default null,
    additional_information varchar(4096) default null,
    autoapprove varchar(255) default null,
     primary key (client_id) );


-- token store
create table if not exists oauth_client_token (
    token_id varchar(256),
    token BYTEA,
    authentication_id varchar(256) primary KEY,
    user_name varchar(256),
    client_id varchar(256) );


create table if not exists oauth_access_token (
    token_id varchar(256),
    token BYTEA,
    authentication_id varchar(256) primary KEY,
    user_name varchar(256),
    client_id varchar(256),
    authentication BYTEA,
    refresh_token varchar(256) );


create table if not exists oauth_refresh_token (
    token_id varchar(256),
    token BYTEA,
    authentication BYTEA );


create table if not exists oauth_code (
    code varchar(256),
    authentication BYTEA );


create table if not exists oauth_approvals (
    userId varchar(256),
    clientId varchar(256),
    scope varchar(256),
    status varchar(10),
    expiresAt BYTEA,
    lastModifiedAt BYTEA );


create table if not exists oauth_refresh_token (
    id INT primary KEY,
    name VARCHAR(20) );