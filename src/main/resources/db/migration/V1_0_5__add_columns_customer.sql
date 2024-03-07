alter table customer add column employee_id int null;
alter table customer add column status int default 0 not null;
alter table customer add column deleted_by int null;
alter table customer add column deleted_time timestamp null;
alter table customer add column created_by int null;
alter table customer add column created_time timestamp null;
alter table customer add column updated_by int null;
alter table customer add column updated_time timestamp null;