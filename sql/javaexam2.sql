

--팔로잉 하는 사람들의 리뷰 목록중 상위 5개 가져오기 
select rownum as rn,tt.* from (
    (select view_review.*,member_following.member_id as follower
        from view_review 
        join member_following on member_following.following = view_review.member_id
        where member_following.member_id = 'nodi'
        order by review_id desc ) tt)
where rownum>=1 and rownum<=5;

commit;
select * from book;
select * from view_review;

--독서모임 모집글 
create table group_board
