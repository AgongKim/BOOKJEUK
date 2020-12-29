drop table book purge;
drop table book_tag purge;
drop SEQUENCE seq_book;
commit;
alter table book modify book_summary varchar2(500);

-- book ���� ���̺�
create table book (
    book_id number primary key,              -- å ������ȣ
    book_name varchar2(50) not null,      -- å �̸�
    writer varchar2(50) not null,         -- �۰�
    publisher varchar2(50) not null,    -- ���ǻ�
    grade number not null,               -- ����
    book_summary varchar2(50) not null, -- å ���丮 ���
    book_link varchar2(500) not null,        -- å ���Ÿ�ũ
    book_img  varchar2(500) not null       -- å �̹���
);

create table book_tag(--�±� �����ϴ����̺�. �ش�Ǵ� �帣���1, �ƴ϶�� 0�� ���Եȴ�.
    book_id number primary key,         --å�ĺ���ȣ
    book_name varchar2(30),               --å �̸�
    genre_poet number not null,         --��
    genre_essay number not null,        --������
    genre_selfDev number not null,      --�ڱ���
    genre_history number not null,         --����
    genre_science number not null,          --����    
    genre_novel NUMBER not null,             --�Ҽ�
    genre_comics number not null,            --��ȭ
    genre_art number not null               --����
    );
	
	create table member (                       -- ȸ�� DB
    member_nick varchar2(15),              -- �г���
    member_id varchar2(30) primary key,     -- ���̵�
    member_pw varchar2(30) not null,    -- ��й�ȣ
    member_genre varchar2(300),            -- �����ϴ� �帣
    member_photo varchar2(500),          -- ����
    member_phone varchar2(30),            -- ����ȣ
    member_goal number default 0,
    member_email VARCHAR2(50)
);
drop table member purge;

create table search (                       -- �˻� DB
    member_id varchar2(30) not null,     -- ���̵�
    search varchar2(30) not null,   -- �˻��׸�
    logtime date
);


drop table record purge;
select * from member where member_id = 'th3121';

    --������ ��ü ����
    create sequence seq_book nocache nocycle;
    commit;
    
    
    
    insert into book values(seq_book.nextval,'å�̸�','������','���ǻ�',4.8,'�� å�� ���� ���ϴ�','op.gg');
    insert into member values('������','readking','1234','genre_art,genre_novel,genre_selfDev','profile.jpg','01022224444');
    insert into member values('������','minju','1234','genre_art,genre_novel,genre_selfDev','test.jpg','01022224444');
    select * from
				    (select rownum rn, tt. *from
				        (select * from 
				            (select  view_book.*,book_tag.genre_art
				                from book_tag
				                    inner join view_book
				                    on book_tag.book_id = view_book.book_id
				                    order by view_book.grade desc)
				                    where genre_art=1) tt)
				where rn>=1 and rn<=15;
    
    select record.*,book.book_img from record inner join book
        on record.book_name = book.book_name
        where member_id = 'minju';
    
    
    select max(book_id) as book_id from book;
        
    select * from book_tag;
    select * from book;
    select * from member;
    select book_img from book where book_id=2;
    
    ALTER table book add book_img varchar2(500);
    
    insert into book_tag values(22,'dfdfds',0,1,0,1,1,1,0,0);
    
    --alter table member drop column member_goal;
    --alter table member add( member_goal number DEFAULT 0)
    --commit;
    rollback;
    
    --�ɹ� ��ǥ�Ǽ� ���� 
    update member 
    set member_goal = 10 
    where member_id='minju';
    
    commit;
    delete book where book_id=21;