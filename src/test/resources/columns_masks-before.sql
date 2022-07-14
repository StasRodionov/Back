TRUNCATE columns_masks CASCADE;
TRUNCATE grids CASCADE;

INSERT INTO grids (id)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9);

INSERT INTO columns_masks (grid_id, employee_id, mask)
VALUES (1, 1, 2147483647),
       (2, 1, 2147483647),
       (3, 1, 2147483647),
       (4, 1, 2147483647);

SELECT * FROM grids;
SELECT * FROM columns_masks;