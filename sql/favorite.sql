--���� å ��Ͽ� �� ���̺� �����̺�� �ɹ����̺� ������ �����ؼ� ����.
alter table favorite add primary key(member_id,book_id);
-- �ɹ�&�Ͼ��̵� ��� Ű����

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