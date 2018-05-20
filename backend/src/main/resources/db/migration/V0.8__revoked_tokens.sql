
create table REVOKED_TOKEN
(
	ID UUID not null primary key,
	USER_ID UUID,
	EXPIRATION_DATE TIMESTAMP not null
);
