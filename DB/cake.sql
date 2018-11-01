create table cake
(
	id int(64) not null primary key,
	name varchar(100) null comment '蛋糕名称',
	category varchar(50) null comment '分类',
	price double(10,2) default '0.00' null comment '价格',
	img varchar(200) null comment '图片地址',
	banner int(5) default '0' null
)
comment '产品表';

create table user
(
	id       varchar(64)  not null
		primary key,
	name     varchar(100) null,
	age      int          null,
	username varchar(150) null,
	password varchar(200) null,
	`group`  varchar(40)  null,
	gender   varchar(20)  null,
	email    varchar(140) null,
	phone    varchar(100) null
)
	comment '用户表';

