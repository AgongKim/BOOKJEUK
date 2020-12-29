--찜한 책 목록에 쓸 테이블 북테이블과 맴버테이블 적절히 조인해서 쓴다.
alter table favorite add primary key(member_id,book_id);
-- 맴버&북아이디 묶어서 키설정

create table favorite(
    book_id number,
    member_id varchar2(30)
    );
    
commit;
select *from favorite;

select * from favorite where member_id='minju' and book_id=1;

insert into favorite values(10,'minju');

select book.*,favorite.member_id from book inner join favorite
    on book.book_id=favorite.book_id
    where favorite.member_id='minju';

delete from favorite where member_id='minju' and book_id=10;