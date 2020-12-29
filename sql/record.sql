--북아이디와 맴버아이디를 묶어서 키로 설정했다.
create table record (               -- 타임라인 기록 테이블
    book_id number,                -- 책 ID
    member_id varchar2(30),        -- 회원 ID
    book_name varchar2(30),   -- 책 이름
    start_time date,       --독서시작시간
    end_time date,          --독서 끝난시간 
    isreading number
);

drop table record purge;

select book_name,book_summary from book;

commit;
rollback;

insert into record values(6,'minju','맥심6',to_date('20201123123021','YYYYMMDDHH24MISS'),to_date(20201123140000,'YYYYMMDDHH24MISS'),0);
insert into record values(5,'minju','맥심5',to_date('20201103123021','YYYYMMDDHH24MISS'),to_date(20201105140000,'YYYYMMDDHH24MISS'),0);
insert into record values(15,'minju','자기계발서4',to_date('20201109123021','YYYYMMDDHH24MISS'),to_date(20201112140000,'YYYYMMDDHH24MISS'),0);
insert into record values(7,'minju','역사1',to_date('20201023123021','YYYYMMDDHH24MISS'),to_date(20201023140000,'YYYYMMDDHH24MISS'),0);
insert into record values(10,'minju','역사4',to_date('20201024123021','YYYYMMDDHH24MISS'),null,1);

select * from record;
commit;
update record set start_time = (to_date('2020-11-11','YYYY-MM-DD')) , end_time= (to_date('20201112','YYYY-MM-DD')) where book_id=8 and member_id = 'minju';
 --해당하는 월안에 있는데이터만 가져오기
 select * from 
    (select * from record
    where end_time between to_date(20201101000000,'YYYYMMDDHH24MISS') and to_date(20201201235959,'YYYYMMDDHH24MISS'))
    where member_id='minju';

--처음에 책일기 시작 눌렀을때
insert into record values(22,'minju','원피스 94권',sysdate,null,1);
--나중에 책 다읽고 다읽음 버튼 눌렀을때
update record set end_time=sysdate,isreading=0 where member_id = 'minju' and book_id=10;

commit;

select * from 
    		(select * from record
    			where end_time between to_date(20201101000000,'YYYYMMDDHH24MISS') 
    			and to_date(20201130235959,'YYYYMMDDHH24MISS') or isreading=1)
			where member_id='minju';


alter table record add isreading number default 0;

select record.*,book.book_img from record inner join book
        	on record.book_name = book.book_name
        where member_id = 'minju' 
        ORDER BY  record.isreading desc , record.end_time desc;
        
    --alter table member add( member_goal number DEFAULT 0)
select isreading from record where member_id='minju' and book_id=5;

alter table record add primary key(member_id,book_id);

    create table rated(
        member_id varchar2(30),
        book_id varchar2(30),
        book_grade varchar2(20)
        );
        
alter table rated add primary key(member_id,book_id);

select view_book.*,
    book_tag.genre_art,book_tag.genre_comics,
    book_tag.genre_essay,book_tag.genre_history,
    book_tag.genre_novel,book_tag.genre_poet,
    book_tag.genre_science,book_tag.genre_selfdev
    from view_book
    inner join book_tag
    on view_book.book_id = book_tag.book_id
    where book_tag.genre_art = 1;
    
SELECT * from record;
delete record where book_id = 3;
commit;

select book.*,book_grade.*,record.member_id
from book
    join book_grade 
    on book.book_id = book_grade.book_id
    join record
    on book.book_id=record.book_id
    where member_id = 'minju';
