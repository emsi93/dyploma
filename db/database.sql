create database carriage_of_goods;
use carriage_of_goods;
create table types_of_trailers(
id int primary key auto_increment,
type_of_trailer varchar(50) not null
);
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('kurtynowa');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('kontenerowa');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('ch³odnicza');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('typu MEGA');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('ponadgabarytowa');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('nispokopodwoziowa z najazdami');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('kurtynowa');
INSERT INTO types_of_trailers(type_of_trailer) VALUES ('cysterna');
create table types_of_goods(
id int primary key auto_increment,
type_of_good varchar(50) not null
);
INSERT INTO types_of_goods(type_of_good) VALUES('¿ywnoœæ');
INSERT INTO types_of_goods(type_of_good) VALUES('elektronika');
INSERT INTO types_of_goods(type_of_good) VALUES('³atwopalne');
INSERT INTO types_of_goods(type_of_good) VALUES('mleko');
INSERT INTO types_of_goods(type_of_good) VALUES('woda');
INSERT INTO types_of_goods(type_of_good) VALUES('delikatnej konstrukcji');
INSERT INTO types_of_goods(type_of_good) VALUES('mro¿onki');
create table countries(
id int primary key auto_increment,
name varchar(50) not null
);
		INSERT INTO countries(name) VALUE('Albania');
		INSERT INTO countries(name) VALUE('Andora');
		INSERT INTO countries(name) VALUE('Austria');
		INSERT INTO countries(name) VALUE('Belgia');
		INSERT INTO countries(name) VALUE('Bia³oruœ');
		INSERT INTO countries(name) VALUE('Boœnia i Hercegowina	');
		INSERT INTO countries(name) VALUE('Bu³garia');
		INSERT INTO countries(name) VALUE('Chorwacja');
		INSERT INTO countries(name) VALUE('Czarnogóra');
		INSERT INTO countries(name) VALUE('Czechy');
		INSERT INTO countries(name) VALUE('Dania');
		INSERT INTO countries(name) VALUE('Estonia');
		INSERT INTO countries(name) VALUE('Finlandia');
		INSERT INTO countries(name) VALUE('Francja');
		INSERT INTO countries(name) VALUE('Grecja');
		INSERT INTO countries(name) VALUE('Hiszpania');
		INSERT INTO countries(name) VALUE('Holandia');
		INSERT INTO countries(name) VALUE('Irlandia');
		INSERT INTO countries(name) VALUE('Islandia');
		INSERT INTO countries(name) VALUE('Kazachstan');
		INSERT INTO countries(name) VALUE('Liechtenstein');
		INSERT INTO countries(name) VALUE('Litwa');
		INSERT INTO countries(name) VALUE('Luksemburg');
		INSERT INTO countries(name) VALUE('£otwa');
		INSERT INTO countries(name) VALUE('Macedonia');
		INSERT INTO countries(name) VALUE('Malta');
		INSERT INTO countries(name) VALUE('Mo³dawia');
		INSERT INTO countries(name) VALUE('Monako');
		INSERT INTO countries(name) VALUE('Niemcy');
		INSERT INTO countries(name) VALUE('Norwegia');
		INSERT INTO countries(name) VALUE('Polska');
		INSERT INTO countries(name) VALUE('Portugalia');
		INSERT INTO countries(name) VALUE('Rosja');
		INSERT INTO countries(name) VALUE('Rumunia');
		INSERT INTO countries(name) VALUE('San Marino');
		INSERT INTO countries(name) VALUE('Serbia');
		INSERT INTO countries(name) VALUE('S³owacja');
		INSERT INTO countries(name) VALUE('S³owenia');
		INSERT INTO countries(name) VALUE('Szwajcaria');
		INSERT INTO countries(name) VALUE('Szwecja');
		INSERT INTO countries(name) VALUE('Turcja');
		INSERT INTO countries(name) VALUE('Ukraina');
		INSERT INTO countries(name) VALUE('Watykan');
		INSERT INTO countries(name) VALUE('Wêgry');
		INSERT INTO countries(name) VALUE('Wielka Brytania');
		INSERT INTO countries(name) VALUE('W³ochy');
create table types_of_companies(
id int primary key auto_increment,
type_of_company varchar(25) not null
);
create table companies(
id int primary key auto_increment,
company_name varchar(50) not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
flat_number int,
nip_number varchar(10) not null,
phone_number varchar(15) not null,
website varchar(100),
email varchar(100) not null,
description varchar(1000),
type_of_company int not null,
note decimal
);
alter table companies
add constraint foreign key (type_of_company) references types_of_companies(id);
create table users(
id int primary key auto_increment not null,
name varchar(20) not null,
surname varchar(40) not null,
phone_number varchar(15) not null,
email varchar(50) not null,
id_company int not null
);
alter table users
add constraint foreign key (id_company) references companies(id);
create table logins(
id int primary key auto_increment not null,
login varchar(10) not null,
password varchar(20) not null,
id_user int not null,
activity int default 1
);
alter table logins
add constraint foreign key (id_user) references users(id);
create table goods(
id int primary key auto_increment not null,
title varchar(50) not null,
content varchar(1000),
trailer varchar(50) not null,
from_country varchar(30) not null,
from_city varchar(30) not null,
from_street varchar (60) not null,
to_country varchar(30) not null,
to_city varchar(30) not null,
to_street varchar (60) not null,
max_price decimal not null,
date_adding date not null,
date_of_delivery date not null,
id_login int not null,
status TINYINT(4) default '1',
actual_price decimal not,
weight decimal not null,
deadline_acution date not null,
type_good varchar(50) not null
);
alter table goods
add constraint foreign key(id_login) references logins(id);
create table purchase_offers(
id int primary key auto_increment not null,
id_login int not null,
id_good int not null,
price decimal not null,
data varchar(30) not null
);
alter table purchase_offers
add constraint foreign key (id_login) references logins(id);
alter table purchase_offers
add constraint foreign key(id_good) references goods(id);
create table finished_transactions(
id int primary key auto_increment,
id_purchase_offer int not null
);
alter table finished_transactions
add constraint foreign key(id_purchase_offer) references purchase_offers(id);
CREATE TABLE notes_comments(
id int primary key auto_increment,
id_login int not null,
id_good int not null,
id_company int not null,
comment varchar(150) not null,
data VARCHAR(50) not null,
note int not null
);
alter table notes_comments
add constraint foreign key(id_login) references logins(id);
alter table notes_comments
add constraint foreign key(id_good) references goods(id);
alter table notes_comments
add constraint foreign key(id_company) references companies(id);
ALTER TABLE `carriage_of_goods`.`companies` CHANGE COLUMN `note` `note` DECIMAL(10,2) NULL DEFAULT NULL  ;
