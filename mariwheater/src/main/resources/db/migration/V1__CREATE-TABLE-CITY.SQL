CREATE TABLE CITY (
    id integer unique not null auto_increment,
    name varchar(150) not null,
    temperature float not null,
    last_updated timestamp,

    primary key(id)
);