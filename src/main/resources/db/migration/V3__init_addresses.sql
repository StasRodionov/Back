TRUNCATE addresses CASCADE;

INSERT INTO addresses (id, index, country, region, city, street, house, apartment)
VALUES (1, '123456', 'Россия', 'Московская', 'Москва', 'ул. Ленина', '11', '15'),
       (2, '188678', 'USA', 'Колумбия', 'Вашингтон', 'ул. 5я Авеню', '6', '22'),
       (3, '123456', 'Panama', 'Область', 'Столица Панамы', 'ул. Индейцев', '2', '1');
SELECT setval('addresses_id_seq', max(id)) FROM addresses;
