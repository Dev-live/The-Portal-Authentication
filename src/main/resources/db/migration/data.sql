INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES
('dashboard', '$2b$10$JzyUiHShQvFmqQvYT1A1Pe8Eij0NHlDolzgIql8v/Y4xbm4C.5Niy', 'http://localhost:8080/code', 'READ,WRITE,TRUST',
 '3600', '10000', 'portal.admin', 'authorization_code,password,refresh_token,implicit', '{}');
INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES
('android', '$2b$10$JzyUiHShQvFmqQvYT1A1Pe8Eij0NHlDolzgIql8v/Y4xbm4C.5Niy', 'http://localhost:8080/code', 'READ,WRITE,TRUST',
 '3600', '10000', 'portal.android', 'authorization_code,password,refresh_token,implicit', '{}');
INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES
('flutter', '$2b$10$JzyUiHShQvFmqQvYT1A1Pe8Eij0NHlDolzgIql8v/Y4xbm4C.5Niy', 'http://localhost:8080/code', 'READ,WRITE,TRUST',
 '3600', '10000', 'portal.flutter', 'authorization_code,password,refresh_token,implicit', '{}');
 INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES
('ios', '$2b$10$JzyUiHShQvFmqQvYT1A1Pe8Eij0NHlDolzgIql8v/Y4xbm4C.5Niy', 'http://localhost:8080/code', 'READ,WRITE,TRUST',
 '3600', '10000', 'portal.ios', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO authorities (id,name,description)
VALUES(1,'ROLE_USER','Trusted User');
INSERT INTO authorities (id,name,description)
VALUES( 2,'ROLE_ADMIN','App Admin');
INSERT INTO authorities (id,name,description)
VALUES(3,'ROLE_SUPER_ADMIN','Tenant Admin');