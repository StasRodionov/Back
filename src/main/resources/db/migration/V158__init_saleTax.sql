CREATE TABLE sale_tax
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

INSERT INTO sale_tax (
    id,
    name,
    sort_number)
VALUES (1, 'без НДС', '1'),
       (2, '0%', '2'),
       (3, '10%', '3'),
       (3, '18%', '4'),
       (3, '20%', '5');

SELECT setval('sale_tax_id_seq', max(id))
FROM sale_tax