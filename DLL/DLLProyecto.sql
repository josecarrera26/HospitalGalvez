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
