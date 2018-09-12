create table quantitys (
    bag_id int8  not null  references bag(id),
    product_id int8  not null references product(id),
    count int4 ,
    primary key (bag_id, product_id)
);