create database if not exists pesqol;

use pesqol;

create table student (
	id varchar(50) not null,
	name varchar(200) not null,
	age integer,
	school varchar(200),
	primary key(id)
);
