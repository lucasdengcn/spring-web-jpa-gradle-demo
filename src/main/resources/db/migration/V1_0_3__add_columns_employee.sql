alter table employee add column status int default 0 not null;
alter table employee add column deleted_by int null;
alter table employee add column deleted_time timestamp null;
