CREATE TABLE type
(
    type_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT type_pkey PRIMARY KEY (type_id),
    CONSTRAINT type_name_key UNIQUE (name)
);
