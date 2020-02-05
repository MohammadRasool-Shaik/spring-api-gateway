drop table product if exists;

create table product (product_id integer generated by default as identity (start with 1), pname varchar(50) not null, product_price double not null, product_type varchar(10) not null, primary key (product_id))

