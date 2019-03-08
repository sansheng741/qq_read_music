--------------------------------------------------------------------------------
----qq
---------��������
create sequence seq_qid start with 100001 increment by 1;
create sequence seq_rsid start with 1 increment by 1;
create sequence seq_qcid start with 1 increment by 1;
update qq set flag=1

select * from qq for update;
create table qq(   --������Ϣ��
       qid varchar2(32) primary key,-- qq��
       pwd varchar2(32) not null,--����
       qimage blob ,-- ͷ��
       qname varchar2(50) not null,-- ����
       motto varchar2(86),-- ǩ��
       email varchar2(25),--��������
       age int default 0,--����
       sex varchar2(4) default '��' constraint CK_sex check( sex in('��','Ů')),-- �Ա�
       birthday varchar2(20) default '1900-1-1',--����
       adrr varchar2(64) default '����',-- ��ַ
       flag int default 1-- ��Ǵ��˺��Ƿ�Ϊ�ڵ��������¼��
);

select * from qfriend for update;
create sequence seq_qfid start with 1 increment by 1;

drop table qfriend
create table qfriend(  --���ѱ�
      -- ��Ӻ���  ����Է�ͬ�� �͸���˫�������ݿ�
      qfid int primary key,
      qid1 varchar2(32) constraint FK_qfriend_qid1 references qq(qid), --��½�ı��˺ŵ� qq��
      groupname varchar2(32),-- ���ѷ���
      qid2 varchar2(32) constraint FK_qfriend_qid2 references qq(qid),-- ���ѵ�qq ��
      beizu varchar2(16)-- ��ע
);

select * from getfriend for update;
create table getfriend(     -- ���������
      qid1 varchar2(32) constraint FK_getfriend_qid1 references qq(qid),
      qid2 varchar2(32) constraint FK_getfriend_qid2 references qq(qid),
      groupname varchar2(32),-- ���ѷ���
      liyou varchar2(64)
);

select * from qcontent for update;
drop table qcontent
create table qcontent( -- �Ự��
      qcid int primary key,
      qid1 varchar2(32) constraint FK_qcontent_qid1 references qq(qid),
      qid2 varchar2(32) constraint FK_qcontent_qid2 references qq(qid),
      text varchar2(2000),-- ���͵�����   
      qdate date,
      flag int  default 1
);

select * from shuoshuo for update;
create table shuoshuo(  --˵˵��
      qid varchar2(32) constraint FK_shuoshuo_qid references qq(qid),
      msg varchar2(86),--����˵˵������
      qimage blob,
      qtime date
);

select * from registers for update;
create table registers(  -- ע���
      rsid int primary key,
      qid varchar2(32) not null unique,
      pwd varchar2(32) not null
);

-- ɾ����
drop table qq;
drop table qfriend;
drop table getfriend;
drop table qcontent;
drop table shuoshuo;
drop table registers;

--ɾ������
drop sequence seq_qid;
drop sequence seq_rsid;

--------------------------------------------------------------------------------
----qq�Ķ�
--��������
create sequence seq_bid start with 10001 increment by 1;
create sequence seq_reid start with 1 increment by 1;

select * from librarybooks for update;
create table librarybooks(--�鼮��
       bid varchar2(40) primary key,--�鱾���
       typename varchar2(40) not null constraint FK_typename references  type(typename),--���
       picture blob default null,--����
       bname varchar2(80) not null,--����
       author varchar2(40) not null,--����
       pub varchar2(40) not null,--������
       bprice number not null,--�۸�
       bdate date default null--��������
);

select * from type for update;
create table type(  --���ͱ�
       typename varchar2(40) primary key--��������    
);

select * from blend for update;
create table  blend( --�ղر�
       bid varchar2(40) constraint FK_blend_bid references librarybooks(bid),--�鱾���
       qid varchar2(32) constraint FK_blend_qid references qq(qid)--���߱��
);
------------����鼮
insert into librarybooks values(seq_bid.nextval,'С˵',null,'���޴�½','�Ƽ�����','xx������',63,to_date('20180813','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'С˵',null,'���Ʋ��','�������','����������',50,to_date('20180823','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'С˵',null,'Ų����ɭ��','���ϴ���','���ϳ�����',56,to_date('20180823','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'С˵',null,'�����ӻ���','��Ұ����','���ϳ�����',43,to_date('20180801','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'��ʷ',null,'����־','����','���ϳ�����',60,to_date('20180803','yyyy-mm-dd'));                                                                                                    
insert into librarybooks values(seq_bid.nextval,'��ʷ',null,'�й�������ǧ��','����',' ����˹̹��ͼ���������ι�˾',43,to_date('20180801','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'�����',null,'C�������','̷��ǿ','�廪��ѧ������',33,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'�����',null,'C++�������������','̷��ǿ','�廪��ѧ������',34.5,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'�����',null,'�㷨�������ž���','�����','�廪��ѧ������',49,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'����',null,'���ӱ���','����','����������',36,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'����',null,'��������е�������','���Ÿ���˹','Randon House Trade Publishing',34.5,to_date('20180803','yyyy-mm-dd'));
insert into librarybooks values(seq_bid.nextval,'��ѧ',null,'��¥��','��ѩ��','��������',53,to_date('20180803','yyyy-mm-dd'));
------------��ӷ���
insert into type values('С˵');
insert into type values('��ʷ');
insert into type values('�����');
insert into type values('����');
insert into type values('����');
insert into type values('��ѧ');

-- ɾ����
drop table librarybooks;
drop table type;
drop table blend;

--ɾ������
drop sequence seq_bid;

--------------------------------------------------------------------------------
----qq����

----��������
create sequence seq_mid start with 1 increment by 1;
create sequence seq_cid start with 1 increment by 1;
--1.�û��赥

select * from list for update;
create table list(
   lname varchar2(50),          --1.�赥��
   qid varchar2(32) constraint FK_list_qid references qq(qid), --2.qid   ����qq,����û���Ϣ
   mid int default '' constraint FK_list_mid references music(mid)
);

select * from music for update;
--2.�赥����Ϣ/������Ϣ

create table music(
   mid int primary key not null,          --1.�����
   msong varchar2(200) not null,          --2.����
   msinger varchar2(200) not null,        --3.����
   malbum varchar2(200) not null,         --4.ר�� 
   mtime varchar2(500) not null,          --5.ʱ��
   msize varchar2(500) not null,          --6.��С
   mmv varchar2(200) default'',           --7.MV
   mmp3 varchar2(200) default'',          --8.MP3
   cid int default '' constraint FK_music_cid references classify(cid) 
);

select * from classify for update;
--3.��������
create table classify(
   cid int primary key,                       --cid
   cname varchar2(20) not null,            --�������� 
   cimage blob default ''
)


-----------------ɾ����
drop table music;
drop table list;
drop table classify;

-----------------ɾ������
drop sequence seq_mid;
drop sequence seq_cid;
