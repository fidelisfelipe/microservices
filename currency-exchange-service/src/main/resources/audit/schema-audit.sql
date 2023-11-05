create table history_conversion
(
  id bigint auto_increment
    primary key,
  date timestamp default CURRENT_TIMESTAMP not null
);
