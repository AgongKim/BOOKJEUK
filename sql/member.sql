	create table member (                       -- 회원 DB
    member_nick varchar2(15),              -- 닉네임
    member_id varchar2(30) primary key,     -- 아이디
    member_pw varchar2(30) not null,    -- 비밀번호
    member_genre varchar2(300),            -- 좋아하는 장르
    member_photo varchar2(500),          -- 프사
    member_phone varchar2(30),            -- 폰번호
    member_goal number default 0
);

    alter table member add member_email varchar2(50);
    select * from member;
    insert into member values('퀸민주','test','1234','genre_art,genre_novel,genre_selfDev','test.jpg','01022224444',0);
    
    select * from member where member_id='minju' and member_pw = '1234';
    delete member where member_id = 'th3121';
    commit;
    select * from search where member_id = #{member_id} order by logtime desc;
    update member set member_email = 'james3121@naver.com' where member_id = 'minju';
    
--회원이 책평가한 기록을 담을 테이블 
create table member_grade(
    member_id varchar2(30),
    book_id number,
    graded varchar2(10)
    );
drop table member_grade purge;

create or replace view view_grade_member as
    select member_grade.member_id,member_grade.book_id,member_grade.graded,
            book_grade.book_good,book_grade.book_bad,book_grade.book_neut
    from book_grade
        left outer join member_grade on member_grade.book_id=book_grade.book_id;
        
select * from view_grade_member;

--둘이서 한쌍
insert into member_grade values('minju',1,'good');
update book_grade set book_good=book_good+1 where book_id=3 ;

--회원이 이미 체크 했는지 보기
select member_grade.member_id,member_grade.graded,book_grade.* from
    member_grade join book_grade
    on member_grade.book_id = book_grade.book_id
    where member_grade.member_id = 'minju' and member_grade.book_id=1;
    

alter table member_grade add primary key(member_id,book_id);
select * from member;
delete member where member_id = 'asdf321';
commit;