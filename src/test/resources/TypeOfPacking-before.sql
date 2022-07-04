TRUNCATE type_of_packing CASCADE;

INSERT INTO type_of_packing (
    id,
    name,
    sort_number)
VALUES (1, 'Штучная', '1'),
       (2, 'Весовая', '2'),
       (3, 'Разливная', '3');

SELECT setval('type_of_packing_id_seq', max(id))
FROM type_of_packing