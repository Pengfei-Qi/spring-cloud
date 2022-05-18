create database if not exists db_test;
use db_test;
drop table if exists company_tbl;
create table db_test.company_tbl (
                                     id int(11) not null auto_increment,
                                     name varchar(255) default null,
                                     address varchar(255) default null,
                                     count int(11)  ,
                                     db_source varchar(255) default null,
                                     primary key (id)
) engine=innodb default charset=utf8 ;
desc db_test.company_tbl;

truncate table db_test.company_tbl;

insert into db_test.company_tbl (name,address,count,db_source) values ('张三','河北联通',50,'联通机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('李四','江苏联通',30,'联通机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('王五','浙江联通',40,'联通机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('赵六','上海联通',99,'联通机房');

insert into db_test.company_tbl (name,address,count,db_source) values ('张三三','河北移动',36,'移动机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('李四四','江苏移动',78,'移动机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('王五五','浙江移动',77,'移动机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('赵六六','上海移动',75,'移动机房');

insert into db_test.company_tbl (name,address,count,db_source) values ('孙三三','河北电信',66,'电信机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('孙四四','江苏电信',68,'电信机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('孙五五','浙江电信',67,'电信机房');
insert into db_test.company_tbl (name,address,count,db_source) values ('孙六六','上海电信',65,'电信机房');