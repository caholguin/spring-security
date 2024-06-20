-- CREACIÓN DE MODULOS
INSERT INTO spring_security.module (base_path,name) VALUES
                                                        ('/products','PRODUCT'),
                                                        ('/categories','CATEGORY'),
                                                        ('/customers','CUSTOMER'),
                                                        ('/auth','AUTH'),
                                                        ('/roles','ROLE'),
                                                        ('/operations','OPERATION');

-- CREACIÓN DE OPERACIONES
INSERT INTO spring_security.operation (http_method,name,`path`,permit_all,module_id) VALUES
	 ('GET','READ_ALL_PRODUCTS','',0,1),
	 ('GET','READ_ONE_PRODUCT','/[0-9]*',0,1),
	 ('POST','CREATE_ONE_PRODUCT','',0,1),
	 ('PUT','UPDATE_ONE_PRODUCT','/[0-9]*',0,1),
	 ('PUT','DISABLE_ONE_PRODUCT','/[0-9]*/disabled',0,1),
	 ('GET','READ_ALL_CATEGORIES','',0,2),
	 ('GET','READ_ONE_CATEGORY','/[0-9]*',0,2),
	 ('POST','CREATE_ONE_CATEGORY','',0,2),
	 ('PUT','UPDATE_ONE_CATEGORY','/[0-9]*',0,2),
	 ('PUT','DISABLE_ONE_CATEGORY','/[0-9]*/disabled',0,2);
INSERT INTO spring_security.operation (http_method,name,`path`,permit_all,module_id) VALUES
                                                                                         ('GET','READ_ALL_CUSTOMERS','',0,3),
                                                                                         ('POST','REGISTER_ONE','',1,3),
                                                                                         ('POST','AUTHENTICATE','/authenticate',1,4),
                                                                                         ('GET','VALIDATE-TOKEN','/validate-token',1,4),
                                                                                         ('GET','READ_MY_PROFILE','/profile',0,4),
                                                                                         ('GET','READ_ALL_ROLES','',1,5),
                                                                                         ('GET','READ_ONE_ROLE','/[0-9]*',1,5),
                                                                                         ('POST','CREATED_ROLE','',1,5),
                                                                                         ('PUT','UPDATE_ROLE','/[0-9]*',1,5),
                                                                                         ('GET','GET_ALL_OPERATION','',1,6);
INSERT INTO spring_security.operation (http_method,name,`path`,permit_all,module_id) VALUES
                                                                                         ('DELETE','DELETE_ROLE','/[0-9]*',1,5),
                                                                                         ('POST','LOGOUT','/logout',1,4),
                                                                                         ('POST','REFRESH_TOKEN','/refreshToken',1,4);


-- CREACIÓN DE ROLES
INSERT INTO spring_security.`role` (name) VALUES
                                              ('CUSTOMER'),
                                              ('ASSISTANT_ADMINISTRATOR'),
                                              ('ADMINISTRATOR');


-- CREACIÓN DE PERMISOS
INSERT INTO spring_security.granted_permission (operation_id,role_id) VALUES
                                                                          (2,2),
                                                                          (4,2),
                                                                          (6,2),
                                                                          (7,2),
                                                                          (9,2),
                                                                          (15,2),
                                                                          (1,3),
                                                                          (2,3),
                                                                          (3,3),
                                                                          (4,3);
INSERT INTO spring_security.granted_permission (operation_id,role_id) VALUES
                                                                          (5,3),
                                                                          (6,3),
                                                                          (7,3),
                                                                          (8,3),
                                                                          (9,3),
                                                                          (10,3),
                                                                          (15,3),
                                                                          (1,2),
                                                                          (2,2);


-- CREACIÓN DE USUARIOS
INSERT INTO spring_security.`user` (name,password,username,role_id) VALUES
                                                                        ('luis márquez','$2a$10$ywh1O2EwghHmFIMGeHgsx.9lMw5IXpg4jafeFS.Oi6nFv0181gHli','lmarquez',1),
                                                                        ('fulano pérez','$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.','fperez',2),
                                                                        ('mengano hernández','$2a$10$TMbMuEZ8utU5iq8MOoxpmOc6QWQuYuwgx1xJF8lSMNkKP3hIrwYFG','mhernandez',3),
                                                                        ('pepe juan','$2a$10$MgUGx/7cMsGwrLjJPx8HIe0bXhjqUxCRhqhuKGXknrFCUnkUkRVwu','pepe01',1);


-- CREACIÓN DE CATEGORIAS
INSERT INTO spring_security.category (name,status) VALUES
                                                       ('Electrónica','ENABLED'),
                                                       ('Ropa','ENABLED'),
                                                       ('Deportes','ENABLED'),
                                                       ('Hogar','ENABLED');


-- CREACIÓN DE PRODUCTOS
INSERT INTO spring_security.product (name,price,status,category_id) VALUES
                                                                        ('Smartphone',500.00,'ENABLED',1),
                                                                        ('Auriculares Bluetooth',50.00,'DISABLED',1),
                                                                        ('Tablet',300.00,'ENABLED',1),
                                                                        ('Camiseta',25.00,'ENABLED',2),
                                                                        ('Pantalones',35.00,'ENABLED',2),
                                                                        ('Zapatos',45.00,'ENABLED',2),
                                                                        ('Balón de Fútbol',20.00,'ENABLED',3),
                                                                        ('Raqueta de Tenis',80.00,'DISABLED',3),
                                                                        ('Aspiradora',120.00,'ENABLED',4),
                                                                        ('Licuadora',50.00,'ENABLED',4);

