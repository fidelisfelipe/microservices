drop table exchange_value;
create table exchange_value
(
  id bigint auto_increment
    primary key,
  conversion_multiple decimal(10, 2),
  currency_from varchar(3) null,
  currency_to varchar(3) null,
  port varchar(10) null
);
drop table currency_type;
create table currency_type
(
  id bigint auto_increment
    primary key,
  name varchar(3) null
);

