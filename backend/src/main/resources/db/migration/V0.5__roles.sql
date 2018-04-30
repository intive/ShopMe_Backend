create table ROLE
(
	ROLE VARCHAR(255) not null primary key
);

create table USERS_ROLE
(
	USERS_ID UUID not null,
	ROLE_ID VARCHAR(255) not null
);
