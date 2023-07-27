CREATE TABLE brand
(
    brand_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT brand_pkey PRIMARY KEY (brand_id),
    CONSTRAINT brand_name_key UNIQUE (name)
);
