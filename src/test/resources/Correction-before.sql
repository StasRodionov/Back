TRUNCATE corrections CASCADE;
TRUNCATE correction_products CASCADE;
TRUNCATE products CASCADE;
TRUNCATE warehouses CASCADE;
TRUNCATE companies CASCADE;


ALTER TABLE correction_products DROP CONSTRAINT IF EXISTS fkce5t6mbl3wyno68nskoi1knxy;
ALTER TABLE products DROP CONSTRAINT IF EXISTS fknnc51dj9kv0ohq9qlvo5o3kyl;

INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'OOO "Организация №2"', true, '733126789654', '00002', 'stamp', 2, 2),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'OOO "Организация №3"', true, '799123786542', '00003', 'stamp', 3, 3);

insert into warehouses (id, address, comment, comment_to_address, name, sort_number)
values  (1, 'Moskva', 'coment', 'coment adress', 'Основной склад', '00001'),
        (2, 'Moskva', 'coment', 'coment adress', 'Основной склад1', '00001'),
        (3, 'Moskva', 'coment', 'coment adress', 'Основной склад2', '00001'),
        (4, 'Moskva', 'coment', 'coment adress', 'Основной склад3', '00001');

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

INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product, company_id, warehouse_id)
VALUES (1, 'Оприходование 1', '2021-06-23 15:10', false, false, false, 1, 1),
       (2, 'Оприходование 2', '2021-06-23 15:10', false, false, false, 2, 1),
       (3, 'Оприходование 3', '2021-06-23 15:10', false, false, false, 3, 1);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9);

SELECT setval('corrections_id_seq', max(id))
FROM corrections;

