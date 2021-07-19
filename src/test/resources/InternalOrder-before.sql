TRUNCATE internal_order CASCADE;

INSERT INTO internal_order (id, comment, date, is_print, is_sent, company_id)
VALUES (1, 'Комментарий 1', '1234-12-12 12:34', false, false, 1),
       (2, 'Комментарий 2', '1234-12-12 12:34', true, false, 2),
       (3, 'Комментарий 3', '1234-12-12 12:34', false, true, 3);

SELECT setval('internal_order_id_seq', max(id))
FROM corrections;