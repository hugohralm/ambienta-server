-- noinspection SqlNoDataSourceInspectionForFile
INSERT INTO USUARIO (ID, DATA_CADASTRO, CPF, SENHA, NOME, EMAIL, ATIVADO) VALUES (1, now(), '11111111111', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin@admin.com', true);

INSERT INTO PAPEL (NOME) VALUES ('ADMIN');
INSERT INTO PAPEL (NOME) VALUES ('USUARIO');

INSERT INTO USUARIO_PAPEL (USUARIO_ID, PAPEL_NOME) VALUES (1, 'USUARIO');
INSERT INTO USUARIO_PAPEL (USUARIO_ID, PAPEL_NOME) VALUES (1, 'ADMIN');
