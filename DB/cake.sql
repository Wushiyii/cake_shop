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

create table cake_order
(
  id          varchar(64)  not null primary key,
  user_id     varchar(64)  null comment '创建订单用户ID',
  status      varchar(100) null comment '订单状态 0:待确认,1:已确认,2:取消',
  receive_status      varchar(100) null comment '送达状态 NOT_RECEIVED:待收货,DELIVERING:送货中,RECEIVED:已收货',
  sure_id     varchar(64)  null comment '确认人id',
  sure_user   varchar(100) null comment '确认人名称',
  sure_time   datetime     null comment '确认时间',
  create_user varchar(100) null comment '创建订单人名称',
  create_time datetime     null comment '创建订单时间',
  update_user    varchar(100) null,
  updateTime     datetime     null
) comment '订单表';


DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) DEFAULT NULL COMMENT '关联订单表ID',
  `cake_id` varchar(64) DEFAULT NULL COMMENT '关联cake表id',
  `quantity` int(10) DEFAULT NULL COMMENT '数量',
  `price` double(10,2) DEFAULT '0.00' COMMENT '总价',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单详情表';

create table cart
(
  id          varchar(64)  not null primary key,
  user_id     varchar(100) null
  comment '创建购物车用户id',
  create_user varchar(100) null
  comment '创建购物车用户',
  create_date datetime     null
  comment '创建购物车日期'
)
  comment '购物车表';

create table cart_detail
(
  id          varchar(64)     not null primary key,
  cart_id     varchar(64)     null
  comment '关联购物车表id',
  cake_id     varchar(64)     null
  comment '关联商品id',
  quantity    int default '0' null
  comment '数量',
  create_date datetime        null,
  update_date datetime        null
) comment '购物车详情表';
