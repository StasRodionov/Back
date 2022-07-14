ALTER TABLE invoice ADD COLUMN IF NOT EXISTS sales_channel_id INT8;

ALTER TABLE IF EXISTS invoice
    ADD CONSTRAINT fk_sales_channel_on_invoice FOREIGN KEY (sales_channel_id) REFERENCES sales_channel
    ON DELETE SET NULL;

ALTER TABLE purchase_control ADD COLUMN IF NOT EXISTS sales_channel_id INT8;