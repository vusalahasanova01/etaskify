create table IF NOT EXISTS organization
(
    id           int auto_increment,
    name         varchar(255) not null,
    phone_number varchar(20)  not null,
    address      varchar(255) not null,
    constraint organization_pk
        primary key (id)
);

