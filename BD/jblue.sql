create database jblue;

create table consumidores(
    id serial primary key,
    nombre varchar(30),
    ap varchar(30),
    am varchar(30),
    titular int,
    fregistro varchar(15),
    activo int
);
