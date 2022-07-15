ALTER TABLE products RENAME COLUMN country TO country_id;
ALTER TABLE products ALTER COLUMN country_id TYPE BIGINT USING country_id::bigint;
ALTER TABLE products ADD CONSTRAINT country_id FOREIGN KEY (country_id) REFERENCES country (id);
