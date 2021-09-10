TRUNCATE retail_sales CASCADE;
TRUNCATE contractors CASCADE;

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1);

INSERT INTO retail_sales (id, time, retail_store_id, contractor_id, company_id, sum_cash,
                          sum_non_cash, prepayment, sum_discount, sum, sent, printed, comment)
VALUES (1, '2021-08-11', 1, 1, 1, 145000.00, 0.00, 0.00, 0.00, 145000.00, false, false, 'Комментарий 1'),
       (2, '2020-08-11', 1, 1, 1, 0.00, 145000.00, 0.00, 0.00, 145000.00, true, true, 'Комментарий 2'),
       (3, '2019-08-11', 1, 1, 1, 236000.00, 0.00, 0.00, 0.00, 236000.00, false, true, 'Комментарий 3'),
       (4, '2018-08-11', 1, 1, 1, 145000.00, 0.00, 0.00, 1000.00, 144000.00, true, false, 'Комментарий 4'),
       (5, '2017-08-11', 1, 1, 1, 100000.00, 0.00, 0.00, 0.00, 100000.00, false, true, 'Комментарий 5');
SELECT setval('retail_sales_id_seq', max(id))
FROM retail_sales