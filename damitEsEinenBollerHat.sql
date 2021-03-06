drop table Dozent CASCADE CONSTRAINTS;
drop table Fakult�t CASCADE CONSTRAINTS;
drop table Semester CASCADE CONSTRAINTS;
drop table Studiengang CASCADE CONSTRAINTS;
drop table Uebung CASCADE CONSTRAINTS;
drop table Verwaltungspersonal CASCADE CONSTRAINTS;
drop table Student CASCADE CONSTRAINTS;
drop table Wis_Mitarbeiter CASCADE CONSTRAINTS;
drop table Department CASCADE CONSTRAINTS;
drop table Klausur CASCADE CONSTRAINTS;
drop table Vorlesung CASCADE CONSTRAINTS;
drop table Mitarbeiter CASCADE CONSTRAINTS;
drop table BESUCHT CASCADE CONSTRAINTS;
drop table SCHREIBT CASCADE CONSTRAINTS;


create table Semester
(Semesterbezeichnung	varchar(7), 
Enddatum			date, 
Beginndatum			date,
primary key (Semesterbezeichnung));

create table Fakult�t
(Fakult�tsname			varchar(10),
PersonalID          varchar(10),
primary key (Fakult�tsname));

create table Department
(Departmentname		varchar(10),
Fakult�tsname		varchar(10),
AnzahlStudis		number,
primary key (Departmentname),
foreign key (Fakult�tsname) references Fakult�t);

create table Mitarbeiter
(PersonalID			varchar(10),
Departmentname		varchar(10),
Name				      varchar(20),
primary key (PersonalID),
foreign key (Departmentname) references Department);

create table Dozent
(PersonalID			varchar(10),
DozK�rzel				varchar(10),
foreign key (PersonalID) references Mitarbeiter,
primary key (PersonalID));

create table Studiengang
(Departmentname		varchar(10),
Studiengangsbezeichnung	varchar(10),
primary key (Studiengangsbezeichnung),
foreign key (Departmentname) references Department);

create table Student
(MatrNr				varchar(7),
Departmentname			varchar(10),
Studiengangsbezeichnung		varchar(10),
Name				varchar(20),
primary key (MatrNr),
foreign key (Studiengangsbezeichnung) references Studiengang,
foreign key (Departmentname) references Department);


create table Verwaltungspersonal
(PersonalID			varchar(10),
Kostenstelle			varchar(10),
primary key (PersonalID),
foreign key (PersonalID) references Mitarbeiter);

create table Vorlesung
(PersonalID			varchar(10),
Vorlesungsname			varchar(10),
Studiengangsbezeichnung		varchar(10),
primary key (Vorlesungsname),
foreign key (Studiengangsbezeichnung) references Studiengang,
foreign key (PersonalID) references Dozent);

create table Besucht
(MatrNr				varchar(7),
Vorlesungsname			varchar(10),
foreign key (Vorlesungsname) references Vorlesung,
foreign key (MatrNr) references Student);

create table Wis_Mitarbeiter
(PersonalID			varchar(10),
Fachgebiet				varchar(10),
primary key (PersonalID),
foreign key (PersonalID) references Mitarbeiter);

create table Uebung
(Vorlesungsname		varchar(10),
PersonalID				varchar(10),
primary key (Vorlesungsname),
foreign key (PersonalID) references Wis_Mitarbeiter);

create table Klausur
(Beginnzeit			    varchar(5),
Dauer				    varchar(10),
Raum				    varchar(4),
Klausurdatum  			date,
Vorlesungsname			varchar(10),
Semesterbezeichnung		varchar(10),
Klausurschl�ssel        varchar(20),
foreign key (Vorlesungsname) references Vorlesung,
foreign key (Semesterbezeichnung) references Semester,
primary key (Klausurschl�ssel));

create table Schreibt
(MatrNr				    	varchar(7),
Klausurschl�ssel			varchar(20),
Punkte				    	number(2) check (Punkte between 0 and 15),
foreign key (Klausurschl�ssel) references Klausur,
foreign key (MatrNr) references Student);


