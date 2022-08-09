alter table public.bookings
    add column box_id integer constraint fk_bookings_box references boxes(id);