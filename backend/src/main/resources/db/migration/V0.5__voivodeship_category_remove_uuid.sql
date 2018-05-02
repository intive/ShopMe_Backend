alter table OFFER drop constraint FK_OFFER_CATEGORY;
alter table OWNER drop constraint FK_OWNER_VOIVODESHIP;
alter table USERS drop constraint FK_USERS_VOIVODESHIP;

alter table CATEGORY drop column ID;
alter table CATEGORY add primary key (NAME);

alter table VOIVODESHIP drop column ID;
alter table VOIVODESHIP add primary key (NAME);

alter table OFFER rename column CATEGORY_ID to CATEGORY_NAME;
alter table OFFER alter column CATEGORY_NAME type VARCHAR(30);
alter table OFFER add constraint FK_OFFER_CATEGORY FOREIGN KEY(CATEGORY_NAME) references CATEGORY;

alter table OWNER rename column VOIVODESHIP_ID to VOIVODESHIP_NAME;
alter table OWNER alter column VOIVODESHIP_NAME type VARCHAR(30);
alter table OWNER add constraint FK_USERS_VOIVODESHIP FOREIGN KEY (VOIVODESHIP_NAME) references VOIVODESHIP;

alter table USERS rename column VOIVODESHIP_ID to VOIVODESHIP_NAME;
alter table USERS alter column VOIVODESHIP_NAME type VARCHAR(30);
alter table USERS add constraint FK_OWNER_VOIVODESHIP FOREIGN KEY (VOIVODESHIP_NAME) references VOIVODESHIP;