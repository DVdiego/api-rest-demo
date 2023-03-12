drop table  IF EXISTS PRICES;
create table PRICES (
    id bigint,
    brand_id integer,
    start_date timestamp,
    end_date timestamp,
	price_list integer,
	product_id bigint,
	priority integer,
    price numeric(10,2),
    curr varchar(255),
    primary key (id)
);