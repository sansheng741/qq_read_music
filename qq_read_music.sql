--------------------------------------------------------------------------------
----qq
---------创建序列
create sequence seq_qid start with 100001 increment by 1;
create sequence seq_rsid start with 1 increment by 1;
create sequence seq_qcid start with 1 increment by 1;
update qq set flag=1

select * from qq for update;
create table qq(   --个人信息表
       qid varchar2(32) primary key,-- qq号
       pwd varchar2(32) not null,--密码
       qimage blob ,-- 头像
       qname varchar2(50) not null,-- 网名
       motto varchar2(86),-- 签名
       email varchar2(25),--电子邮箱
       age int default 0,--年龄
       sex varchar2(4) default '男' constraint CK_sex check( sex in('男','女')),-- 性别
       birthday varchar2(20) default '1900-1-1',--生日
       adrr varchar2(64) default '不详',-- 地址
       flag int default 1-- 标记此账号是否为在电脑上面登录了
);

select * from qfriend for update;
create sequence seq_qfid start with 1 increment by 1;

drop table qfriend
create table qfriend(  --好友表
      -- 添加好友  如果对方同意 就更新双方的数据库
      qfid int primary key,
      qid1 varchar2(32) constraint FK_qfriend_qid1 references qq(qid), --登陆的本账号的 qq号
      groupname varchar2(32),-- 好友分组
      qid2 varchar2(32) constraint FK_qfriend_qid2 references qq(qid),-- 好友的qq 号
      beizu varchar2(16)-- 备注
);

select * from getfriend for update;
create table getfriend(     -- 好友申请表
      qid1 varchar2(32) constraint FK_getfriend_qid1 references qq(qid),
      qid2 varchar2(32) constraint FK_getfriend_qid2 references qq(qid),
      groupname varchar2(32),-- 好友分组
      liyou varchar2(64)
);

select * from qcontent for update;
drop table qcontent
create table qcontent( -- 会话表
      qcid int primary key,
      qid1 varchar2(32) constraint FK_qcontent_qid1 references qq(qid),
      qid2 varchar2(32) constraint FK_qcontent_qid2 references qq(qid),
      text varchar2(2000),-- 发送的内容   
      qdate date,
      flag int  default 1
);

select * from shuoshuo for update;
create table shuoshuo(  --说说表
      qid varchar2(32) constraint FK_shuoshuo_qid references qq(qid),
      msg varchar2(86),--发表说说的内容
      qimage blob,
      qtime date
);

select * from registers for update;
create table registers(  -- 注册表
      rsid int primary key,
      qid varchar2(32) not null unique,
      pwd varchar2(32) not null
);

-- 删除表
drop table qq;
drop table qfriend;
drop table getfriend;
drop table qcontent;
drop table shuoshuo;
drop table registers;

--删除序列
drop sequence seq_qid;
drop sequence seq_rsid;

--------------------------------------------------------------------------------
----qq阅读
--创建序列
create sequence seq_bid start with 10001 increment by 1;
create sequence seq_reid start with 1 increment by 1;

select * from librarybooks for update;
create table librarybooks(--书籍表
       bid varchar2(40) primary key,--书本编号
       typename varchar2(40) not null constraint FK_typename references  type(typename),--类别
       picture blob default null,--封面
       bname varchar2(80) not null,--书名
       author varchar2(40) not null,--作者
       pub varchar2(40) not null,--出版社
       bprice number not null,--价格
       bdate date default null--出版日期
);

select * from type for update;
create table type(  --类型表
       typename varchar2(40) primary key--类型名字    
);

select * from blend for update;
create table  blend( --收藏表
       bid varchar2(40) constraint FK_blend_bid references librarybooks(bid),--书本编号
       qid varchar2(32) constraint FK_blend_qid references qq(qid)--读者编号
);
------------添加书籍
insert into librarybooks values(seq_bid.nextval,'小说',null,'斗罗大陆','唐家三少','xx出版社',63,to_date('20180813','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'小说',null,'斗破苍穹','天蚕土豆','北京出版社',50,to_date('20180823','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'小说',null,'挪威的森林','村上春树','湖南出版社',56,to_date('20180823','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'小说',null,'解忧杂货店','东野圭吾','湖南出版社',43,to_date('20180801','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'历史',null,'三国志','陈寿','湖南出版社',60,to_date('20180803','yyyy-mm-dd'));                                                                                                    
insert into librarybooks values(seq_bid.nextval,'历史',null,'中国上下五千年','章祺',' 北京斯坦威图书有限责任公司',43,to_date('20180801','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'计算机',null,'C程序设计','谭浩强','清华大学出版社',33,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'计算机',null,'C++面向对象程序设计','谭浩强','清华大学出版社',34.5,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'计算机',null,'算法竞赛入门经典','刘汝佳','清华大学出版社',49,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'军事',null,'孙子兵法','孙膑','北京出版社',36,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'外语',null,'美国大城市的死与生','简·雅各布斯','Randon House Trade Publishing',34.5,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'文学',null,'红楼梦','曹雪芹','天津出版社',53,to_date('20180803','yyyy-mm-dd'));
------------添加分类
insert into type values('小说');
insert into type values('历史');
insert into type values('计算机');
insert into type values('军事');
insert into type values('外语');
insert into type values('文学');

-- 删除表
drop table librarybooks;
drop table type;
drop table blend;

--删除序列
drop sequence seq_bid;

--------------------------------------------------------------------------------
----qq音乐

----创建序列
create sequence seq_mid start with 1 increment by 1;
create sequence seq_cid start with 1 increment by 1;
--1.用户歌单

select * from list for update;
create table list(
   lname varchar2(50),          --1.歌单名
   qid varchar2(32) constraint FK_list_qid references qq(qid), --2.qid   关联qq,获得用户信息
   mid int default '' constraint FK_list_mid references music(mid)
);

select * from music for update;
--2.歌单内信息/音乐信息

create table music(
   mid int primary key not null,          --1.歌序号
   msong varchar2(200) not null,          --2.歌名
   msinger varchar2(200) not null,        --3.歌手
   malbum varchar2(200) not null,         --4.专辑 
   mtime varchar2(500) not null,          --5.时长
   msize varchar2(500) not null,          --6.大小
   mmv varchar2(200) default'',           --7.MV
   mmp3 varchar2(200) default'',          --8.MP3
   cid int default '' constraint FK_music_cid references classify(cid) 
);

select * from classify for update;
--3.歌曲分类
create table classify(
   cid int primary key,                       --cid
   cname varchar2(20) not null,            --分类名字 
   cimage blob default ''
)


-----------------删除表
drop table music;
drop table list;
drop table classify;

-----------------删除序列
drop sequence seq_mid;
drop sequence seq_cid;
