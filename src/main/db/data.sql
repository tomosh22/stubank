CREATE TABLE Address(
AddressId int NOT NULL AUTO_INCREMENT,
Number varchar(45) NOT NULL ,
Street varchar(45) NOT NULL ,
TownOrCity varchar(45) NOT NULL ,
County varchar(45) NOT NULL ,
Postcode varchar(45) NOT NULL ,

PRIMARY KEY (AddressId)
);

CREATE TABLE User
(
Username varchar(45) NOT NULL ,
Password varchar(128) NOT NULL ,
Salt varchar(16) NOT NULL ,
FirstName varchar(45) NOT NULL ,
SecondName varchar(45) NOT NULL ,
Email varchar(45) NOT NULL ,
AddressId int NOT NULL ,

PRIMARY KEY (Username),
FOREIGN KEY (AddressId) REFERENCES Address(AddressId)
);

CREATE TABLE Account
(
Name varchar(45) NOT NULL ,
Type varchar(45) NOT NULL ,
Balance decimal(65,2) NOT NULL ,
Currency varchar(45) NOT NULL ,
Username varchar(45) NOT NULL ,


PRIMARY KEY (Name,Username),
FOREIGN KEY (Username) REFERENCES User(Username)
);

CREATE TABLE Transaction
(
TransactionId int NOT NULL AUTO_INCREMENT,
Amount decimal(65,2) NOT NULL ,
DateTime datetime NOT NULL ,
AccTo int NOT NULL ,
AccFrom int NOT NULL ,

PRIMARY KEY (TransactionId),
FOREIGN KEY (AccTo) REFERENCES Account(AccountId),
FOREIGN KEY (AccFrom) REFERENCES Account(AccountId)
);