create table IF NOT EXISTS user
(
    id              int auto_increment,
    username        varchar(255)  not null,
    email           varchar(255)  not null,
    password        varchar(1000) not null,
    organization_id int           null,
    constraint user_pk
        primary key (id),
    constraint user_pk2
        unique (username, email),
    constraint user_organization_id_fk
        foreign key (organization_id) references organization (id)
);

