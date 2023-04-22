INSERT INTO regiones(nombre) VALUES('Sudamérica');
INSERT INTO regiones(nombre) VALUES('Centroamérica');
INSERT INTO regiones(nombre) VALUES('Norteamérica');
INSERT INTO regiones(nombre) VALUES('Europa');
INSERT INTO regiones(nombre) VALUES('Asia');
INSERT INTO regiones(nombre) VALUES('África');
INSERT INTO regiones(nombre) VALUES('Oceanía');
INSERT INTO regiones(nombre) VALUES('Antártida');

INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Blanca', 'Martinez', 'blanca@mail.com', '2018-06-12', 1);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Cata', 'Perejil', 'cata@mail.com', '2018-06-12', 2);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Juana', 'De Arco', 'juana@mail.com', '2018-06-12', 3);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Tomás', 'De Aquino', 'tomas@mail.com', '2018-06-12', 4);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Aristóteles', 'De Atenas', 'aristoteles@mail.com', '2018-06-12', 5);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Leucipo', 'Matacarrillo', 'leucipo@mail.com', '2018-06-12', 6);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Demócrito', 'De Alejandría', 'democrito@mail.com', '2018-06-12', 7);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Marco', 'Aurelio', 'marco@mail.com', '2018-06-12', 8);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Félix', 'Cuevas', 'felix@mail.com', '2018-06-12', 7);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Juan', 'De Arimatea', 'juan.arimatea@mail.com', '2018-06-12', 6);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Maria', 'San Juan', 'maria@mail.com', '2018-06-12', 5);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('John', 'Doe', 'john@mail.com', '2018-06-12', 4);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Hugo', 'Sanchez', 'hugo@mail.com', '2018-06-12', 3);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Jorge', 'Campos', 'jorge@mail.com', '2018-06-12', 2);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Jared', 'Borgetti', 'jared@mail.com', '2018-06-12', 1);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Francisco', 'Fonseca', 'francisco@mail.com', '2018-06-12', 6);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Juan', 'Pérez', 'juan@mail.com', '2018-06-12', 7);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Ilena', 'Hiridia', 'ileana@mail.com', '2018-06-12', 6);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Platón', 'De Atenas', 'platon@mail.com', '2018-06-12', 5);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Gregorio', 'Magno', 'gregorio@mail.com', '2018-06-12', 4);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Ines', 'De La Cruz', 'ines@mail.com', '2018-06-12', 3);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Javier', 'Hernández', 'javier@mail.com', '2018-06-12', 2);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Carlos', 'Vela', 'carlos@mail.com', '2018-06-12', 1);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Miguel', 'Hidalgo', 'miguel@mail.com', '2018-06-12', 2);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Pancho', 'Villa', 'pancho@mail.com', '2018-06-12', 3);
INSERT INTO clientes(nombre, apellido, email, create_at, region_id) VALUES('Gustavo', 'A. Madero', 'gustavo@mail.com', '2018-06-12', 4);

INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$/YwHIqzSqa4A1BFgNZ.rJuzXiYzDcKaNzBZd99sTIK7jYGjXGNqtq', 1, 'John', 'Doe', 'admin@mail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('adrian', '$2a$10$7ikmbPxw4/DVOVmY64609OBhn2MYqVieGauNYnD5Di7oRyplb864q', 1, 'Adrian', 'Gonzalez', 'adrian@mail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('usuario', '$2a$10$5E0qt1GYGC3.0UnPOrZkBuFYb9Jih.C1hm6wNwbln10Tl4Tdolsv2', 1, 'Juan', 'Perez', 'juan@mail.com');

INSERT INTO roles(nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles(nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (3, 2);