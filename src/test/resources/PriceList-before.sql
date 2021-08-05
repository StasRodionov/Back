TRUNCATE price_lists CASCADE;

INSERT INTO price_lists (id, commentary, number, printed, sent, time, company_id)
VALUES
    (1, 'comment1', 'number1', 1, 1, '1234-12-12 12:34', 1),
    (2, 'comment2', 'number2', 2, 2, '1234-12-12 12:34', 2),
    (3, 'comment3', 'number3', 3, 3, '1234-12-12 12:34', 3);

SELECT setval('price_list_id_seq', max(id))
FROM price_lists;