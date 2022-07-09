ALTER TABLE IF EXISTS invoice
ADD COLUMN IF NOT EXISTS project_id INT8 REFERENCES projects (id);

ALTER TABLE IF EXISTS invoice
ADD CONSTRAINT FK_PROJECT_ON_INVOICE
    FOREIGN KEY (project_id) REFERENCES projects (id)
    ON DELETE SET NULL;

UPDATE invoice SET project_id = 1 WHERE id = 31;
UPDATE invoice SET project_id = 2 WHERE id = 30;
UPDATE invoice SET project_id = 3 WHERE id = 32;

