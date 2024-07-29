CREATE TABLE NOTIFICATIONS (
    id integer unique not null,
    city_id integer not null REFERENCES cities(id),
    message varchar(300),
    created_at timestamp,

    primary key(id)
);