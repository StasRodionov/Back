TRUNCATE correction_products CASCADE;

ALTER TABLE correction_products DROP CONSTRAINT IF EXISTS fkce5t6mbl3wyno68nskoi1knxy;

INSERT INTO correction_products (id, product_id, amount, price) VALUES
(1, 1, 11.00, 12.00),
(2, 2, 13.00, 14.00),
(3, 3, 15.00, 16.00),
(4, 4, 17.00, 18.00),
(5, 5, 18.00, 19.00),
(6, 6, 19.00, 20.00),
(7, 7, 20.00, 21.00),
(8, 8, 21.00, 22.00),
(9, 9, 22.00, 23.00);

SELECT setval('correction_products_id_seq', max(id)) FROM correction_products;
