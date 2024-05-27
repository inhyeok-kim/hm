INSERT INTO simple_user (create_at,name,uid,email,phone_number,family_id)
VALUES (now(), '테스터1','test1','test1@seaweed.com','010-0000-0000',1)
    , (now(),'테스터2','test2','test2@seaweed.com','010-0000-0001',1)
    , (now(),'테스터3','test3','test3@seaweed.com','010-0000-0002',2)
    , (now(),'테스터4','test4','test3@seaweed.com','010-0000-0002',0);

INSERT INTO simple_auth (create_at,login_id,password,user_id)
VALUES (now(), 'test1','6da4535039108c26c9d73f5f3c73f19d39c9134409dd01fe9953dd944442467f',1)
   , (now(),'test2','e67ca3cc5af4604dfb56b7008299a503095d74e50b5d6f3beccf541d5a6b29d5',2)
   , (now(),'test3','930c813cafe5adb6a5526ed22b26b5ef2da6a44c3bc1bb01485bea38c5cf475e',3)
   , (now(),'test4','930c813cafe5adb6a5526ed22b26b5ef2da6a44c3bc1bb01485bea38c5cf475e',4);

INSERT INTO family (create_at,name,invite_code)
VALUES (now(), 'spyFamily','155eacf3-59c9-37cc-a76d-7b74ecf78297')
 , (now(),'testFamily','8ea22333-2f38-42ce-ac19-89b5be5d69e6');

INSERT INTO family_join_req (create_at, family_id, user_id, status)
VALUES ( now(), 1, 4, 1 );


INSERT INTO item (create_at, name,family_id, type,cnt, class_type)
VALUES (now(), '휴지', 1, '1', 5,'2')
    ,(now(), '물티슈', 1, '1', 3,'2')
    ,(now(), '손톱깍이', 1, '2', 3,'1')
    ,(now(), '일룸침대', 1, '2', 1,'3')
    ,(now(), 'LG TV', 1, '2', 1,'3')
    ,(now(), '만두', 1, '1', 7,'4')
    ,(now(), '김치', 1, '1', 1,'4')
    ,(now(), '계란', 1, '1', 12,'4')
    ,(now(), '라면', 1, '1', 4,'4')
    ;