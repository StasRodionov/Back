drop table price_lists_products;

create table price_lists_products
(
    id bigserial NOT NULL,
    price numeric(19,2) NOT NULL DEFAULT 0,
    price_list_id BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    CONSTRAINT price_list_product_pkey PRIMARY KEY (id)
);

ALTER TABLE price_lists_products
    ADD CONSTRAINT FK_PRICE_LIST_ID
        FOREIGN KEY (price_list_id) REFERENCES price_lists (id);

ALTER TABLE price_lists_products
    ADD CONSTRAINT FK_PRODUCT_ID
        FOREIGN KEY (products_id) REFERENCES products (id);

INSERT INTO price_lists_products(price_list_id, products_id, price)
VALUES (1, 1, 123), (1, 2, 230), (1, 3, 133), (2, 4, 133), (2, 5, 133), (2, 6, 133),
       (3, 7, 280), (3, 8, 280), (3, 9, 280), (4, 10, 133), (4, 11, 133), (4, 12, 133);