create sequence user_id_seq owned by public.users.id;
alter table users alter column id set default nextval('user_id_seq');
update users set id = nextval('user_id_seq');

create sequence booking_id_seq owned by public.bookings.id;
alter table bookings alter column id set default nextval('booking_id_seq');
update bookings set id = nextval('booking_id_seq');

create sequence box_id_seq owned by public.boxes.id;
alter table boxes alter column id set default nextval('box_id_seq');
update boxes set id = nextval('box_id_seq');

create sequence box_type_id_seq owned by public.box_types.id;
alter table box_types alter column id set default nextval('box_type_id_seq');
update box_types set id = nextval('box_type_id_seq');

create sequence discount_id_seq owned by public.discount_constrains_settings.id;
alter table discount_constrains_settings alter column id set default nextval('discount_id_seq');
update discount_constrains_settings set id = nextval('discount_id_seq');

create sequence request_id_seq owned by public.request.id;
alter table request alter column id set default nextval('request_id_seq');
update request set id = nextval('request_id_seq');

create sequence wash_type_id_seq owned by public.wash_types.id;
alter table wash_types alter column id set default nextval('wash_type_id_seq');
update wash_types set id = nextval('wash_type_id_seq');