CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password, enabled, authority_id)
VALUES ('root', '$2a$10$b5cO8jS4n6VXhtO6GJ9ih.MUahUy/LFolRYE4aN9.RhPKU.ldfcUy', true,
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));
