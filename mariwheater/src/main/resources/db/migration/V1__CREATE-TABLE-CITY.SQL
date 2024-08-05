CREATE TABLE CITY (
    id integer unique not null auto_increment,
    name varchar(150) not null,
    temperature DECIMAL(4, 2) not null,
    last_updated timestamp,

    primary key(id)
);