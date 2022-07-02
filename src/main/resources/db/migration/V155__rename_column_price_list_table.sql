ALTER TABLE price_lists RENAME COLUMN time TO date;
ALTER TABLE price_lists RENAME COLUMN sent TO is_sent;
ALTER TABLE price_lists RENAME COLUMN printed TO is_print;
ALTER TABLE price_lists RENAME COLUMN commentary TO comment;