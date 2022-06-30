CREATE TABLE type_of_accounting
(
    id          bigserial not null,
    name        varchar(255),
    sort_number varchar(255),
    primary key (id)
);

INSERT INTO type_of_accounting (
    id,
    name,
    sort_number)
VALUES (1, 'Без специализированного учета', '1'),
       (2, 'Алкогольный товар', '2'),
       (3, 'Учет по серийным номерам', '3'),
       (4, 'Средство индивидуальной защиты', '4');

SELECT setval('type_of_accounting_id_seq', max(id))
FROM type_of_accounting
