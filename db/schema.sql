CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE type
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(2000),
    text    varchar(2000),
    address varchar(2000),
    type_id int references type (id)
);

CREATE TABLE accident_rule
(
    accident_id int references accident (id),
    rule_id     int references rule (id),
    primary key (accident_id, rule_id)
);

INSERT INTO type (name)
VALUES ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

INSERT INTO rule (name)
VALUES ('Статья 1'),
       ('Статья 2'),
       ('Статья 3');
