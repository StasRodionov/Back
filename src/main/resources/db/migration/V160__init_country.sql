INSERT INTO country (id, type, short_name, full_name, digit_code, two_letter_code, three_letter_code)
VALUES (1, 'Системный', 'Албания', 'Республика Албания', '008', 'AL', 'ALB'),
       (2, 'Системный', 'Гайана', 'Республика Гайана', '328', 'GY', 'GUY'),
       (3, 'Системный', 'Тонга', 'Королевство Тонга', '776', 'TO', 'TON');
SELECT setval('country_id_seq', max(id))
FROM country