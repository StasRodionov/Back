CREATE TABLE IF NOT EXISTS import (
    id BIGSERIAL NOT NULL,
    task VARCHAR(255),
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    status VARCHAR(255),

    PRIMARY KEY (id)
);

INSERT INTO import (id, task, start_date, end_date, status) VALUES
    (1, 'Товары и остатки', current_timestamp, current_timestamp, 'Задача завершена'),
    (2, 'Товары', current_timestamp, current_timestamp, 'Задача завершена'),
    (3, 'Контрагенты', current_timestamp, current_timestamp, 'Задача завершена'),
    (4, 'Банковская выписка', current_timestamp, current_timestamp, 'Задача завершена');