drop table friends;
drop table comments;
drop table posts;
drop table userprofiles;

create table userprofiles(
	login varchar2(255) primary key,
	password varchar2(255),
	firstname varchar2(255),
	lastname varchar2(255),
	email varchar2(255),
	isConnected number,
	lastConnection timestamp
);

create table posts(
    id_post integer,
	login_author varchar2(255),
	date_post timestamp,
    title varchar2(255),
	content varchar2(255),
	image blob;
	constraint pk_posts primary key (id_post),
	constraint fk_log_author foreign key (login_author) references userprofiles(login)
);

create table comments(
    id_comments integer,
    id_post integer,
    author_login varchar2(255),
    content varchar2(255),
    date_comment timestamp,
    constraint comments_pk primary key(id_comments),
    constraint author_comments_fk foreign key (author_login) references userprofiles(login)
);

create table friends(
	login_user varchar2(255),
	login_friend varchar2(255),
    constraint friends_pk primary key (login_user,login_friend),
	constraint friend_friends_fk foreign key (login_friend) references userprofiles(login),
	constraint user_friends_fk foreign key (login_user) references userprofiles(login)
);

insert into userprofiles (login) values ('Assim');
insert into posts (id_post,login_author,title,content) values(1,'Assim','Mon Title','Content Content');

select id_post,login_author,date_post,content,title from posts;
select * from userprofiles;
insert into friends(login_user,login_friend) values('assim','sasa');
insert into friends(login_user,login_friend) values('sasa','assim');
delete from userprofiles;

select * from posts;

select * from comments;
select * from friends;
