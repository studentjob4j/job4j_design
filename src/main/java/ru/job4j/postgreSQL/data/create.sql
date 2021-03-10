
create table role(
                     id serial primary key,
                     profession varchar(30)
);

create table rules(
                      id serial primary key,
                      description varchar(30)
);

create table comments(
                         id serial primary key,
                         description text
);

create table attachs(
                        id serial primary key,
                        dataCreate date
);

create table status(
                       id serial primary key,
                       status boolean
);

create table category(
                         id serial primary key,
                         description text
)

create table role_rules(
                           id serial primary key,
                           id_role int references role(id),
                           id_rules int references rules(id)
);

create table item(
                     id serial primary key ,
                     name varchar(30),
                     comment_id int references comments(id),
                     attaches_id int references attachs(id),
                     category_id int references category(id),
                     status_id int references status(id)
);

create table anyUser(
                        id serial primary key,
                        name varchar(10),
                        birhday date,
                        role_id int references role(id),
                        item_id int references item(id)
);




