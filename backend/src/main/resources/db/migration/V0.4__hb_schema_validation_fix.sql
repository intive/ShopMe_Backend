-- Workaround for: https://hibernate.atlassian.net/browse/HHH-9861
alter table OFFER alter column BASE_PRICE     type double precision;
alter table OFFER alter column EXTENDED_PRICE type double precision;
alter table OFFER alter column EXTRA_PRICE    type double precision;
