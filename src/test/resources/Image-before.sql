TRUNCATE images CASCADE;

INSERT INTO images (id, image_url, sort_number)
VALUES (1, 'image_url1', 'sort_number1'),
       (2, 'image_url2', 'sort_number2'),
       (3, 'image_url3', 'sort_number3');

SELECT setval('images_id_seq', max(id))
FROM images;
