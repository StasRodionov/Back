TRUNCATE discount CASCADE;

INSERT INTO discount (id, is_active, name, type, is_bonus_program, bonus_program_id)
VALUES (1, true, 'Бонусная программа 1', 'Бонусная программа', true, 1),
       (2, true, 'Бонусная программа 2', 'Бонусная программа', true, 1),
       (3, true, 'Бонусная программа 3', 'Бонусная программа', true, 1);
SELECT setval('discount_id_seq', max(id))
FROM discount;
