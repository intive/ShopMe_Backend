INSERT INTO category (ID , NAME ) SELECT ID, NAME FROM CSVREAD('classpath:data/categories.csv');
