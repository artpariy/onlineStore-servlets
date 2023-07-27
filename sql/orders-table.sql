CREATE TABLE orders
(
    id BIGSERIAL,
    phone_number character varying(40) NOT NULL,
    address character varying(300) NOT NULL,
    user_login character varying(100) NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);
