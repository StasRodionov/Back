ALTER TABLE file
    ADD COLUMN kit_id int8;

CREATE TABLE kits_images
(
    kit_id int8 not null,
    images_id int8 not null
);

CREATE TABLE kits_product_prices
(
    kit_id int8 not null,
    product_prices_id int8 not null
);

CREATE TABLE kits_products
(
    kit_id int8 not null,
    products_id int8 not null
);

CREATE TABLE kits
(
    id int8 not null,
    name varchar(255),
    description varchar(255),
    archive boolean,
    weight numeric(19, 2),
    volume numeric(19, 2),
    purchase_price numeric(19, 2),
    unit_id int8,
    contractor_id int8,
    tax_system_id int8,
    product_group_id int8,
    item_number int4,
    country varchar(255),
    unique_code varchar(255),
    barcode varchar(255),
    external_code varchar(255),
    sale_tax varchar(255),
    indication_calculation varchar(255),
    marking varchar(255),
    employee_id int8,
    department_id int8,
    access_all boolean,
    additional_expenses numeric(19, 2),
    primary key (id)
);

INSERT INTO kits (
    id,
    name,
    description,
    archive,
    weight,
    volume,
    purchase_price,
    unit_id,
    contractor_id,
    tax_system_id,
    item_number,
    sale_tax,
    employee_id,
    department_id,
    access_all)
VALUES (1, 'Джентельменский набор №1', 'Костюм тройка, туфли, галстук', false, 0, 0, 33.33, 1, 1, 1, 4, '20', 1, 1, false ),
       (2, 'Джентельменский набор №2', 'Костюм двойка, ,туфли, бабочка', false, 0, 0, 44.44, 1, 1, 1, 5,  '20', 1, 1, false ),
       (3, 'Джентельменский набор №3', 'Пиджак, джинсы, ботинки', false, 0, 0, 55.55, 1, 1, 1, 6, '20', 1, 1, false );


