drop table book purge;
drop table book_tag purge;
drop SEQUENCE seq_book;
commit;
alter table book modify book_summary varchar2(500);

-- book 관리 테이블
create table book (
    book_id number primary key,              -- 책 고유번호
    book_name varchar2(50) not null,      -- 책 이름
    writer varchar2(50) not null,         -- 작가
    publisher varchar2(50) not null,    -- 출판사
    grade number not null,               -- 평점
    book_summary varchar2(50) not null, -- 책 스토리 요약
    book_link varchar2(500) not null,        -- 책 구매링크
    book_img  varchar2(500) not null       -- 책 이미지
);

create table book_tag(--태그 관리하는테이블. 해당되는 장르라면1, 아니라면 0이 삽입된다.
    book_id number primary key,         --책식별번호
    book_name varchar2(30),               --책 이름
    genre_poet number not null,         --시
    genre_essay number not null,        --에세이
    genre_selfDev number not null,      --자기계발
    genre_history number not null,         --역사
    genre_science number not null,          --과학    
    genre_novel NUMBER not null,             --소설
    genre_comics number not null,            --만화
    genre_art number not null               --예술
    );
	
	create table member (                       -- 회원 DB
    member_nick varchar2(15),              -- 닉네임
    member_id varchar2(30) primary key,     -- 아이디
    member_pw varchar2(30) not null,    -- 비밀번호
    member_genre varchar2(300),            -- 좋아하는 장르
    member_photo varchar2(500),          -- 프사
    member_phone varchar2(30),            -- 폰번호
    member_goal number default 0,
    member_email VARCHAR2(50)
);
drop table member purge;

create table search (                       -- 검색 DB
    member_id varchar2(30) not null,     -- 아이디
    search varchar2(30) not null,   -- 검색항목
    logtime date
);


drop table record purge;
select * from member where member_id = 'th3121';

    --시퀀스 객체 생성
    create sequence seq_book nocache nocycle;
    commit;
    
    
    
    insert into book values(seq_book.nextval,'책이름','김태현','출판사',4.8,'이 책은 존나 쩝니다','op.gg');
    insert into member values('독서왕','readking','1234','genre_art,genre_novel,genre_selfDev','profile.jpg','01022224444');
    insert into member values('퀸민주','minju','1234','genre_art,genre_novel,genre_selfDev','test.jpg','01022224444');
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
    
    --맴버 목표권수 설정 
    update member 
    set member_goal = 10 
    where member_id='minju';
    
    commit;
    delete book where book_id=21;