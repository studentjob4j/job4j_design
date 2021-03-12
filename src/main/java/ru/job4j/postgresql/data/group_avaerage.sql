create table devices(
                        id serial primary key,
                        name varchar(255),
                        price float
);

create table people(
                       id serial primary key,
                       name varchar(255)
);

create table devices_people(
                               id serial primary key,
                               device_id int references devices(id),
                               people_id int references people(id)
);

insert into people(name) values ('Vangok');
insert into people(name) values ('Chupachups');
insert into people(name) values ('Likee');
select * from people;

insert into devices(name, price) values ('samsung', 12);
insert into devices(name, price) values ('apple', 23);
insert into devices(name, price) values ('motorolla', 11);
insert into devices(name, price) values ('huawei', 45);
insert into devices(name, price) values ('sony', 10);
insert into devices(name, price) values ('dji', 53);
select * from devices;

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (5, 3);
insert into devices_people(device_id, people_id) values (6, 3);
select * from devices_people;

select avg(price) from devices;
select s.name, avg(ss.price) from people as s join devices ss on s.id = ss.id group by s.name;
select s.name, avg(ss.price) from people as s join devices ss on s.id = ss.id group by s.name having avg(ss.price) > 12;

