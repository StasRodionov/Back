TRUNCATE sale_tax CASCADE;

INSERT INTO sale_tax (
    id,
    name,
    sort_number)
VALUES (1, 'без НДС', '1'),
       (2, '0%', '2'),
       (3, '10%', '3'),
       (4, '18%', '4'),
       (5, '20%', '5');

SELECT setval('sale_tax_id_seq', max(id))
FROM sale_tax