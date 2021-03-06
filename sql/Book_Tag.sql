create table book_tag(--?κ·? κ΄?λ¦¬ν???΄λΈ?. ?΄?Ή?? ?₯λ₯΄λΌλ©?1, ???Όλ©? 0?΄ ?½???€.
    book_id number primary key,         --μ±μλ³λ²?Έ
    book_name varchar2(30),               --μ±? ?΄λ¦?
    genre_poet number not null,         --?
    genre_essay number not null,        --??Έ?΄
    genre_selfDev number not null,      --?κΈ°κ³λ°?
    genre_history number not null,         --?­?¬
    genre_science number not null,          --κ³Όν    
    genre_novel NUMBER not null,             --??€
    genre_comics number not null,            --λ§ν
    genre_art number not null               --?? 
    );
    
commit;
    
drop table tag purge;
select * from book_tag;

--book ??΄λΈκ³Ό μ‘°μΈ??¬ ?κ·? ??΄λΈμ ? ? ?΄λ¦Όμ°¨??Όλ‘? ? ? ¬?κ²?.
select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc;
    
--?? ??΄λΈμ ? ?????μ°¨λ‘ ?λ₯Έκ²
select * from
    (select rownum rn, tt. *from
        (select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc) tt)
where rn>=1 and rn<=100;


--??? ?₯λ₯΄μ ?΄?Ή?? μ±λ€? ? λ³΄λ§ κ°?? Έ?€κΈ?
select * from 
(select  book.*,book_tag.genre_art
from book_tag
    inner join book
    on book_tag.book_id = book.book_id
    order by book.grade desc)
    where genre_art=1;

--??? ?₯λ₯΄μ ?΄?Ή?? μ±λ€? ?Έ?±?€λ³λ‘ ?λ₯Έκ²
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
    