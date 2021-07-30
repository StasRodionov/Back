TRUNCATE internal_order CASCADE;

INSERT INTO payments (id, number, payment_methods, sum, time, type_of_payment, company_id, contract_id, contractor_id, project_id)
VALUES (1, '0001', 'CASH', 100, '2021-07-30 13:23:24.249491', 'INCOMING',1, 1, 1, 1),
       (2, '0002', 'CASH', 100, '2021-07-30 13:23:24.249491', 'INCOMING',1, 1, 1, 1),
       (3, '0003', 'CASH', 100, '2021-07-30 13:23:24.249491', 'INCOMING',1, 1, 1, 1);

SELECT setval('payments_id_seq', max(id))
FROM payments;