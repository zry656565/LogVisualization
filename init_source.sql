create database logvisualization character set utf8;

use logvisualization;

create table tb_source (
	id int auto_increment,
	name varchar(255),
	url varchar(255),
	states varchar(255),
	username varchar(255),
	primary key(id)
);