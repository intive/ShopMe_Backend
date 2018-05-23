alter table OFFER drop OWNER_ID;

alter table OFFER add CITY VARCHAR(50) not null;

alter table OFFER add USER_ID UUID not null;
alter table OFFER add constraint FK_OFFER_USER foreign key (USER_ID) references USERS(ID);

alter table OFFER add VOIVODESHIP_NAME VARCHAR(255) not null;
alter table OFFER add constraint FK_OFFER_VOIVODESHIP foreign key (VOIVODESHIP_NAME) references VOIVODESHIP(NAME);

alter table USERS add ADDITIONAL_INFO VARCHAR(800);
