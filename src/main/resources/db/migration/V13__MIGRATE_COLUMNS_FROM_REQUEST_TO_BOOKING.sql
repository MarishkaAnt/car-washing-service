alter table public.request
    drop constraint if exists fk_request_boxes;

alter table public.request
    drop column if exists box_id;

alter table public.request
    drop column if exists response_datetime_from;

alter table public.request
    drop column if exists response_datetime_to;

alter table public.request
    drop column if exists duration;


