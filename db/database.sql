create database carriage_of_goods;
use carriage_of_goods;
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
type_of_company int not null
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