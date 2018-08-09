create table products_buy (
    bag_id bigint not null auto_increment references bag,
    product_id bigint not null references product,
    primary key (bag_id, product_id)
    ) engine=MyISAM;