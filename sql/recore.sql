
create table record (               -- Ÿ�Ӷ��� ��� ���̺�
    book_id number,                -- å ID
    member_id varchar2(30),        -- ȸ�� ID
    book_name varchar2(30),   -- å �̸�
    --record_year varchar2(30),   --1 �� ������ǥ�� ��״� ȸ�������̴ϱ� ȸ�����̺� ���°� ������
    -- record_month varchar2(10),  --����������ǥ��
    start_time date,       --�������۽ð�
    end_time date          --���� �����ð� 
);



insert into record values(6,'minju','�ƽ�6',to_date('20201123123021','YYYYMMDDHH24MISS'),to_date(20201123140000,'YYYYMMDDHH24MISS'));
select * from record;