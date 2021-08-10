INSERT INTO supplier_accounts (id, comment, date, is_spend, company_id, contract_id, contractor_id, warehouse_id)
VALUES (1, 'Комментарий 1', '2021-08-10T09:03:49.174239100', false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-08-10T09:03:49.203559500', false, 2, 1, 2, 1),
       (3, 'Комментарий 3', '2021-08-10T09:03:49.208638500', false, 3, 1, 3, 1),
       (4, 'Комментарий 4', '2021-08-10T09:03:49.211652200', false, 4, 1, 4, 1),
       (5, 'Комментарий 5', '2021-08-10T09:03:49.214097700', false, 5, 1, 5, 1);
SELECT setval('supplier_accounts_id_seq', max(id))
FROM supplier_accounts