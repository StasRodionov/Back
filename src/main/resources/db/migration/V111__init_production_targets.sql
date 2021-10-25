INSERT INTO production_targets (id, date, company_id, deliveryPlannedMoment, materialWarehouse,
                                productionWarehouse, productionStart, productionEnd, shared, Owner, employeeOwner,
                                published, printed, description, updated, updatedByName)
VALUES (1, '2021-08-10 12:15:00.000000', 1, '2021-22-11 12:15:00.000000', 'ООО"Агроном', 'Химкинский склад',
        '2021-10-09 08:00:00.000000',  '2021-11-22 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 1', false, 'Я'),
       (2, '2021-10-10 12:15:00.000000', 1, '2022-22-01 12:15:00.000000', 'ООО"Агроном', 'Химкинский склад',
        '2021-10-11 08:00:00.000000',  '2021-11-19 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 2', false, 'Я'),
       (3, '2021-08-10 12:15:00.000000', 1, '2021-22-11 12:15:00.000000', 'ООО"Агроном', 'Химкинский склад',
        '2021-09-09 08:00:00.000000',  '2021-11-19 08:00:00.000000', false, 'Гастроном', 'Гастрономский Городовой', false,
        false, 'комментарий 3', false, 'Я');
SELECT setval('production_targets_id_seq', max(id))
FROM production_targets