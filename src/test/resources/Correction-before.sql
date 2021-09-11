TRUNCATE corrections CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE corrections_correction_products CASCADE;
/*ALTER TABLE corrections DROP CONSTRAINT IF EXISTS fk6ywb8bc8s80xemuaqxbrfb8o7;
ALTER TABLE corrections DROP CONSTRAINT IF EXISTS fkaeuhfctrdv2do40pknq8jrewx;*/
INSERT INTO companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
VALUES (1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
        'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
        'chiefTestAccount', 'stampTest');

INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product, company_id, warehouse_id)
VALUES (1, 'Оприходование 1', '2021-06-23 15:10', false, false, false, 1, 1),
       (2, 'Оприходование 2', '2021-06-23 15:10', false, false, false, 1, 1),
       (3, 'Оприходование 3', '2021-06-23 15:10', false, false, false, 1, 1);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);

SELECT setval('corrections_id_seq', max(id))
FROM corrections;

