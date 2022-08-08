truncate table bookings cascade;
truncate table request cascade;
truncate table users cascade;
truncate table boxes cascade;
truncate table box_types cascade;
truncate table wash_types cascade;
truncate table discount_constrains_settings cascade;

insert into wash_types values
                           (setval('wash_types_id_seq', 1), 'LIGHT', '15 minutes', default, default, 300.0),
                           (default, 'FULL', '20 minutes', default, default, 500.0),
                           (default, 'ECO', '30 minutes', default, default, 400.0),
                           (default, 'LUX', '50 minutes', default, default, 700.0)
;

insert into box_types values
                          (setval('box_types_id_seq', 1), 'STANDARD', 1.0),
                          (default, 'QUICK', 0.7),
                          (default, 'SUPER_QUICK', 0.5)
;

insert into boxes values
                      (setval('boxes_id_seq', 1), 1, '08:00:00', '21:00:00', default, default ),
                      (default, 2, '09:00:00', '21:00:00', default, default ),
                      (default, 3, '10:00:00', '23:00:00', default, default )
;

insert into discount_constrains_settings values
    (setval('discount_constrains_settings_id_seq', 1), 30.0, 5.0)
;

insert into users values
                      (setval('users_id_seq', 1), 'Ivan', 'Ivanov', 'iivanov@mail.ru', '12345',
                       default, default, default, null),
                      (default, 'John', 'Doe', 'johndoe@mail.ru', '12345', default, default, default, null),
                      (default, 'Jane', 'Doe', 'janedoe@mail.ru', '12345', default, default, default, null)
;

insert into request values
                        (setval('request_id_seq', 1), 1, 1, 1, '2022-08-10 10:00:00',
                         '2022-08-10 18:00:00', null, null, null),
                        (default, 1, 1, 1, '2022-08-10 10:00:00', '2022-08-10 18:00:00', null, null, null)
;

insert into bookings values
    (setval('bookings_id_seq', 1), 1, 300, 'NEW', default, null, null, null, '15 minutes', 1)
;
