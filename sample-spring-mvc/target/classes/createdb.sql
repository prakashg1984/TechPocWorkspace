CREATE DATABASE myfirsttestdb;

CREATE TABLE user_details (
    ID int NOT NULL AUTO_INCREMENT,
    user_id varchar(255) NOT NULL,
    password varchar(255),
    email_address varchar(255),
    PRIMARY KEY (ID)
);


