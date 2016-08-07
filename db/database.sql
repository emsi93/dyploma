create database carriage_of_goods;
use carriage_of_goods;
create table types_of_goods(
id int primary key auto_increment,
type_of_good varchar(20) not null
);
create table types_of_bodies(
id int primary key auto_increment,
type_of_body varchar(30) not null
);
create table permissions(
id int primary key auto_increment,
permission varchar(25) not null
);
create table logs(
description varchar(255) not null,
date_time timestamp not null
);
create table shippers(
id int primary key auto_increment,
company_name varchar(50) not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
nip_number varchar(10) not null,
regon_number varchar(14) not null,
phone_number varchar(15) not null,
email varchar(100) not null
);
create table carriers(
id int primary key auto_increment,
company_name varchar(50) not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
nip_number varchar(10) not null,
regon_number varchar(14) not null,
phone_number varchar(15) not null,
email varchar(100) not null
);
create table users_shipper(
id int primary key auto_increment,
name varchar(20) not null,
surname varchar(30) not null,
login varchar(10) not null,
password varchar(30) not null,
id_permission int not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
flat_number int,
phone_number varchar(15) not null,
email varchar(100) not null,
pesel_number varchar(11) not null,
id_shipper int not null,
status boolean not null
);
alter table users_shipper
add constraint foreign key (id_permission) references permissions(id);
alter table users_shipper
add constraint foreign key (id_shipper) references shippers(id);
create table users_carrier(
id int primary key auto_increment,
name varchar(20) not null,
surname varchar(30) not null,
login varchar(10) not null,
password varchar(30) not null,
id_permission int not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
flat_number int,
phone_number varchar(15) not null,
email varchar(100) not null,
pesel_number varchar(11) not null,
id_carrier int not null,
status boolean not null
);
alter table users_carrier
add constraint foreign key (id_permission) references permissions(id);
alter table users_carrier
add constraint foreign key (id_carrier) references carriers(id);
create table goods(
id int primary key auto_increment,
short_description varchar(50) not null,
id_type_of_good int not null,
country_A varchar(30) not null,
postcode_A varchar(10) not null,
city_A varchar(30) not null,
street_A varchar (60) not null,
country_B varchar(30) not null,
postcode_B varchar(10) not null,
city_B varchar(30) not null,
street_B varchar (60) not null,
long_description varchar(50),
weight double not null,
id_type_of_body int not null,
reported timestamp not null,
valid_until timestamp not null,
id_user_shipper int not null,
freight_price double not null,
current_freight_price double not null,
status boolean not null
);
alter table goods
add constraint foreign key (id_type_of_good) references types_of_goods(id);
alter table goods
add constraint foreign key (id_type_of_body) references types_of_bodies(id);
alter table goods
add constraint foreign key (id_user_shipper) references users_shipper(id);
create table transactions(
id_good int not null,
id_user_shipper int not null,
id_user_carrier int not null,
price double not null,
transaction_time timestamp not null
);
alter table transactions
add constraint foreign key (id_good) references goods(id);
alter table transactions
add constraint foreign key (id_user_shipper) references users_shipper(id);
alter table transactions
add constraint foreign key (id_user_carrier) references users_carrier(id);