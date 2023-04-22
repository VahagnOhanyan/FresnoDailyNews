create table newsduplicatestats
(
    id BIGINT primary key not null AUTO_INCREMENT,
    text BLOB,
    text2  BLOB,
    score INTEGER
);
