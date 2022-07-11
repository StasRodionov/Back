CREATE TABLE IF NOT EXISTS country (
    id BIGSERIAL NOT NULL,
    type VARCHAR(255),
    short_name VARCHAR(255),
    full_name VARCHAR(255),
    digit_code VARCHAR(255),
    two_letter_code VARCHAR(100),
    three_letter_code VARCHAR(100),

    PRIMARY KEY (id)
);



