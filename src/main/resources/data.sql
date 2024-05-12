DELETE FROM TRANSACTIONS;

DELETE FROM USERS;

INSERT INTO users (fullname, cpf, email, password, balance, UserTypeEnum) VALUES ('Pamela', '123.456.789-00', 'pamela@hotmail.com', '123', 100.0, 'REGULAR');
INSERT INTO users (fullname, cpf, email, password, balance, UserTypeEnum) VALUES ('Crystal', '123.456.783-00', 'crystal@hotmail.com', '123', 200.0, 'MERCHANT');