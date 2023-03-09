drop table member;

create table member(
	id varchar(50) primary key,
	pwd varchar(50) not null,
	name varchar(50) not null,
	email varchar(50) unique, 
	auth int
);

--boolean조사
select id
from member
where id = 'aaa';

--숫자 조사
select count(*)
from member
where id ='aaa';

select * from member;