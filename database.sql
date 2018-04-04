create table userprofiles(
	login varchar2(255) primary key,
	password varchar2(255),
	firstname varchar2(255),
	lastname varchar2(255),
	email varchar2(255)
);

create table posts(
	login_author varchar2(255),
	date_post timestamp,
	content varchar2(255),
	constraint pk_posts primary key (login_author,date_post,content),
	constraint fk_log_author foreign key (login_author) references userprofiles(login)
);

create table friends(
	login_user varchar2(255),
	login_friend varchar2(255),
	constraint friend_friends_fk foreign key (login_friend) references userprofiles(login),
	constraint user_friends_fk foreign key (login_user) references userprofiles(login)
);
