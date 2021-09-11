TRUNCATE agent_reports CASCADE;
TRUNCATE companies CASCADE;
TRUNCATE contractors CASCADE;

ALTER TABLE companies DROP CONSTRAINT IF EXISTS fkmi4ikf6y17wva3x4fl9jwqst8;
ALTER TABLE agent_reports DROP CONSTRAINT IF EXISTS fkogqngsc7r5gprd176vueh4ugj;
ALTER TABLE agent_reports DROP CONSTRAINT IF EXISTS fkm7q0fhb6lyqq5otu8u2mogb8a;
ALTER TABLE contractors DROP CONSTRAINT IF EXISTS fkeuquohches3x842tjbco3cv8w;


INSERT INTO companies (id, chief_accountant, chief_accountant_signature, comment_to_address, email, fax, inn,
                       leader, leader_manager_position, leader_signature, name, payer_vat, phone, sort_number,
                       stamp, address_id, legal_detail_id)
VALUES (1, 'Сергеев Петр Сергеевич', 'chief signature', 'something comment', 'organization1@mail.com',
        '810-41-1234567890', '7712345000', 'Петров Сергей Петрович', 'Manager', 'leader signature',
        'OOO "Организация №1"', true, '749512345678', '00001', 'stamp', 1, 1),
       (2, 'Соболев Николай Андреевич', 'chief signature', 'something comment', 'organization2@mail.com',
        '920-12-2365723233', '9543564000', 'Иванова Мария Сергеевна', 'Executive director', 'leader signature',
        'OOO "Организация №2"', true, '733126789654', '00002', 'stamp', 2, 2),
       (3, 'Стрелецкая Анастасия Михайловна', 'chief signature', 'something comment', 'organization3@mail.com',
        '543-23-1234543221', '3453123000', 'Сергеева Ксения Андреевна', 'Project manager', 'leader signature',
        'OOO "Организация №3"', true, '799123786542', '00003', 'stamp', 3, 3);

INSERT INTO contractors (id, comment, comment_to_address, dicsount_card_number, email, fax, name, phone,
                         sort_number, access_parameters_id, address_id, contractor_group_id,
                         contractor_status_id, legal_detail_id, type_of_price_id)
VALUES (1, 'comment', '1 comment to address', '1234-5678-9012-3456', 'alena.pechnikova@x5.ru', '8 (495) 232-59-24',
        'Торговый Дом "Перекресток", ЗАО', '8 (495) 232-59-24', '1', 1, 1, 1, 1, 1, 1),
       (2, '2comment', '2comment to address', '7890-1234-5678-9012', 'inbox@5ka.ru', '8 (800) 555-55-05',
        'Агроаспект, ООО', '8 (800) 555-55-05', '2', 2, 2, 1, 2, 1, 2),
       (3, '3comment', '3comment to address', '3456-7890-1234-5678', 'info@izbenka.msk.ru', '8 (495) 981-13-45',
        'Вкусвилл, ООО', '8 (495) 981-13-45', '3', 3, 3, 1, 3, 1, 1);

INSERT INTO agent_reports (id, comitent_sum, commentary, document_type, number, paid, printed, remuniration_sum, sent,
                          status, sum, time, company_id, contractor_id)
VALUES (1, 1, 'Комментарий 1', '.doc', '1', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (2, 1, 'Комментарий 2', '.doc', '2', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (3, 1, 'Комментарий 3', '.doc', '3', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1);

SELECT setval('agent_reports_id_seq', max(id)) FROM agent_reports;