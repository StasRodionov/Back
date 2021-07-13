delete from retail_stores;
delete from invoice;
delete from inventarizations_inventarization_products;
delete from inventarizations;
delete from corrections_correction_products;
delete from corrections;
delete from return_suppliers;
delete from payments;
delete from contracts;
delete from companies_bank_accounts;
delete from balance_adjustments;
delete from companies;

insert into companies(id, name, inn, sort_number, phone, fax, email, payer_vat, comment_to_address, leader,
                      leader_manager_position, leader_signature, chief_accountant, chief_accountant_signature,
                      stamp)
values

(1, 'OOO Тест №1', '1234', '0001', '+79436527610', '810-41-1234567823', 'veraogon@mail.ru', true,
 'something comment', 'testLeader', 'testLeaderMeneger', 'testLeaderSignature', 'chiefTest',
 'chiefTestAccount', 'stampTest'),
(2, 'OOO Тест №2', '4321', '0002', '+76572518965', '810-41-1234567824', 'karimogon@mail.ru', true,
 'comment', 'Leader', 'testLeaderMeneger', 'LeaderSignature', 'chief', 'chiefAccount', 'stamp');