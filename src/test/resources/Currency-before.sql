-- DELETE FROM inventarizations_inventarization_products;
DELETE FROM currency;

INSERT INTO currency (id, digital_code, full_name, letter_code, short_name, sort_number)
VALUES (1, '25', 'Russian Rubles', 'rub', 'rubles', '1'),
       (2, '25', 'Bellarusian Rubles', 'belrub', 'bel rubles', '2'),
       (3, '25', 'USA Dollars', 'dol', 'eng dollar', '3');

-- INSERT INTO inventarizations_inventarization_products (inventarization_id, inventarization_products_id)
-- VALUES (1, 1),
--        (1, 2),
--        (1, 3),
--        (2, 4),
--        (2, 5),
--        (2, 6),
--        (3, 7),
--        (3, 8),
--        (3, 9);

SELECT setval('currency_id_seq', max(id))
FROM inventarizations;