INSERT INTO FAKULT�T VALUES ('MI', 0);
INSERT INTO FAKULT�T VALUES ('BW', 0);

INSERT INTO DEPARTMENT VALUES ('AI', 'MI', 200);
INSERT INTO DEPARTMENT VALUES ('TI', 'MI', 200);
INSERT INTO DEPARTMENT VALUES ('BL', 'BW', 150);
INSERT INTO DEPARTMENT VALUES ('AL', 'BW', 150);

INSERT INTO Semester VALUES ('WISE', '01.01.1970', '01.08.1970');
INSERT INTO Semester VALUES ('SOSE', '01.07.1970', '01.12.1970');

INSERT INTO Studiengang VALUES ('TI', 'BTI1');
INSERT INTO Studiengang VALUES ('AI', 'BAI1');
INSERT INTO Studiengang VALUES ('AI', 'BAI2');

INSERT INTO Mitarbeiter VALUES ('01','AI', 'BERND KAHLBRANT');
INSERT INTO Mitarbeiter VALUES ('02','AI', 'GERT �LKER');
INSERT INTO Mitarbeiter VALUES ('03','AI', 'GERKEN');
INSERT INTO Mitarbeiter VALUES ('04','TI', 'KLAUS KLEBER');
INSERT INTO Mitarbeiter VALUES ('05','BL', 'GUNDULA GAUSE');

INSERT INTO Dozent VALUES ('01','BK');
INSERT INTO Dozent VALUES ('03', 'GER');

INSERT INTO Wis_Mitarbeiter VALUES('02', 'G�');

INSERT INTO Verwaltungspersonal VALUES('04', 'KK');
INSERT INTO Verwaltungspersonal VALUES('05', 'GG');

INSERT INTO Vorlesung VALUES ('01','PM1', 'BAI1');
INSERT INTO Vorlesung VALUES ('01','PM2', 'BAI2');
INSERT INTO Vorlesung VALUES ('03','DB', 'BAI2');
INSERT INTO Vorlesung VALUES ('03','LB', 'BAI2');
INSERT INTO Vorlesung VALUES ('03','MG', 'BAI1');

INSERT INTO Student VALUES ('1234567','TI', 'BTI1', 'Mustermann');
INSERT INTO Student VALUES ('2319195','AI', 'BAI1', 'Schomacker');
INSERT INTO Student VALUES ('2319845','AI', 'BAI1', 'Trendelenburg');


INSERT INTO BESUCHT VALUES ('2319845', 'PM2');
INSERT INTO BESUCHT VALUES ('2319845', 'DB');
INSERT INTO BESUCHT VALUES ('2319845', 'LB');
INSERT INTO BESUCHT VALUES ('1234567', 'PM1');


INSERT INTO Uebung VALUES ('PM1', '02');
INSERT INTO Uebung VALUES ('PM2', '02');


INSERT INTO Klausur VALUES ('0800','120', '1085', '01.01.1970', 'PM1', 'SOSE', 'PM1SOSE');
INSERT INTO Klausur VALUES ('0800','120', '1001', '01.07.1970', 'MG', 'SOSE', 'MGSOSE');
INSERT INTO Klausur VALUES ('0800','120', '1001', '01.07.1970', 'MG', 'WISE', 'MGWISE');

INSERT INTO SCHREIBT VALUES('1234567', 'PM1SOSE', 13);
INSERT INTO SCHREIBT VALUES('2319845', 'MGSOSE', 0);
INSERT INTO SCHREIBT VALUES('2319845', 'MGWISE', 13);

SELECT * FROM FAKULT�T;
SELECT * FROM DEPARTMENT;
SELECT * FROM MITARBEITER;
SELECT * FROM DOZENT;
SELECT * FROM WIS_MITARBEITER; 
SELECT * FROM VERWALTUNGSPERSONAL; 
SELECT * FROM STUDENT; 
SELECT * FROM SEMESTER; 
SELECT * FROM STUDIENGANG; 
SELECT * FROM VORLESUNG; 
SELECT * FROM UEBUNG; 
SELECT * FROM KLAUSUR;
SELECT * FROM SCHREIBT;
SELECT * FROM BESUCHT;