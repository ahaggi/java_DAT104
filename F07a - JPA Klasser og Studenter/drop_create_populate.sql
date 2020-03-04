DROP TABLE "student";
DROP TABLE "klasse";

CREATE TABLE klasse 
(
   kode      	VARCHAR (10),
   program		VARCHAR (40),
   PRIMARY KEY (kode)
);

CREATE TABLE student 
(
   id           VARCHAR (7),
   navn         VARCHAR (40),
   klasse_kode  VARCHAR (10),
   PRIMARY KEY (id),
   FOREIGN KEY (klasse_kode) REFERENCES klasse (kode)
);

INSERT INTO klasse VALUES('15HDATA', 'Bachelor Ingeniørfag, Data');
INSERT INTO klasse VALUES('15HINF',  'Bachelor Informasjonsteknologi');

INSERT INTO student VALUES('h123456', 'King Kong', '15HDATA');
INSERT INTO student VALUES('h234567', 'Super Mann', '15HINF');







