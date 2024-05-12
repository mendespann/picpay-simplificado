

DROP TABLE IF EXISTS transactions;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    UserTypeEnum ENUM('REGULAR', 'MERCHANT') NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS cpf_idx ON USERS (CPF);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON USERS (EMAIL);


CREATE TABLE transactions (
    transactionId BIGINT AUTO_INCREMENT PRIMARY KEY,
    payerId INT NOT NULL,
    payeeId INT NOT NULL,
    transactionValue DOUBLE NOT NULL,
    FOREIGN KEY (payerId) REFERENCES users(userId),
    FOREIGN KEY (payeeId) REFERENCES users(userId)
);
