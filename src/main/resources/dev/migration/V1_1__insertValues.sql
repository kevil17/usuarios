INSERT INTO users (id, user_name, password, email, creation_date) VALUES
                                                                (1000, 'niquit', '12345', 'niqui@gamil.com', '2023-11-17'),
                                                                (1001, 'alis', '12345', 'alis@gamil.com', '2022-11-15'),
                                                                (1002, 'carlitos', '12345', 'carl@gamil.com', '2021-11-13'),
                                                                (1003, 'miguel', '12345', 'migueli@gamil.com', '2015-11-14'),
                                                                (1004, 'kevinick', '12345', 'kevil@gamil.com', '2014-11-14');
INSERT INTO user_detail (id, first_name, last_name, age, birth_day,user_id) VALUES
                                                              (1000, 'Nicol', 'Torrez', 22, '1995-11-19',1000),
                                                              (1001, 'Alison', 'Gutierrez', 21, '1995-1-11',1001),
                                                              (1002, 'Carlos', 'Loma', 28, '1995-10-13',1002),
                                                              (1003, 'Miguel', 'Tordoya', 26, '1995-2-14',1003),
                                                              (1004, 'Carol', 'Mencia', 26, '1995-6-19',1004);

INSERT INTO rol (id, name) VALUES
                                  (1000,'Administrador' ),
                                  (1001, 'Cliente'),
                                  (1002, 'Usuario'),
                                  (1003, 'Colaborador');
INSERT INTO user_rol (id, active, created_at, user_id, rol_id) VALUES
                                  (1000,true,now(),1000,1000 ),
                                  (1001,true,now(),1001,1001 ),
                                  (1002,true,now(),1002,1002 ),
                                  (1003,true,now(),1003,1003 );