create table if not exists public.discount_constrains
(
    name varchar(16),
    value decimal(5,2) default 0.0
);

insert into discount_constrains values ('MAX', 30.0), ('MIN', 0.0);