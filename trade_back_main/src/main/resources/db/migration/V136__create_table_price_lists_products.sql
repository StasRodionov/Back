create table if not exists price_lists_products
(
    price_list_id BIGINT NOT NULL,
    products_id BIGINT NOT NULL
);

ALTER TABLE price_lists_products
    ADD CONSTRAINT FK_PRICE_LIST_ID
        FOREIGN KEY (price_list_id) REFERENCES price_lists (id);

ALTER TABLE price_lists_products
    ADD CONSTRAINT FK_PRODUCT_ID
        FOREIGN KEY (products_id) REFERENCES products (id);

INSERT INTO price_lists_products(price_list_id, products_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (2, 6), (3, 7), (3, 8), (3, 9), (4, 10), (4, 11), (4, 12);