CREATE TABLE IF NOT EXISTS task
(
    id          int auto_increment,
    title       varchar(255)   not null,
    description varchar(10000) not null,
    from_id     int            not null,
    to_id       int            not null,
    deadline    timestamp      null,
    status      int default 1  null,
    is_active   int default 1  not null,
    constraint task_pk
        primary key (id),
    constraint task_from_user_fk
        foreign key (from_id) references user (id),
    constraint task_to_user_fk
        foreign key (to_id) references user (id)
);