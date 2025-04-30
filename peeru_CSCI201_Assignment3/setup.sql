DROP DATABASE if exists UserData;
CREATE DATABASE UserData;
USE UserData;
Create TABLE UserTable(ID int(11) primary key auto_increment,fname varchar(20) not null, username varchar(20) not null unique, password VARCHAR(20) not null);
Create TABLE FavouriteArtists( FID varchar(24) primary key not null, username varchar(20) not null, 
FOREIGN KEY (username) REFERENCES UserTable(username));
INSERT INTO UserTable (fname,username,password) VALUES ("Aakanksha","peeru@usc.edu","Incorrect");
set sql_safe_updates = 0;
select*from FavouriteArtists;
delete from FavouriteArtists;