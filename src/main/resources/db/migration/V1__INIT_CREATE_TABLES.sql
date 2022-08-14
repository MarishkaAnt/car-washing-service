create table if not exists public.users
(
    id              serial          not null primary key,
    first_name      varchar         not null,
    last_name       varchar         not null,
    email           varchar         not null unique,
    user_password   varchar         not null,
    is_active       boolean         default TRUE,
    user_role       varchar         ,
    has_discount    boolean         default FALSE,
    discount_amount integer         default 0
);
comment on table public.users is 'Пользователи';
create sequence if not exists user_id_seq owned by public.users.id;
alter table users alter column id set default nextval('user_id_seq');
--------------------------------------------------------------------------------------
create table if not exists public.wash_types
(
    id              serial          not null primary key,
    wash_type_name  varchar         not null,
    duration        interval        not null,
    has_discount    boolean         default FALSE,
    discount_amount integer         default 0,
    wash_cost       decimal(12,2)   not null
);
comment on table public.wash_types is 'Типы оказываемых услуг по мойке машин';
create sequence if not exists wash_type_id_seq owned by public.wash_types.id;
alter table wash_types alter column id set default nextval('wash_type_id_seq');
--------------------------------------------------------------------------------------
create table if not exists public.box_types
(
    id                  serial          not null primary key,
    type_name           varchar         not null,
    speedCoefficient    decimal(3,2)    not null
);
comment on table public.box_types is 'Тип бокса, согласно его оснащению';
comment on column box_types.speedcoefficient is
    'Коэффициент скорости. Стандартный коэф.скорости = 1, при коэффициенте = 0,5 работа будет выполнена в 2 раза быстрее стандарта';
create sequence if not exists box_type_id_seq owned by public.box_types.id;
alter table box_types alter column id set default nextval('box_type_id_seq');
----------------------------------------------------------------------------------------
create table if not exists public.boxes
(
    id                  serial          not null primary key,
    box_type_id         integer         constraint fk_box_type references box_types(id),
    open_time           time            not null,
    close_time          time            not null,
    has_discount        boolean         default FALSE,
    discount_amount     integer         default 0
);
comment on table public.boxes is 'Боксы для мойки машин';
create sequence if not exists box_id_seq owned by public.boxes.id;
alter table boxes alter column id set default nextval('box_id_seq');
--------------------------------------------------------------------------------------
create table if not exists public.bookings
(
    id             serial        not null primary key,
    wash_type_id   integer       constraint fk_booking_wash_type references public.wash_types(id),
    box_id         integer       constraint fk_booking_box references public.boxes(id),
    user_id        integer       constraint fk_booking_user references users(id),
    total_cost     decimal(12,2) ,
    status         varchar       not null,
    is_paid        boolean       default false,
    payment_time   timestamp     ,
    datetime_from  timestamp     ,
    datetime_to    timestamp
);
comment on table public.bookings is 'Брони/записи';
create sequence if not exists booking_id_seq owned by public.bookings.id;
alter table bookings alter column id set default nextval('booking_id_seq');
--------------------------------------------------------------------------------------
create table if not exists public.discount_settings
(
    id          integer primary key,
    max_value   integer,
    min_value   integer
);
insert into public.discount_settings(id, max_value, min_value) values (1, 30, 10)
    on conflict do nothing;
