create table products_buy (
    bag_id bigserial not null  references bag(id),
    product_id bigserial not null references product(id),
    primary key (bag_id, product_id)
    );