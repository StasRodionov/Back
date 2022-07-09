CREATE TABLE type_of_packing
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

INSERT INTO type_of_packing (
    id,
    name,
    sort_number)
VALUES (1, 'Штучная', '1'),
       (2, 'Весовая', '2'),
       (3, 'Разливная', '3');

SELECT setval('type_of_packing_id_seq', max(id))
FROM type_of_packing