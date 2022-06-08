drop table comments;
drop table movie;
create table movie
(
	mnum number(5) primary key,
	title varchar2(50),
	content varchar2(100),
	director varchar2(20)
);

create table comments
(
	num number(5) primary key, --댓글번호
	mnum number(5) references movie(mnum),--영화번호
	id varchar2(10), --작성자
	comments varchar2(100) --내용
);
create sequence movie_seq;
create sequence comments_seq;

insert into movie values(movie_seq.nextval,'메이의 새빨간 비밀','귀여운 영화','외국감독');
insert into movie values(movie_seq.nextval,'마리코','슬픈 영화','일본감독');
insert into comments values(comments_seq.nextval,1,'일일','재미나요');
insert into comments values(comments_seq.nextval,1,'이이','귀여워요');
insert into comments values(comments_seq.nextval,2,'삼삼','슬퍼요');
commit;
