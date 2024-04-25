create database bendicion;

CREATE SCHEMA hospital;

--creacion de secuencia citas
--drop sequence bendicion.hospital.examen_seq
CREATE SEQUENCE bendicion.hospital.examen_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla citas
--drop table examen;
--select * from examen;
create table examen (
	id_examen int4 not null default nextval('bendicion.hospital.examen_seq') 
		constraint pk_examen primary key,
	descripcion varchar(250),
	precio numeric(10,2),
	observaciones varchar(250)
);

--insert into examen (descripcion, precio, observaciones) values ('prueba', 1234.20, 'observaciones')

--creacion de secuencia operacion
--drop sequence bendicion.hospital.operacion_seq
CREATE SEQUENCE bendicion.hospital.operacion_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla operacion
--drop table operacion;
--select * from operacion;
create table operacion (
	id_operacion int4 not null default nextval('bendicion.hospital.operacion_seq') 
		constraint pk_operacion primary key,
	descripcion varchar(250)
);

--insert into operacion (descripcion) values ('descripcion operacion')


--creacion de secuencia cita
--drop sequence bendicion.hospital.cita_seq
CREATE SEQUENCE bendicion.hospital.cita_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla cita
--drop table cita;
--select * from cita;
create table cita(
	id_cita int4 not null default nextval('bendicion.hospital.cita_seq') 
		constraint pk_cita primary key,
	id_paciente integer,
	fecha_cita timestamp,
	descripcion varchar(250),
	id_usuario integer,
	id_medico integer,
	estado varchar(1)
);

--insert into cita (id_paciente, fecha_cita, descripcion, id_usuario, id_medico, estado) values (1, now(),'descripcion', 1,1,'A')


--creacion de secuencia receta
--drop sequence bendicion.hospital.receta_seq
CREATE SEQUENCE bendicion.hospital.receta_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla receta
--drop table receta;
--select * from receta;
create table receta(
	id_receta int4 not null default nextval('bendicion.hospital.receta_seq') 
		constraint pk_receta primary key,
	id_cita integer,
	id_detalle_receta integer
);

--insert into receta (id_cita, id_detalle_receta) values (1,1)
-- Creación de secuencia para id_role
CREATE SEQUENCE bendicion.hospital.role_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 999999;

-- Creación de tabla role
CREATE TABLE role (
    id_role INT NOT NULL DEFAULT nextval('bendicion.hospital.role_seq'),
    nombre VARCHAR(100),
    descripcion VARCHAR(250),
    accesos JSON, -- Definición del campo accesos como tipo JSON
    PRIMARY KEY (id_role)
);
--INSERT INTO public."role" (nombre, descripcion, accesos) VALUES ('administrador', 'acceso a todo', '{"permiso": "valor_permiso"}');

-- Creación de secuencia para id_usuario
CREATE SEQUENCE bendicion.hospital.usuario_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 999999;

-- Creación de tabla usuario
CREATE TABLE usuario (
    id_usuario INT NOT NULL DEFAULT nextval('bendicion.hospital.usuario_seq'),
    username VARCHAR(255),
    role_id INT, -- El ID del rol de usuario, que será una clave foránea
    password VARCHAR(255),
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (role_id) REFERENCES role(id_role) -- Definición de la clave foránea
);
--INSERT INTO public.usuario (username, role_id, "password") VALUES('adm', 1, '1234');

--select * from "role" r 
--select * from usuario u 
