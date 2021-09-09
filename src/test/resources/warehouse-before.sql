/*alter table invoice drop if exists warehouse_id;
alter table corrections drop if exists warehouse_id;
delete from warehouses;*/

/*alter table acceptances drop if exists warehouse_id;
alter table corrections drop if exists warehouse_id;
alter table internal_order drop if exists warehouse_id;
alter table inventarizations drop if exists warehouse_id;
alter table loss drop if exists warehouse_id;
alter table movement drop if exists warehouse_from_id;
alter table movement drop if exists warehouse_to_id;
alter table requsts_productions drop if exists warehouse_id;
alter table return_suppliers drop if exists warehouse_id;
alter table supplier_accounts drop if exists warehouse_id;
alter table technical_operations cle if exists warehouse_id;*/
TRUNCATE warehouses CASCADE;

insert into warehouses(id, name, sort_number, address, comment_to_address, comment) values
(1, 'name1', 'sort num 1', 'address1', 'comment to addr 1', 'comment1'),
(2, 'name2', 'sort num 2', 'address2', 'comment to addr 2', 'comment2'),
(3, 'name3', 'sort num 3', 'address3', 'comment to addr 3', 'comment3');

SELECT setval('warehouses_id_seq', max(id))
FROM warehouses;

