INSERT INTO SIMPLE_USER (create_at,name,uid,email,phone_number,family_id)
VALUES (now(), '테스터1','test1','test1@seaweed.com','010-0000-0000',1)
    , (now(),'테스터2','test2','test2@seaweed.com','010-0000-0001',1)
    , (now(),'테스터3','test3','test3@seaweed.com','010-0000-0002',2);

INSERT INTO SIMPLE_AUTH (create_at,login_id,password,user_id)
VALUES (now(), 'test1','6da4535039108c26c9d73f5f3c73f19d39c9134409dd01fe9953dd944442467f',1)
   , (now(),'test2','e67ca3cc5af4604dfb56b7008299a503095d74e50b5d6f3beccf541d5a6b29d5',2)
   , (now(),'test3','930c813cafe5adb6a5526ed22b26b5ef2da6a44c3bc1bb01485bea38c5cf475e',3);

INSERT INTO FAMILY (create_at,name)
VALUES (now(), 'spyFamily')
 , (now(),'testFamily');

INSERT INTO ITEM_CLASS (create_at, name,family_id, type)
VALUES (now(), '휴지', 1, '1')
        ,(now(), '물티슈', 1, '1')
        ,(now(), '침대', 1, '2')
        ,(now(), 'TV', 1, '3')
        ,(now(), '만두', 1, '4')
        ,(now(), '김치', 1, '4')
        ,(now(), '계란', 1, '4')
        ,(now(), '라면', 1, '4')
;

INSERT INTO ITEM (create_at, name,class_id, type,count)
VALUES (now(), '휴지', 1, '1', 5)
,(now(), '물티슈', 2, '1', 3)
,(now(), '일룸침대', 3, '2', 1)
,(now(), 'LG TV', 4, '2', 1)
,(now(), '만두', 5, '1', 7)
,(now(), '김치', 6, '1', 1)
,(now(), '계란', 7, '1', 12)
,(now(), '라면', 8, '1', 4)
;