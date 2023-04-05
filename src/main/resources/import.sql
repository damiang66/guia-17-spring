INSERT INTO noticias (titulo, cuerpo,fecha) VALUES('noticia 1', 'noticia 1 relacionada con algoo que no me importa',  '2018-01-04');
INSERT INTO noticias (titulo, cuerpo,fecha) VALUES('noticia 2', 'noticia 2 relacionada con algoo que no me importa',  '2018-01-03');
INSERT INTO noticias (titulo, cuerpo,fecha) VALUES('noticia 3', 'noticia 3 relacionada con algoo que no me importa', '2018-02-01');

INSERT INTO `usuarios` (username,password,enabled) VALUES('damian','$2a$10$h8vYBo7eRN7OLIvDs2NNyepH8HtIYdlUjalc7kGOl1dJ6ifJ2y4GC',1);
INSERT INTO `usuarios` (username,password,enabled) VALUES('admin','$2a$10$EDZs8ITemE9iXCZ7O15AS.RIzrmEg9TAmDL/OJY7iE8vEwrKMMzXa',1);
INSERT INTO `roles` (nombre) VALUES('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES('ROLE_ADMIN');
INSERT INTO `usuarios_roles` (usuario_id,roles_id) VALUES(1,1);
INSERT INTO `usuarios_roles` (usuario_id,roles_id) VALUES(2,2);
INSERT INTO `usuarios_roles` (usuario_id,roles_id) VALUES(2,1);