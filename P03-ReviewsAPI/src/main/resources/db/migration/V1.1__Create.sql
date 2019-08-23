/* create tables */
create table product
(
    product_id   int auto_increment,
    product_name varchar(300) not null,
    description varchar(500) not null,
    constraint order_pk primary key (product_id)
);

create table review
(
    review_id   int auto_increment,
    description varchar(500) not null,
    title varchar(200) not null,
    product_id  int          not null,
    constraint review_pk primary key (review_id),
    constraint review_fk foreign key (product_id)
        references product (product_id)
);

create table comment
(
    comment_id  int auto_increment,
    description varchar(1000) not null,
    title varchar(200) not null,
    review_id   int           not null,
    constraint comment_pk primary key (comment_id),
    constraint comment_fk foreign key (review_id)
        references review (review_id)
);
