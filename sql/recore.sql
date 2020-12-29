
create table record (               -- 타임라인 기록 테이블
    book_id number,                -- 책 ID
    member_id varchar2(30),        -- 회원 ID
    book_name varchar2(30),   -- 책 이름
    --record_year varchar2(30),   --1 년 독서목표량 얘네는 회원관련이니까 회원테이블에 가는게 맞을듯
    -- record_month varchar2(10),  --월별독서목표량
    start_time date,       --독서시작시간
    end_time date          --독서 끝난시간 
);



insert into record values(6,'minju','맥심6',to_date('20201123123021','YYYYMMDDHH24MISS'),to_date(20201123140000,'YYYYMMDDHH24MISS'));
select * from record;