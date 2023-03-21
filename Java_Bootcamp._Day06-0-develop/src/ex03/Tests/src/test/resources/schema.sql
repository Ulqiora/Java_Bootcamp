create schema if not exists shop;
create table if not exists shop.product(
    id integer,
    name varchar(40),
    price integer
);