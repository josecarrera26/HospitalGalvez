create database bendicion;

CREATE SCHEMA hospital;

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

--creacion de secuencia medicamento
--drop sequence bendicion.hospital.medicamento_seq
CREATE SEQUENCE bendicion.hospital.medicamento_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;


--creacion de tabla medicamento
--drop table medicamento;
--select * from medicamento;
create table medicamento(
	id_medicamento INT NOT NULL DEFAULT nextval('bendicion.hospital.medicamento_seq')
	constraint pk_medicamento primary key,
	codigo_medicamento varchar(250),
	nombre_medicamento varchar(250),
	tipo_medida varchar(30),
	estado varchar(10),
	tipo_medicamento varchar(5)
);



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
	fecha TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

--insert into receta (id_cita) values (1)

--creacion de secuencia detalle_receta
--drop sequence bendicion.hospital.detalle_receta_seq
CREATE SEQUENCE bendicion.hospital.detalle_receta_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla detalle_receta
--drop table detalle_receta;
--select * from detalle_receta;
create table detalle_receta(
	id_detalle_receta int4 not null default nextval('bendicion.hospital.detalle_receta_seq') 
		constraint pk_detalle_receta primary key,
	id_receta integer,
	id_medicamento integer
);

--insert into detalle_receta (id_receta, id_medicamento) values (1,2)

-- Creación de secuencia para id_role
CREATE SEQUENCE bendicion.hospital.role_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 999999;

-- Creación de tabla role
CREATE TABLE role (
    id_role integer NOT NULL DEFAULT nextval('bendicion.hospital.role_seq'),
    nombre VARCHAR(100),
    descripcion VARCHAR(250),
    accesos JSON, -- Definición del campo accesos como tipo JSON
    PRIMARY KEY (id_role)
);
--INSERT INTO hospital."role" (nombre, descripcion, accesos) VALUES ('administrador', 'acceso a todo', '{"permiso": "valor_permiso"}');

-- Creación de secuencia para id_usuario
CREATE SEQUENCE bendicion.hospital.usuario_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 999999;

-- Creación de tabla usuario
CREATE TABLE usuario (
    id_usuario integer NOT NULL DEFAULT nextval('bendicion.hospital.usuario_seq'),
    username VARCHAR(255) UNIQUE,
    role_id integer, -- El ID del rol de usuario, que será una clave foránea
    password VARCHAR(255),
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (role_id) REFERENCES role(id_role) -- Definición de la clave foránea
);
--INSERT INTO hospital.usuario (username, role_id, "password") VALUES('adm', 1, '1234');

--select * from "role" r; 
--select * from usuario; 

--creacion de secuencia paciente
--drop sequence bendicion.hospital.paciente_seq
CREATE SEQUENCE bendicion.hospital.paciente_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla paciente
--drop table paciente cascade;
--select * from paciente;
create table paciente(
	id_paciente int4 not null default nextval('bendicion.hospital.paciente_seq') 
		constraint pk_paciente primary key,
	nombre varchar(250),
	apellido varchar(250),
	fecha_nacimiento date,
	direccion varchar(250),
	telefono varchar,
	dpi varchar,
	nit varchar(25),
	email varchar(100),
    genero varchar(25),
	id_usuario int4 REFERENCES hospital.usuario(id_usuario) ON DELETE SET NULL,
	estado varchar(1)
);

--insert into paciente (nombre, apellido, fecha_nacimiento, direccion, telefono, dpi, nit, email, genero, id_usuario, estado) values ('Marco', 'Lopez', now(), 'calle zona 1', 55555555, 01010110101, 12345678, 'mario@lopez.com', 'masculino', 1, 1)


--creacion de secuencia medico
--drop sequence bendicion.hospital.medico_seq
CREATE SEQUENCE bendicion.hospital.medico_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;

--creacion de tabla medico
--drop table medico;
--select * from medico;
create table medico(
	id_medico INT NOT NULL DEFAULT nextval('bendicion.hospital.medico_seq')
	constraint pk_medico primary key,
	nombre_medico varchar(250),
	apellido_medico varchar(250),
	id_especialidad Integer,
	id_usuario Integer,
	telefono varchar(20),
	jornada varchar,
	cod_jefe_inmediato integer
	colegiado varchar(30),
	fechaCreacion date,
	direccion varchar (100),
	centro_hospitalario varchar(50),
	edad int,
	Observaciones varchar,
);

--insert into medico (nombre_medico, apellido_medico, id_especialidad, id_usuario, telefono, jornada, cod_jefe_inmediato) values ('Thomas', 'Miller', 1, 1, 12345678, a, 1)


--creacion de secuencia especialidad
--drop sequence bendicion.hospital.paciente_seq
CREATE SEQUENCE bendicion.hospital.especialidad_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;


