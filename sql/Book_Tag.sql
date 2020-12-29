create table book_tag(--?ƒœê·? ê´?ë¦¬í•˜?Š”?…Œ?´ë¸?. ?•´?‹¹?˜?Š” ?¥ë¥´ë¼ë©?1, ?•„?‹ˆ?¼ë©? 0?´ ?‚½?…?œ?‹¤.
    book_id number primary key,         --ì±…ì‹ë³„ë²ˆ?˜¸
    book_name varchar2(30),               --ì±? ?´ë¦?
    genre_poet number not null,         --?‹œ
    genre_essay number not null,        --?—?„¸?´
    genre_selfDev number not null,      --?ê¸°ê³„ë°?
    genre_history number not null,         --?—­?‚¬
    genre_science number not null,          --ê³¼í•™    
    genre_novel NUMBER not null,             --?†Œ?„¤
    genre_comics number not null,            --ë§Œí™”
    genre_art number not null               --?˜ˆ?ˆ 
    );
    
commit;
    
drop table tag purge;
select * from book_tag;

--book ?…Œ?´ë¸”ê³¼ ì¡°ì¸?•˜?—¬ ?ƒœê·? ?…Œ?´ë¸”ì„ ? ?ˆ˜ ?‚´ë¦¼ì°¨?ˆœ?œ¼ë¡? ? •? ¬?•œê²?.
select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc;
    
--?œ„?˜ ?…Œ?´ë¸”ì„ ? ?ˆ˜?†’???ˆœì°¨ë¡œ ?ë¥¸ê²ƒ
select * from
    (select rownum rn, tt. *from
        (select book_tag.* , book.grade
            from book_tag
            inner join book
            on book_tag.book_id = book.book_id
            order by book.grade desc) tt)
where rn>=1 and rn<=100;


--?•˜?‚˜?˜ ?¥ë¥´ì— ?•´?‹¹?•˜?Š” ì±…ë“¤?˜ ? •ë³´ë§Œ ê°?? ¸?˜¤ê¸?
select * from 
(select  book.*,book_tag.genre_art
from book_tag
    inner join book
    on book_tag.book_id = book.book_id
    order by book.grade desc)
    where genre_art=1;

--?•˜?‚˜?˜ ?¥ë¥´ì— ?•´?‹¹?•˜?Š” ì±…ë“¤?„ ?¸?±?Š¤ë³„ë¡œ ?ë¥¸ê²ƒ
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
    