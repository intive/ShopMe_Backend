alter table USERS alter column EMAIL SET not null;
create unique index IDX_USERS_EMAIL on USERS (EMAIL)
;
