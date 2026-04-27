**\[DATABASE]**

CREATE DATABASE library\_db;



USE library\_db;



CREATE TABLE Book (

&#x20;   bookID VARCHAR(50) NOT NULL,

&#x20;   title VARCHAR(255) NOT NULL,

&#x20;   genre VARCHAR(100) NOT NULL,

&#x20;   status VARCHAR(50) NOT NULL,

&#x20;   PRIMARY KEY (bookID)

);





**\[USER's TABLE]**

CREATE TABLE Users (

&#x20;   userID VARCHAR(50) PRIMARY KEY,

&#x20;   name VARCHAR(100) NOT NULL,

&#x20;   email VARCHAR(100) NOT NULL,

&#x20;   password VARCHAR(100) NOT NULL,

&#x20;   userType VARCHAR(20) NOT NULL,

&#x20;   staffID VARCHAR(50) 

);



**\[BOOK's TABLE]**

USE library\_db;



CREATE TABLE Book (

&#x20;   bookID VARCHAR(50) NOT NULL,

&#x20;   title VARCHAR(255) NOT NULL,

&#x20;   genre VARCHAR(100) NOT NULL,

&#x20;   status VARCHAR(50) NOT NULL,

&#x20;   PRIMARY KEY (bookID)

);



**\[RESERVATION's TABLE]**

CREATE TABLE Reservations (

&#x20;   reservationID VARCHAR(50) PRIMARY KEY,

&#x20;   bookID VARCHAR(50),

&#x20;   userID VARCHAR(50),

&#x20;   holdDate DATE,

&#x20;   FOREIGN KEY (bookID) REFERENCES Book(bookID) ON DELETE CASCADE,

&#x20;   FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE

);



**\[TRANSACTION's TABLE]**

CREATE TABLE Transactions (

&#x20;   transactionID VARCHAR(50) PRIMARY KEY,

&#x20;   bookID VARCHAR(50),

&#x20;   userID VARCHAR(50),

&#x20;   borrowDate DATE,

&#x20;   dueDate DATE,

&#x20;   FOREIGN KEY (bookID) REFERENCES Book(bookID) ON DELETE CASCADE,

&#x20;   FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE

);









