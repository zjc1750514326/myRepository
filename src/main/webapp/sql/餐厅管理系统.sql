/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/3 19:24:02                           */
/*==============================================================*/
create database zjc_hotle default charset utf8;

use zjc_hotle;


drop index table_order_r_FK on dtable;

drop table if exists dtable;

drop table if exists employee;

drop table if exists food;

drop table if exists orderdetail;

drop table if exists orders;

drop table if exists style;

/*==============================================================*/
/* Table: dtable                                                */
/*==============================================================*/
create table dtable
(
   id                   int not null,
   orde_id               int not null,
   tableName            varchar(20) not null,
   tableState           int not null,
   orderDate            datetime,
   primary key (id)
);

alter table dtable comment '²Í×À±àºÅ     id           Integer
²Í×ÀÃû³Æ     tableName      ';
/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   id                   int not null,
   empName              varchar(20) not null,
   empJob               varchar(20),
   empPs                int,
   primary key (id)
);

alter table employee comment 'Ô±¹¤±àºÅ           id           Integer
Ô±¹¤ÐÕÃû           emp';

/*==============================================================*/
/* Table: food                                                  */
/*==============================================================*/
create table food
(
   id                   int not null,
   sty_id               int not null,
   foodName             varchar(40) not null,
   foodPrice            double,
   foodVprice           double,
   foodRemark           varchar(200),
   foodImage            varchar(100),
   primary key (id)
);

alter table food comment '²Ëµ¥±àºÅ         id                       integer
²Ëµ¥Ãû³Æ    ';

/*==============================================================*/
/* Table: orderdetail                                           */
/*==============================================================*/
create table orderdetail
(
   detail_id            int not null,
   foo_id               int not null,
   ord_id               int not null,
   food_name            varchar(40),
   foodCount            int,
   primary key (detail_id)
);

alter table orderdetail comment '¶©µ¥ÏêÏ¸±àºÅ        id         Integer
¶©µ¥±àºÅ                o';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   id                   int not null,
   dta_id               int not null,
   detail_id            int,
   orderTotaleprice     double,
   orderDate            datetime,
   orderState           int,
   primary key (id)
);

alter table orders comment '¶©µ¥±àºÅ         id                   Integer
²Í×À±àºÅ        ';

/*==============================================================*/
/* Table: style                                                 */
/*==============================================================*/
create table style
(
   id                   int not null,
   styleName            varchar(20) not null,
   primary key (id)
);

alter table style comment '²ËÏµ±àºÅ       id             Integer
²ËÏµÃû³Æ       styleName';

alter table dtable add constraint FK_Relationship_2 foreign key (orde_id)
      references orders (id) on delete restrict on update restrict;

alter table food add constraint FK_Relationship_1 foreign key (sty_id)
      references style (id) on delete restrict on update restrict;

alter table orderdetail add constraint FK_Relationship_3 foreign key (ord_id)
      references orders (id) on delete restrict on update restrict;

alter table orderdetail add constraint FK_Relationship_4 foreign key (foo_id)
      references food (id) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_5 foreign key (detail_id)
      references orderdetail (detail_id) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_2 foreign key (dta_id)
      references dtable (id) on delete restrict on update restrict;

