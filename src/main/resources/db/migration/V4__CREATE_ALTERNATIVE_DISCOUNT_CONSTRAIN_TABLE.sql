create table if not exists public.discount_constrains_settings(

    id          serial primary key,
    max_value   decimal(5,2),
    min_value   decimal(5,2)
);

insert into public.discount_constrains_settings values (1, 30.0, 10.0);