TRUNCATE bank_accounts CASCADE;


delete from bank_accounts;

insert into bank_accounts (id, rcbic, bank, address, correspondent_account, account, main_account, sort_number) values
(5, 'rbic1', 'bank1', 'address1', 'correspondentAccount1', 'account1', true, 'sortNumber1'),
(2, 'rbic2', 'bank2', 'address2', 'correspondentAccount2', 'account2', true, 'sortNumber2'),
(3, 'rbic3', 'bank3', 'address3', 'correspondentAccount3', 'account3', true, 'sortNumber3'),
(4, 'rbic2', 'bank4', 'address4', 'correspondentAccount4', 'account4', true, 'sortNumber4')


