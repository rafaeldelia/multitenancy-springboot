-- public.tbl_product definition

-- Drop table

-- DROP TABLE public.tbl_product;

CREATE TABLE public.tbl_product (
	product_id int4 NOT NULL,
	product_name varchar(50) NOT NULL,
	quantity int4 NOT NULL DEFAULT 0,
	"size" varchar(3) NOT NULL,
	CONSTRAINT tbl_product_pkey PRIMARY KEY (product_id)
);

INSERT INTO public.tbl_product (product_id,product_name,quantity,"size") VALUES
	 (1,'Apple',1,'12');