--creacion de tabla especialidad
--drop table especialidad;
--select * from especialiad;
create table especialidad(
	id_especialidad INT NOT NULL DEFAULT nextval('bendicion.hospital.especialidad_seq')
	constraint pk_especialidad primary key,
	nombre_especialidad varchar(250),
	descripcion varchar(250),
	estado varchar(10)
);

--insert into especialidad (nombre_especialidad, descripcion, estado) values ('Cardiologo', 'descripcion', Disponible)



-- creacion tabla factura --
--drop sequence bendicion.hospital.factura_seq
CREATE SEQUENCE bendicion.hospital.factura_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 999999;

create table factura(
	id_factura INT not null default nextval('bendicion.hospital.factura_seq') 
		constraint pk_factura primary key,
	fecha_factura timestamp,
	id_usuario integer,
	id_cita integer,
	nit varchar(25)
);

--creacion de secuencia detalle_factura
--drop sequence bendicion.hospital.detalle_factura_seq
CREATE SEQUENCE bendicion.hospital.detalle_factura_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    maxvalue 999999;
   
--creacion de tabla detalle_receta
--drop table detalle_receta;
--select * from detalle_receta;
create table detalle_factura(
	id_detalle_factura INT not null default nextval('bendicion.hospital.detalle_factura_seq') 
		constraint pk_detalle_factura primary key,
	id_factura integer,
	id_medicamento integer,
	cantidad numeric(10,2),
	tarifa numeric(10,2),
	impuesto numeric(10,2),
	descuento numeric(10,2),
	subtotal numeric(10,2),
	iva numeric(10,2),
	total numeric(10,2)
);
--creacion de secuencia historial
--drop sequence bendicion.hospital.historial_seq
CREATE SEQUENCE bendicion.hospital.historial_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 0
	maxvalue 999999;
	
--creacion de tabla historial
--drop table historial;
--select * from historial;
CREATE TABLE "historial" (
  id_historial int not null default nextval('bendicion.hospital.historial_seq')
  constraint pk_historial primary key,
  id_paciente integer,
  fecha TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
  diagnostico varchar,
  id_factura integer
);
--creacion de secuencia detalle_historial
--CREATE SEQUENCE bendicion.hospital.detalle_historial_seq
--	START WITH 1
--	INCREMENT BY 1
--	MINVALUE 0
--	maxvalue 999999;
--
--creacion de tabla detalle_historial
--drop table detalle_historial;
--select * from detalle_historial;
--CREATE TABLE "detalle_historial" (
--  id_detalle_historial int not null default nextval('bendicion.hospital.detalle_historial_seq'), 
---  constraint pk_detalle_historial--creacion de tabla detalle_historial
--  item varchar,
--  id_item integer,
--  descripcion text,
--  id_historial integer
--);


--FK
ALTER TABLE cita
ADD CONSTRAINT fk_cita_paciente
FOREIGN KEY (id_paciente)
REFERENCES paciente(id_paciente);

ALTER TABLE cita
ADD CONSTRAINT fk_cita_usuario
FOREIGN KEY (id_usuario) 
REFERENCES usuario(id_usuario);

ALTER TABLE detalle_receta
ADD CONSTRAINT fk_detalle_receta_receta 
FOREIGN KEY (id_receta) 
REFERENCES receta(id_receta);

ALTER TABLE receta
ADD CONSTRAINT fk_receta_cita 
FOREIGN KEY (id_cita) 
REFERENCES cita(id_cita);

ALTER TABLE paciente
ADD CONSTRAINT fk_paciente_usuario 
FOREIGN KEY (id_usuario) 
REFERENCES usuario(id_usuario);

ALTER TABLE factura
ADD CONSTRAINT fk_factura_usuario
FOREIGN KEY (id_usuario) 
REFERENCES usuario(id_usuario);

ALTER TABLE factura
ADD CONSTRAINT fk_factura_cita 
FOREIGN KEY (id_cita) 
REFERENCES cita(id_cita);

ALTER TABLE detalle_factura
ADD CONSTRAINT fk_detalle_factura_encabezado
FOREIGN KEY (id_factura) 
REFERENCES factura(id_factura);

ALTER TABLE detalle_factura
ADD CONSTRAINT fk_detalle_factura_medicamento
FOREIGN KEY (id_medicamento) 
REFERENCES medicamento(id_medicamento);

ALTER TABLE historial
ADD CONSTRAINT fk_historial_paciente
FOREIGN KEY (id_paciente) 
REFERENCES paciente(id_paciente);

ALTER TABLE historial
ADD CONSTRAINT fk_historial_factura
FOREIGN KEY (id_factura) 
REFERENCES factura(id_factura);

--ALTER TABLE "medico" ADD FOREIGN KEY ("cod_jefe_inmediato") REFERENCES "medico" ("id_medico");
ALTER TABLE "medico" ADD FOREIGN KEY ("id_especialidad") REFERENCES "especialidad" ("id_especialidad");