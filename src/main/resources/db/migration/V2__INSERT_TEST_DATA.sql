truncate table bookings cascade;
truncate table users cascade;
truncate table boxes cascade;
truncate table box_types cascade;
truncate table wash_types cascade;

insert into wash_types (wash_type_name, duration, has_discount, discount_amount, wash_cost)
values
    ('LIGHT', '15 minutes', default, default, 300.0),
    ('FULL', '20 minutes', default, default, 500.0),
    ('ECO', '30 minutes', default, default, 400.0),
    ('LUX', '50 minutes', default, default, 700.0)
on conflict do nothing;

insert into box_types (type_name, speedcoefficient)
values
    ('STANDARD', 1.0),
    ('QUICK', 0.7),
    ('SUPER_QUICK', 0.5)
on conflict do nothing;

insert into boxes (box_type_id, open_time, close_time, has_discount, discount_amount)
values
    (1, '08:00:00', '21:00:00', default, default ),
    (2, '09:00:00', '21:00:00', default, default ),
    (3, '10:00:00', '23:00:00', default, default )
on conflict do nothing;

insert into users (first_name, last_name, email, user_password, is_active, user_role, has_discount, discount_amount)
values
    ('Ivan', 'Ivanov', 'iivanov@mail.ru', '$2a$05$rAT6sQqJYujSkFU0CsVR8eQIL2C9sSefB7DVOnF5XvspCqVCgazoa',
    default, 'USER', default, default),
    ('John', 'Doe', 'johndoe@mail.ru', '$2a$05$JTAHRv7wi9QyTdVRGWSgWO8n73dhEgpcZWEX93/VtAX3jMdt7RmRK',
    default, 'OPERATOR', default, default),
    ('Jane', 'Doe', 'janedoe@mail.ru', '$2a$05$vIqu7CHYjn.9oS3/EIPFqOsO5wLZO30.lkuPIhJ1VgI/0anOdiHYi',
    default, 'ADMIN', default, default)
on conflict do nothing;

insert into bookings (wash_type_id, box_id, user_id, status, is_paid, datetime_from, datetime_to)
values
    (1, 1, 1, 'NEW', false, '2022-08-10 10:00:00', '2022-08-10 18:00:00')
on conflict do nothing;
