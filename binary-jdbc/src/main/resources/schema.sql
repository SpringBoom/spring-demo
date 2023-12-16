DROP TABLE t_menu IF EXISTS;

CREATE TABLE t_menu (
    id bigint auto_increment,
    name varchar(128),
    size varchar(16),
    price bigint,
    create_time TIMESTAMP,
    update_time TIMESTAMP,

    PRIMARY KEY (id)
)