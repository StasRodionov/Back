insert into public.movement (id, comment, date, is_print, is_sent, company_id, warehouse_from_id, warehouse_to_id)
values  (1, 'Перемещение 1', '2021-08-10 09:03:00.000000', false, false, 1, 1, 2),
        (2, 'Перемещение 2', '2021-08-10 09:03:00.000000', false, false, 2, 1, 3),
        (3, 'Перемещение 3', '2021-08-10 09:03:00.000000', false, true, 1, 2, 1);