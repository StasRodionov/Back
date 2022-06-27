CREATE TABLE IF NOT EXISTS sales_channel (
    id                  BIGSERIAL NOT NULL,
    name                VARCHAR(255),
    type                VARCHAR(255),
    description         VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO sales_channel (id, name, type, description)
VALUES
        (1, 'Telegram', 'Мессенджер', 'Видеоигры'),
        (2, 'VK', 'Социальная сеть', 'Реклама'),
        (3, 'Ozon', 'Маркетплейс', 'Товара для дома'),
        (4, 'Посуда-онлайн', 'Интернет-магазин', 'Посуда'),
        (5, 'Авито', 'Доска объявлений', 'Все товара и услуги'),
        (6, 'Автосалон', 'Прямые продажи', 'Автомобили');
