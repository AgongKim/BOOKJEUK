

--�ȷ��� �ϴ� ������� ���� ����� ���� 5�� �������� 
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

--�������� ������ 
create table group_board
