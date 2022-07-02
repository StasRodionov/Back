TRUNCATE sales_channel CASCADE;

INSERT INTO sales_channel(id, name, type, description)
VALUES
    (1, 'Канал1', 'Тип1', 'Описание1'),
    (2, 'Канал2', 'Тип2', 'Описание2'),
    (3, 'Канал3', 'Тип3', 'Описание3');

SELECT setval('sales_channel_id_seq', max(id))
FROM sales_channel;