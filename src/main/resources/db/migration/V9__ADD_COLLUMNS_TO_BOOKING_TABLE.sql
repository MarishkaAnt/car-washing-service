alter table public.bookings
add column if not exists user_id integer constraint fk_booking_user references users(id);
