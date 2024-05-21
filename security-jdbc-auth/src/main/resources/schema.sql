drop table if exists users;
drop table if exists authorities;

create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null
);

create unique index ix_auth_username on authorities (username, authority);