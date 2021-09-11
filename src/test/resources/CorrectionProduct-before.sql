TRUNCATE correction_products CASCADE;
TRUNCATE products CASCADE;

ALTER TABLE correction_products DROP CONSTRAINT IF EXISTS fkce5t6mbl3wyno68nskoi1knxy;
ALTER TABLE products DROP CONSTRAINT IF EXISTS fknnc51dj9kv0ohq9qlvo5o3kyl;


INSERT INTO products (id, archive, country, description, item_nubmber, minimum_balance, name, purchase_price,
                      sale_tax, service, volume, weight, attribute_of_calculation_object_id, contractor_id,
                      product_group_id, tax_system_id, unit_id)
VALUES (1, false, null, 'Красные яблоки голден0', 0, 0, 'Яблоки0', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (2, false, null, 'Красные Бананы голден0', 0, 0, 'Бананы0', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (3, false, null, 'Красные Мандарины голден0', 0, 0, 'Мандарины0', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (4, false, null, 'Красные яблоки голден1', 0, 0, 'Яблоки1', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (5, false, null, 'Красные Бананы голден1', 0, 0, 'Бананы1', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2),
       (6, false, null, 'Красные Мандарины голден1', 0, 0, 'Мандарины1', 33.33, null, false, 1.00, 1.00, 3, 2, 3, 3, 3),
       (7, false, null, 'Красные яблоки голден2', 0, 0, 'Яблоки2', 11.11, null, false, 1.00, 1.00, 1, 1, 1, 1, 1),
       (8, false, null, 'Красные Бананы голден2', 0, 0, 'Бананы2', 22.22, null, false, 1.00, 1.00, 2, 2, 2, 2, 2);

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
