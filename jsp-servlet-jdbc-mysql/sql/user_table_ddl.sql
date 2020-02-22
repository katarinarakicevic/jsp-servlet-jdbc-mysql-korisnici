CREATE DATABASE 'primer';
USE primer;

create table korisnici (
 id  int(3) NOT NULL AUTO_INCREMENT,
 ime varchar(120) NOT NULL,
 email varchar(220) NOT NULL,
 zemlja varchar(120),
 PRIMARY KEY (id)
);