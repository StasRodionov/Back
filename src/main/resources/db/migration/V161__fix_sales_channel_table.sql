ALTER TABLE sales_channel ADD COLUMN IF NOT EXISTS general_access BOOlEAN DEFAULT FALSE;
ALTER TABLE sales_channel ADD COLUMN IF NOT EXISTS department_owner VARCHAR(255);
ALTER TABLE sales_channel ADD COLUMN IF NOT EXISTS employee_owner VARCHAR(255);
ALTER TABLE sales_channel ADD COLUMN IF NOT EXISTS date_of_change VARCHAR(255);
ALTER TABLE sales_channel ADD COLUMN IF NOT EXISTS employee_change VARCHAR(255);

UPDATE sales_channel SET date_of_change = ('2022-06-07 11:55:00') WHERE (id < 6)

