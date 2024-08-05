CREATE TABLE NOTIFICATIONS (
    id integer unique not null auto_increment,
    city_id integer not null REFERENCES city(id),
    message varchar(300),
    created_at timestamp,

    primary key(id)
);