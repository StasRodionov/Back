TRUNCATE return_suppliers;
TRUNCATE contracts CASCADE;

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id, contractor_id, legal_detail_id)
VALUES (1, 200, false, 'no comments', '2021-08-06', '1', 3, 1, 1, 1);

INSERT INTO return_suppliers (id, comment, date, is_print, is_send, company_id, contract_id, contractor_id,
                              warehouse_id)
VALUES (1, 'Комментарий 1', '2021-06-23 15:10', false, false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-06-23 15:10', true, false, 1, 1, 1, 1),
       (3, 'Комментарий 3', '2021-06-23 15:10', false, true, 1, 1, 1, 1),
       (4, 'Комментарий 4', '2021-06-23 15:10', true, true, 1, 1, 1, 1);