TRUNCATE contracts CASCADE;

ALTER TABLE contracts DROP CONSTRAINT IF EXISTS fkn5pkn19y02t2psn9puo74jign;
ALTER TABLE contracts DROP CONSTRAINT IF EXISTS fk8j3mjrj3nchirim7n6nnqorqp;
ALTER TABLE contracts DROP CONSTRAINT IF EXISTS fkqtw4aaypsdp68m8sao605s5br;


INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id, contractor_id, legal_detail_id)
VALUES (1, 200, false, 'no comments', '2021-08-06', '1', 3, 1, 1, 1),
       (2, 200, false, 'no comments', '2021-08-06', '2', 3, 1, 1, 1),
       (3, 200, false, 'no comments', '2021-08-06', '3', 3, 1, 1, 1);

SELECT setval('contracts_id_seq', max(id))
FROM contracts;