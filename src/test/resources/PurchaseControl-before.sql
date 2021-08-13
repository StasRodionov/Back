TRUNCATE purchase_control CASCADE;

INSERT INTO purchase_control (id, product_name, product_code, article_number, product_measure, product_quantity)
VALUES (1, 'test1', 1111111, 111, 'test1', 1111),
       (2, 'test2', 2222222, 222, 'test2', 2222),
       (3, 'test3', 3333333, 333, 'test3', 3333);

SELECT setval('purchase_control_id_seq', max(id))
FROM purchase_control;