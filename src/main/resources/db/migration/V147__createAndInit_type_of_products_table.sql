CREATE TABLE type_of_products
(
    id bigserial not null,
    name varchar(255),
    sort_number varchar(255),
    primary key (id)
);

INSERT INTO type_of_products (
    id,
    name,
    sort_number)
VALUES (1, 'Не маркируется', '1'),
       (2, 'Табачная продукция', '2'),
       (3, 'Обувь', '3'),
       (4, 'Одежда', '4'),
       (5, 'Постельное белье', '5'),
       (6, 'Духи и туалетная вода', '6'),
       (7, 'Фотокамеры и лампы-вспышки', '7'),
       (8, 'Шины и покрышки', '8'),
       (9, 'Молочная продукция', '9'),
       (10, 'Упакованная вода', '10'),
       (11, 'Альтернативная табачная продукция', '11'),
       (12, 'Никотиносодержащая продукция', '12');

SELECT setval('type_of_products_id_seq', max(id))
FROM type_of_products