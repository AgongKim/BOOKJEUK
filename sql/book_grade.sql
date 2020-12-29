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

select * from book;

alter table book drop column grade;

create table book_grade (
    book_id number primary key,         --å���̵�
    book_good number default 0,     --��õ����
    book_neut number default 0,         --��Ÿ����
    book_bad number default 0           --��������
    );
    
    drop table book_grade purge;
    
    commit;

insert into book_grade values(1, 13,4,1);
insert into book_grade values(2, 12,4,2);
insert into book_grade values(3, 25,4,3);
insert into book_grade values(4, 18,4,5);
insert into book_grade values(5, 11,4,1);
insert into book_grade values(6, 13,4,1);
insert into book_grade values(7, 10,4,1);
insert into book_grade values(8, 9,4,1);
insert into book_grade values(9, 2,1,3);
insert into book_grade values(10, 1,0,1);
insert into book_grade values(11, 15,1,4);
insert into book_grade values(12, 2,4,8);
insert into book_grade values(13, 20,1,1);
insert into book_grade values(14, 5,4,2);      
insert into book_grade values(15, 52,22,10);      -- ���� ���� �����Ѵ�
insert into book_grade values(16, 7,5,1);
insert into book_grade values(22, 12,9,1);
insert into book_grade values(23, 10,27,1);

select * from book_grade;


create or replace view view_book as
    select book.*,(book_grade.book_good-book_grade.book_bad) as grade 
    from book
        inner join book_grade
        on book.book_id=book_grade.book_id;
        
select * from view_book;
a

