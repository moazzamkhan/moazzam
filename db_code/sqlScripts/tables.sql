create database if not exists pesqol;

use pesqol;

create table user (
	id varchar(50) not null,
	age varchar(200) not null,
	sensitive_to_air_pollution boolean,
	primary key(id)
);

create table observation(
	id varchar(50) not null,
	air_pollution_id varchar(50) not null,
	
);

create table air_pollution_sensor_data(
	id varchar(50) not null,
	nitrogen_oxide double,
	ozone double,
	sulphur_dioxide double, 
	particulate_matter_10 double,
	particulate_matter_2_point_5 double,
	
	primary key(id)
);

--	Citizen Age Group
--	Health(Sensitive to Air pollution)

---
--Observation
--	Measurements(NO2, O3, SO2, PM10, PM2.5 etc)
--	id
--	AQ index
--	Citizen Classification(poor, adeqaute, ..)
--	text comments
--	pictures
--	timestamp
--	location stamp
--	

drop table image;
create table image (
   id varchar(50) ,
   name varchar(200),
   imagePath varchar(200),
   uploadedOn timestamp,
   primary key(id)   
);