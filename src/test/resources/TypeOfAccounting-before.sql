TRUNCATE type_of_accounting CASCADE;

INSERT INTO type_of_accounting (
    id,
    name,
    sort_number)
VALUES (1, 'Без специализированного учета', '1'),
       (2, 'Алкогольный товар', '2'),
       (3, 'Учет по серийным номерам', '3'),
       (4, 'Средство индивидуальной защиты', '4');

