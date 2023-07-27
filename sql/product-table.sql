CREATE TABLE product
(
    product_id SERIAL NOT NULL,
    type_id INTEGER,
    brand_id INTEGER,
    model VARCHAR(100) NOT NULL,
    release DATE NOT NULL,
    price INTEGER NOT NULL,
    color_id INTEGER NOT NULL,
    vendor_code INTEGER NOT NULL,
    count_on_stock INTEGER,
    CONSTRAINT product_pkey PRIMARY KEY (vendor_code),
    CONSTRAINT product_brand_id_fkey FOREIGN KEY (brand_id)
        REFERENCES public.brand (brand_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT product_color_id_fkey FOREIGN KEY (color_id)
        REFERENCES public.colors (color_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT product_type_id_fkey FOREIGN KEY (type_id)
        REFERENCES public.type (type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
