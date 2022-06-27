ALTER TABLE product_groups
    ADD COLUMN description varchar(255),
    ADD COLUMN sale_tax varchar(255),
    ADD COLUMN tax_system_id int8,
    ADD COLUMN employee_id int8,
    ADD COLUMN department_id int8;
