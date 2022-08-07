alter table public.bookings
add column datetime_from timestamp;

alter table public.bookings
add column datetime_to timestamp;

alter table public.bookings
add column duration interval;