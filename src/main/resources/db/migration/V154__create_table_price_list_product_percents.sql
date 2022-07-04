create table price_lists_percents
(
    id bigserial NOT NULL,
    name varchar(255) NOT NULL,
    percent numeric(19,2) DEFAULT 0,
    price_list_id BIGINT NOT NULL ,
    CONSTRAINT price_lists_percent_pkey PRIMARY KEY (id)
);

ALTER TABLE price_lists_percents
    ADD CONSTRAINT FK_PRICE_LIST_PRODUCT_ID
        FOREIGN KEY (price_list_id) REFERENCES price_lists (id);

INSERT INTO price_lists_percents(name, price_list_id)
VALUES ('колонка1',1), ('колонка1', 2), ('колонка1', 3), ('колонка1', 4), ('колонка1', 5);