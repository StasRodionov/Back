CREATE TABLE IF NOT EXISTS online_store (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255),
    type VARCHAR(128),
    orders VARCHAR(255),
    remains VARCHAR(255),

    PRIMARY KEY(id)
);

INSERT INTO online_store (id, name, type, orders, remains) VALUES
    (1, '1С-Битрикс', 'Системный', 'Товары1', 'Остатки1'),
    (2, 'Deal.by', 'Системный', 'Товары2', 'Остатки2'),
    (3, 'Netcat', 'Системный', 'Товары3', 'Остатки3');

SELECT setval('online_store_id_seq', max(id))
FROM online_store



