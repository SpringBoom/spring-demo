insert into users (username, password, enabled) values ('LiLei',
                                                        '{bcrypt}$2a$10$iAty2GrJu9WfpksIen6qX.vczLmXlp.1q1OHBxWEX8BIldtwxHl3u', true);
insert into authorities (username, authority) values ('LiLei', 'READ_ORDER');
insert into authorities (username, authority) values ('LiLei', 'WRITE_ORDER');