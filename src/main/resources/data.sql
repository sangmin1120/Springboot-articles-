insert into member(email,password) values('111@111','{bcrypt}$2a$10$FRUHyArxnxuTSvKWQ7GB2eANgBwd2RiK/xNreM5XbltNdbMi.ae7m');
insert into member(email,password) values('222@222','{bcrypt}$2a$10$smkMxb4nb6BK9gOnjDtlfuyxU/frxtNy1JL2G.FbAeIzl0oRQqDnC');
insert into member(email,password) values('333@333','{bcrypt}$2a$10$B.JodoszGVNpBTlJkkFENOfZypU61UY0fBOwlBglR5yWdirdh2Sjy');

insert into article(member_id,title,content) values(1,'가가가가','1111');
insert into article(member_id,title,content) values(2,'나나나나','2222');
insert into article(member_id,title,content) values(3,'다다다다','3333');

insert into article(member_id,title,content) values(1,'당신의 인생 영화는?','댓글 고');
insert into article(member_id,title,content) values(2,'당신의 소울 푸드는?','댓글 고고');
insert into article(member_id,title,content) values(3,'당신의 취미는?','댓글 고고고');

insert into comment(article_id,nickname,body) values(1,'test1','1111');
insert into comment(article_id,nickname,body) values(2,'test2','2222');
insert into comment(article_id,nickname,body) values(3,'test3','3333');
insert into comment(article_id,nickname,body) values(3,'test3','123123123');

insert into comment(article_id,nickname,body) values(4,'Park','굿 윌 헌팅');
insert into comment(article_id,nickname,body) values(4,'kim','아이 엠 샘');
insert into comment(article_id,nickname,body) values(4,'Choi','쇼생크 탈출');
insert into comment(article_id,nickname,body) values(5,'Park','치킨');
insert into comment(article_id,nickname,body) values(5,'kim','샤브샤브');
insert into comment(article_id,nickname,body) values(5,'Choi','초밥');
insert into comment(article_id,nickname,body) values(6,'Park','조깅');
insert into comment(article_id,nickname,body) values(6,'Kim','유튜브 시청');
insert into comment(article_id,nickname,body) values(6,'Choi','독서');

