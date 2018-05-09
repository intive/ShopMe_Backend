alter table OFFER drop constraint FK_OFFER_CATEGORY;
alter table OWNER drop constraint FK_OWNER_VOIVODESHIP;
alter table USERS drop constraint FK_USERS_VOIVODESHIP;

alter table OFFER alter column CATEGORY_ID type VARCHAR(255);
alter table CATEGORY alter column ID type VARCHAR(255);
update OFFER O set CATEGORY_ID = (select C.NAME from CATEGORY C where C.ID = O.CATEGORY_ID);

alter table VOIVODESHIP alter column ID type VARCHAR(255);

alter table OWNER alter column VOIVODESHIP_ID type VARCHAR(255);
update OWNER O set VOIVODESHIP_ID = (select V.NAME from VOIVODESHIP V where V.ID = O.VOIVODESHIP_ID);

alter table USERS alter column VOIVODESHIP_ID type VARCHAR(255);
update USERS U set VOIVODESHIP_ID = (select V.NAME from VOIVODESHIP V where V.ID = U.VOIVODESHIP_ID);

alter table CATEGORY drop column ID;
alter table CATEGORY add primary key (NAME);

alter table VOIVODESHIP drop column ID;
alter table VOIVODESHIP add primary key (NAME);

alter table OFFER rename column CATEGORY_ID to CATEGORY_NAME;
alter table OFFER add constraint FK_OFFER_CATEGORY  FOREIGN KEY (CATEGORY_NAME) references CATEGORY;

alter table OWNER rename column VOIVODESHIP_ID to VOIVODESHIP_NAME;
alter table OWNER add constraint FK_OWNER_VOIVODESHIP FOREIGN KEY (VOIVODESHIP_NAME) references VOIVODESHIP;

alter table USERS rename column VOIVODESHIP_ID to VOIVODESHIP_NAME;
alter table USERS add constraint FK_USERS_VOIVODESHIP FOREIGN KEY (VOIVODESHIP_NAME) references VOIVODESHIP;