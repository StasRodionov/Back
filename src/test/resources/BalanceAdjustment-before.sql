TRUNCATE balance_adjustments CASCADE;
DELETE FROM balance_adjustments;

ALTER TABLE balance_adjustments DROP CONSTRAINT IF EXISTS fkqbjlnf5g1uwpdoa8lwygua3ha;
ALTER TABLE balance_adjustments DROP CONSTRAINT IF EXISTS fkrf8xhcxaq8ix0rs04ic2b47tm;

INSERT INTO balance_adjustments (id, date, company_id, contractor_id, account, cash_office, comment, date_changed,
                              who_changed)
VALUES (1, '2021-06-23 15:10', 1, 1, 'Счет 1', 'Касса 1', 'Комм 1', '2021-06-23 15:10', '1'),
       (2, '2021-06-23 15:10', 2, 2, 'Счет 2', 'Касса 2', 'Комм 2', '2021-06-23 15:10', '1'),
       (3, '2021-06-23 15:10', 3, 3, 'Счет 3', 'Касса 3', 'Комм 3', '2021-06-23 15:10', '1'),
       (4, '2021-06-23 15:10', 3, 3, 'Счет 4', 'Касса 4', 'Комм 4', '2021-06-23 15:10', '1');

