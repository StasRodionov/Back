TRUNCATE companies CASCADE;
TRUNCATE legal_details CASCADE;


insert into legal_details (id, comment_to_address, date_of_the_certificate, first_name, inn, kpp, last_name, middle_name, number_of_the_certificate, ogrn, okpo, address_id, type_of_contractor_id)
values  (1, 'comment to address', null, 'Михаил', '3664069397', '79271669', 'Иванов', 'Сергеевич', '432145', '236467', '1053600591197', 1, 1),
        (2, 'comment to address', null, 'Андрей', '3664069439', '34271669', 'Гордон', 'Анатольевич', '432145', '1053600591285', '79271647', 2, 2),
        (3, 'comment to address', null, 'Мария', '3664055588', '35259831', 'Сергеева', 'Дмитриевна', '342145', '1033600141277', '70713032', 3, 3);

insert into companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
values

(1, 'OOO ���� �1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
 'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
 'chiefTestAccount', 'stampTest'),
(2, 'OOO ���� �2', '4321', '0002', '+76572518965', '810-41-1234567824', 'karimogon@mail.ru', true,
 'comment', 'Leader', 'testLeaderMeneger', 'LeaderSignature', 'chief', 'chiefAccount', 'stamp');