alter table public.request
add column response_datetime_from timestamp;

alter table public.request
add column response_datetime_to timestamp;

alter table public.request
add column duration interval;