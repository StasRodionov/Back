ALTER TABLE price_lists ADD COLUMN is_spend BOOLEAN DEFAULT false;

ALTER TABLE price_lists ADD COLUMN is_recyclebin BOOLEAN DEFAULT false;

ALTER TABLE price_lists ADD COLUMN type_of_price_id bigint NOT NULL DEFAULT 1;




