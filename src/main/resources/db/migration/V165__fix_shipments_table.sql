ALTER TABLE shipments ADD COLUMN IF NOT EXISTS sales_channel_id INT8;

ALTER TABLE IF EXISTS shipments
    ADD CONSTRAINT fkyukj31nmv79yzxvypjj712nxq FOREIGN KEY (sales_channel_id) REFERENCES sales_channel
    ON DELETE SET NULL;