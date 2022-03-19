TRUNCATE images cascade;
INSERT INTO images (id, image_url, sort_number)
VALUES (1, 'src/main/resources/file/male.ico', '1'),
       (2, 'src/main/resources/file/women.ico', '2'),
       (3, 'src/main/resources/file/women.ico', '3'),
       (4, 'src/main/resources/file/male.ico', '4'),
       (5, 'src/main/resources/file/women.ico', '5'),
       (6, 'src/main/resources/file/male.ico', '6');

TRUNCATE employees cascade;

INSERT INTO employees (id, description, email, first_name, inn, last_name, middle_name, password, phone, sort_number,
                       department_id, image_id, position_id)
VALUES (1, 'Some special text about Vasya', 'vasyaogon@mail.ru', 'Василий', '526317984689', 'Васильев', 'Васильевич',
        '12345', '+7(999)111-22-33', '1', 1, 1, 1),
       (2, 'Some special text about Sima', 'simaogon@mail.ru', 'Сима', '526317984678', 'Симонова', 'Семенова', '54321',
        '+7(999)222-11-33', '2', 2, 2, 2),
       (3, 'Some special text about Vera', 'veraogon@mail.ru', 'Вера', '526317555678', 'Белив', 'Анатольевна', '76543',
        '+7(999)777-11-33', '3', 5, 3, 5),
       (4, 'Some special text about Karim', 'karimogon@mail.ru', 'Карим', '526316666678', 'Ишлентев', 'Дмитриевич',
        'qwerty', '+7(999)222-77-00', '4', 4, 4, 4),
       (5, 'Some special text about Sasha', 'sashaogon@mail.ru', 'Александра', '526317984600', 'Петко', 'Петрович', 'asdfg',
        '+7(999)222-00-33', '5', 4, 5, 19),
       (6, 'Some special text about Oleg', 'olegogon@mail.ru', 'Олег', '526317974236', 'Петров', 'Иванович', 'terry',
        '+7(999)555-66-77', '6', 4, 6, 19);

TRUNCATE employees_roles cascade;

INSERT INTO employees_roles (employee_id, roles_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 1),
       (5, 2),
       (6, 2);