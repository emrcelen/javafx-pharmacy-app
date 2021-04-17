------> DEMO CODE <------

CREATE DATABASE pharmacy	

CREATE TABLE account(
accountNo INT PRIMARY KEY IDENTITY(1,1),
pname VARCHAR(30) NOT NULL,
psurname VARCHAR (30) NOT NULL,
pbirthDay DATE NOT NULL,
username VARCHAR (30) NOT NULL,
userpassword VARCHAR (50) NOT NULL,
);

CREATE TABLE medicine(
medicineNo VARCHAR(20) PRIMARY KEY,
medicineName VARCHAR(30) NOT NULL,
producerName VARCHAR(30) NOT NULL,
medicineType VARCHAR(30) NOT NULL,
medicineMoney INT NOT NULL, -- INT OR MONEY
medicineAbout VARCHAR(300) NOT NULL,
);

CREATE TABLE producer(
producerName VARCHAR(30) PRIMARY KEY,
producerNo VARCHAR(20) NOT NULL,
);

ALTER TABLE medicine
ADD FOREIGN KEY (producerName) REFERENCES producer(producerName);