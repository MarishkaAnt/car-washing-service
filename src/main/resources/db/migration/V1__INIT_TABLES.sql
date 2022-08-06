create table if not exists public.roles
(
    id serial not null primary key,
    value varchar not null
);
comment on table public.roles is 'Роли пользователей';

create table if not exists public.users
(
    id              serial          not null primary key,
    first_name      varchar(255)    not null,
    last_name       varchar(255)    not null,
    email           varchar(255)    not null unique,
    password        varchar(255)    not null,
    is_active       boolean         default TRUE,
    role_id         integer         not null constraint fk_user_role references public.roles(id),
    has_discount    boolean         default FALSE,
    discount_amount decimal(10,2)   not null default 0.0
);
comment on table public.users is 'Пользователи';

create table if not exists public.wash_types (
    id              serial          not null primary key,
    name            varchar         not null,
    duration        interval        not null,
    has_discount    boolean         default FALSE,
    discount_amount decimal(10,2)   not null default 0.0
);
comment on table public.wash_types is 'Типы оказываемых услуг по мойке машин';

create table if not exists public.box_types
(
    id                  serial          not null primary key,
    type                varchar(32)     not null,
    speedCoefficient    decimal(5,2)    not null
);
comment on table public.box_types is 'Тип бокса, согласно его оснащению';

create table if not exists public.boxes
(
    id                  serial          not null primary key,
    box_type_id         integer         not null constraint fk_box_type references box_types(id),
    open_time           time            not null,
    close_time          time            not null,
    has_discount        boolean         not null default FALSE,
    discount_amount     decimal(10,2)   not null default 0.0
);
comment on table public.boxes is 'Боксы для мойки машин';

create table if not exists public.request
(
   id               serial      not null primary key,
   box_id           integer     constraint fk_request_boxes references public.boxes(id),
   wash_type_id     integer     not null constraint fk_request_wash_types references public.wash_types(id),
   user_id          integer     not null constraint fk_request_users references public.users(id),
   datetime_from    timestamp,
   datetime_to      timestamp
);
comment on table public.request is 'Запрос на бронирование';
comment on column request.datetime_from is 'Желаемые пользователем начальная дата и время';
comment on column request.datetime_to is 'Желаемые пользователем конечная дата и время';

create table if not exists public.bookings (
    id             serial        not null primary key,
    request_id     integer       not null constraint fk_booking_request references public.request(id),
    total_cost     decimal(10,2) not null,
    status         varchar(16)   not null,
    is_paid        boolean       not null,
    payment_time   timestamp
);
comment on table public.bookings is 'Брони/записи';

create table if not exists public.permissions
(
    id      serial      not null primary key,
    value   varchar     not null
);
comment on table public.permissions is 'Разрешения, доступные определенной роли';

create table if not exists public.role_permission
(
    role_id         integer not null constraint fk_cross_role references public.roles,
    permission_id   integer not null constraint fk_cross_permission references public.permissions,
    primary key (role_id, permission_id)
);
comment on table public.role_permission is 'Кросс-таблица для отношения: роли-разрешения';