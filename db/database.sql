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
create table types_of_companies(
id int primary key auto_increment,
type_of_company varchar(25) not null
);
create table logs(
description varchar(255) not null,
date_time timestamp not null
);
create table companies(
id int primary key auto_increment,
company_name varchar(50) not null,
country varchar(30) not null,
postcode varchar(10) not null,
city varchar(30) not null,
street varchar (60) not null,
house_number int not null,
suice_number int,
nip_number varchar(10) not null,
phone_number varchar(15) not null,
website varchar(100),
email varchar(100) not null,
type_of_company int not null,
description varchar(1000)
);
alter table companies
add constraint foreign key (type_of_company) references types_of_companies(id);
create table users(
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
house_number int not null,
flat_number int,
phone_number varchar(15) not null,
email varchar(100) not null,
pesel_number varchar(11) not null,
id_company int not null,
status varchar(8) not null
);
alter table users
add constraint foreign key (id_permission) references permissions(id);
alter table users
add constraint foreign key (id_company) references companies(id);
create table goods(
id int primary key auto_increment,
short_description varchar(25) not null,
id_type_of_good int not null,
country_A varchar(30) not null,
postcode_A varchar(10) not null,
city_A varchar(30) not null,
street_A varchar (60) not null,
country_B varchar(30) not null,
postcode_B varchar(10) not null,
city_B varchar(30) not null,
street_B varchar (60) not null,
long_description varchar(1000),
weight double not null,
id_type_of_body int not null,
reported timestamp not null,
valid_until timestamp not null,
id_shipper int not null,
freight_price double not null,
current_freight_price double not null,
status boolean not null
);
alter table goods
add constraint foreign key (id_type_of_good) references types_of_goods(id);
alter table goods
add constraint foreign key (id_type_of_body) references types_of_bodies(id);
alter table goods
add constraint foreign key (id_shipper) references users(id);
create table transactions(
id_good int not null,
id_carrier int not null,
price double not null,
transaction_time timestamp not null
);
alter table transactions
add constraint foreign key (id_good) references goods(id);
alter table transactions
add constraint foreign key (id_carrier) references users(id);