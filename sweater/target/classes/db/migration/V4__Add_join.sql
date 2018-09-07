create table products_buy (
    bag_id int8 not null  references bag(id),
    product_id int8 not null references product(id),
    primary key (bag_id, product_id)
    );