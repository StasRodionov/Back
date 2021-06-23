INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product,
                         company_id, warehouse_id)
VALUES (2, 'Оприходование 2', '2021-06-22 15:10', false, false, false, 1, 1);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (2, 4),
       (2, 5),
       (2, 6);

SELECT setval('corrections_id_seq', max(id))
FROM corrections;

