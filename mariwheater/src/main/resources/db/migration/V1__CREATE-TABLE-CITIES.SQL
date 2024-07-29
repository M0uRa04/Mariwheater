CREATE TABLE CITIES (
    id integer unique not null,
    name varchar(150) not null,
    temperature float not null,
    last_updated timestamp,

    primary key(id)
);