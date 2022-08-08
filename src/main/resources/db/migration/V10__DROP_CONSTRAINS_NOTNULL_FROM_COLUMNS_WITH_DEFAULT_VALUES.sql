alter table users alter column  discount_amount drop not null;
alter table users drop column if exists role_id;
alter table users add column user_role varchar(16);

alter table wash_types alter column discount_amount drop not null;

alter table boxes alter column has_discount drop not null;
alter table boxes alter column discount_amount drop not null;

alter table bookings alter column is_paid drop not null;
alter table bookings alter column is_paid set default false;

