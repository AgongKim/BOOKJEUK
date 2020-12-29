create table book_tag(--?���? �?리하?��?��?���?. ?��?��?��?�� ?��르라�?1, ?��?��?���? 0?�� ?��?��?��?��.
    book_id number primary key,         --책식별번?��
    book_name varchar2(30),               --�? ?���?
    genre_poet number not null,         --?��
    genre_essay number not null,        --?��?��?��
    genre_selfDev number not null,      --?��기계�?
    genre_history number not null,         --?��?��
    genre_science number not null,          --과학    
    genre_novel NUMBER not null,             --?��?��
    genre_comics number not null,            --만화
    genre_art number not null               --?��?��
    );
    
commit;
    
drop table tag purge;
select * from book_tag;

--book ?��?��블과 조인?��?�� ?���? ?��?��블을 ?��?�� ?��림차?��?���? ?��?��?���?.
select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc;
    
--?��?�� ?��?��블을 ?��?��?��???��차로 ?��른것
select * from
    (select rownum rn, tt. *from
        (select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc) tt)
where rn>=1 and rn<=100;


--?��?��?�� ?��르에 ?��?��?��?�� 책들?�� ?��보만 �??��?���?
select * from 
(select  book.*,book_tag.genre_art
from book_tag
    inner join book
    on book_tag.book_id = book.book_id
    order by book.grade desc)
    where genre_art=1;

--?��?��?�� ?��르에 ?��?��?��?�� 책들?�� ?��?��?��별로 ?��른것
select * from
    (select rownum rn, tt. *from
        (select * from 
            (select  book.*,book_tag.genre_art
                from book_tag
                    inner join book
                    on book_tag.book_id = book.book_id
                    order by book.grade desc)
                    where genre_art=1) tt)
where rn>=1 and rn<=100;

select book.*,book_tag.* 
    from book_tag
    inner join book
    on book.book_id = book_tag.book_id
    where book.book_id = 4;
    
    
select view_book.*,
    book_tag.genre_art,book_tag.genre_comics,
    book_tag.genre_essay,book_tag.genre_history,
    book_tag.genre_novel,book_tag.genre_poet,
    book_tag.genre_science,book_tag.genre_selfdev
    from view_book
    inner join book_tag
    on view_book.book_id = book_tag.book_id
    where book_tag.genre_art = 1;
    