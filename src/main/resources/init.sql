--CREATE DATABASE tgbot;

-- create table currency (
--     id bigserial primary key,
--     price numeric(38,2) not null,
--     symbol varchar(255) not null,
--     createdAt timestamp(6) not null,
--     modifiedAt timestamp(6)
-- );

create table users (
    id bigserial primary key,
    coursePercentage integer not null default (20),
    password varchar(255) not null,
    role varchar(255) not null check (role in ('ADMIN','USER')),
    username varchar(255) not null unique,
    createdAt timestamp(6) not null,
    modifiedAt timestamp(6)
);

-- create sequence currency_id_seq;
--
-- alter sequence currency_id_seq owner to postgres;
--
-- alter sequence currency_id_seq owned by currency.id;