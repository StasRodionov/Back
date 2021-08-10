insert into public.internal_order_products (id, amount, price, product_id)
values  (1, 66.00, 39.00, 1),
        (2, 14.00, 100.00, 2),
        (3, 34.00, 77.00, 3),
        (4, 65.00, 35.00, 4),
        (5, 45.00, 19.00, 5),
        (6, 50.00, 80.00, 6),
        (7, 12.00, 52.00, 7),
        (8, 24.00, 53.00, 8),
        (9, 53.00, 28.00, 9),
        (10, 94.00, 18.00, 10),
        (11, 40.00, 77.00, 11),
        (12, 18.00, 32.00, 12),
        (13, 23.00, 65.00, 13),
        (14, 66.00, 31.00, 14),
        (15, 15.00, 51.00, 15);
SELECT setval('internal_order_products_id_seq', max(id))
FROM internal_order_products