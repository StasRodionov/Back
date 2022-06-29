TRUNCATE kits CASCADE;

INSERT INTO kits (
    id,
    name,
    description,
    archive,
    weight,
    volume,
    purchase_price,
    unit_id,
    contractor_id,
    tax_system_id,
    item_number,
    sale_tax,
    employee_id,
    department_id,
    access_all)
VALUES (1, 'Джентельменский набор №1', 'Костюм тройка, туфли, галстук', false, 0, 0, 33.33, 1, 1, 1, 4, '20', 1, 1, false ),
       (2, 'Джентельменский набор №2', 'Костюм двойка, ,туфли, бабочка', false, 0, 0, 44.44, 1, 1, 1, 5,  '20', 1, 1, false ),
       (3, 'Джентельменский набор №3', 'Пиджак, джинсы, ботинки', false, 0, 0, 55.55, 1, 1, 1, 6, '20', 1, 1, false );

SELECT setval('kits_id_seq', max(id))
FROM kits


