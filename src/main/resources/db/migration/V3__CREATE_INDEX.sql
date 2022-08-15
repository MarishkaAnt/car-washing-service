--bookings--
create index if not exists bookings_datetime_from_ix on public.bookings
    using btree (datetime_from);
create index if not exists bookings_datetime_to_ix on public.bookings
    using btree (datetime_to);
create index if not exists bookings_status_ix on public.bookings using hash (status);
--boxes--
create index if not exists boxes_open_time_ix on public.boxes using btree (open_time);
create index if not exists boxes_close_time_ix on public.boxes using btree (close_time);
--users--
create index if not exists users_email_ix on public.users using hash (email);
create index if not exists users_password_ix on public.users using hash (user_password);
create index if not exists users_role_ix on public.users using hash (user_role);







