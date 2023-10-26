create table IF NOT EXISTS user_temp
(
    id       int auto_increment,
    username varchar(255)  not null,
    email    varchar(255)  not null,
    password varchar(1000) not null,
    otp_code varchar(4)    null,
    constraint user_temp_pk
        primary key (id),
    constraint user_temp_pk2
        unique (email, username)
);

