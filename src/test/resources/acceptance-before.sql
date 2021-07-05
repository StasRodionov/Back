delete from acceptances;

insert into acceptances(id, incoming_number, comment, incoming_number_date, contractor_id, project_id, warehouse_id, contract_id) values

(1, 111, 'comment1', '2021-07-03', 11111, 111111, 1111111, 11111111),
(2, 222, 'comment2', '2021-07-03', 22222, 222222, 2222222, 22222222),
(3, 333, 'comment3', '2021-07-03', 33333, 333333, 3333333, 33333333);

SELECT setval('acceptance_id_seq', max(id)) FROM acceptances;
