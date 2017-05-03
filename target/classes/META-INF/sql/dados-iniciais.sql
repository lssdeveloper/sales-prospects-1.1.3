insert into ramo_atividade (id, descricao) values (1, 'Distribuição de alimentos');
insert into ramo_atividade (id, descricao) values (2, 'Telecomunicações');
insert into ramo_atividade (id, descricao) values (3, 'Vestuário');
insert into ramo_atividade (id, descricao) values (4, 'Lavanderia');
insert into ramo_atividade (id, descricao) values (5, 'Gráfica');
insert into ramo_atividade (id, descricao) values (6, 'Mecânica');
insert into ramo_atividade (id, descricao) values (7, 'Turismo');
insert into ramo_atividade (id, descricao) values (8, 'Saúde');
insert into ramo_atividade (id, descricao) values (9, 'Educação');
insert into ramo_atividade (id, descricao) values (10, 'Recrutamento e Seleção');
insert into ramo_atividade (id, descricao) values (11, 'Lazer');

INSERT INTO tipo_prospecto(descricao) VALUES ('LIGAÇÃO REALIZADA');
INSERT INTO tipo_prospecto(descricao) VALUES ('E-MAIL ENVIADO');
INSERT INTO tipo_prospecto(descricao) VALUES ('MARKETING DIGITAL');
INSERT INTO tipo_prospecto(descricao) VALUES ('VISITAÇÃO IN LOCO');

INSERT INTO usuario (id, email, nome, senha) VALUES (1, 'le@adm', 'Leandro Serra', '123');
INSERT INTO usuario (id, email, nome, senha) VALUES (2, 'yago@col', 'yago', '1Q2W3E4R');
INSERT INTO usuario (id, email, nome, senha) VALUES (1, 'mi@adm', 'Micheline', '1q2w3e4r');
INSERT INTO usuario (id, email, nome, senha) VALUES (2, 'wagnergo@adm', 'Wagner', '1Q2W3E4R');

INSERT INTO grupo (id, descricao, nome) VALUES (1, 'Administradores do sistema', 'Administrador');
INSERT INTO grupo (id, descricao, nome) VALUES (2, 'Colaboradores do sistema', 'Colaborador');

INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1,1);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (2,2);









