create table fresnodailynews.news
(
    id          bigint primary key AUTO_INCREMENT,
    url         varchar(2000),
    image_url   varchar(2000),
    domain      varchar(100),
    country     varchar(50),
    language    varchar(20),
    pub_date    timestamp,
    title       BLOB,
    description BLOB,
    content     BLOB,
    summary     BLOB,
    keywords    BLOB,
    is_published boolean


);

create table fresnodailynews.users
(
    telegram_id varchar(50)  primary key not null,
    first_name  varchar(500) not null,
    last_name   varchar(500) not null,
    username    varchar(100) not null

  );
create table fresnodailynews.verbs
(
    verb varchar(50) primary key not null,
    forms  BLOB
);