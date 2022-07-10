TRUNCATE public.audit;

INSERT INTO public.audit (description, date, employee_id)
VALUES ('test1'::varchar(255), '2022-07-10 09:38:25.000000'::timestamp, 1::bigint);

INSERT INTO public.audit (description, date, employee_id)
VALUES ('test2'::varchar(255), '2022-07-11 09:38:28.000000'::timestamp, 2::bigint);

INSERT INTO public.audit (description, date, employee_id)
VALUES ('test2'::varchar(255), '2022-07-12 09:38:32.000000'::timestamp, 3::bigint);