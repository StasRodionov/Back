TRUNCATE type_of_products CASCADE;

INSERT INTO type_of_products (
    id,
    name,
    sort_number)
VALUES (1, 'Не маркируется', '1'),
       (2, 'Молочная продукция', '2');
