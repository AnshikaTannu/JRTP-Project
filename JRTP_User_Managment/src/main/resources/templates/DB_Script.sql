  create table city (city_id integer not null, city_name varchar(255), state_id integer, primary key (city_id)) engine=InnoDB

 create table country (country_id integer not null, country_name varchar(255), primary key (country_id)) engine=InnoDB

 create table state (state_id integer not null, state_name varchar(255), country_id integer, primary key (state_id)) engine=InnoDB

 create table user (u_id integer not null auto_increment, created_date date, email varchar(255), phno bigint,  pwd varchar(255),pwd_update varchar(255), uname varchar(255), updated_date date, city_id integer, country_id integer, state_id integer, primary key (u_id)) engine=InnoDB

 alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state (state_id)

 alter table state add constraint FKghic7mqjt6qb9vq7up7awu0er foreign key (country_id) references country (country_id)

 alter table user add constraint FK29eqyw0gxw5r4f1ommy11nd9i foreign key (city_id) references city (city_id)

 alter table user add constraint FKge8lxibk9q3wf206s600otk61 foreign key (country_id) references country (country_id)

 alter table user add constraint FKs9pywmstsu61hpsaodsg9lwhe foreign key (state_id) references state (state_id)


insert into country values(1,'India');
insert into country values(2,'USA');


insert into country values(1,'AP',1);
insert into country values(2,'TG',1);

insert into country values(3,'RI',2);
insert into country values(4,'NJ',2);


insert into city values(1,'Guntur',1);
insert into city values(2,'Ongole',1);

insert into city values(3,'Hyderabad',2);
insert into city values(4,'Warangal',2);

insert into city values(5,'Providence',3);
insert into city values(6,'New Port',3);

insert into city values(7,'Jersay',4);
insert into city values(8,'Newark',4);
