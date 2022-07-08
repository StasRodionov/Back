ALTER TABLE IF EXISTS return_suppliers
ADD COLUMN IF NOT EXISTS project_id INT8 REFERENCES projects (id);

ALTER TABLE IF EXISTS invoice
ADD CONSTRAINT FK_PROJECT_ON_RETURN_SUPPLIERS
    FOREIGN KEY (project_id) REFERENCES projects (id)
    ON DELETE SET NULL;

UPDATE return_suppliers SET project_id = 1 WHERE id = 2;
UPDATE return_suppliers SET project_id = 2 WHERE id = 1;
UPDATE return_suppliers SET project_id = 3 WHERE id = 3;

