CREATE TABLE orders_info
(
    id bigint,
    type_id integer NOT NULL,
    brand_id integer NOT NULL,
    product_model character varying(50) NOT NULL,
    release date NOT NULL,
    price integer NOT NULL,
    color_id integer NOT NULL,
    vendor_code integer NOT NULL,
    CONSTRAINT orders_info_id_fkey FOREIGN KEY (id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
