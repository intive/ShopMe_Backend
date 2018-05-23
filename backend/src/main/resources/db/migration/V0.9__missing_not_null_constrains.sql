alter table USERS alter column BANK_ACCOUNT set not null;
alter table USERS alter column INVOICE_REQUEST set not null;
alter table USERS alter column NAME set not null;
alter table USERS alter column SURNAME set not null;
alter table USERS alter column PHONE_NUMBER set not null;

alter table ADDRESS alter column CITY set not null;
alter table ADDRESS alter column NUMBER set not null;
alter table ADDRESS alter column STREET set not null;
alter table ADDRESS alter column ZIP_CODE set not null;
