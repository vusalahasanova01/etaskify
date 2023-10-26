create table IF NOT EXISTS organization
(
    id           int auto_increment,
    name         int         not null,
    phone_number varchar(20) not null,
    address      int         not null,
    constraint organization_pk
        primary key (id)
);

