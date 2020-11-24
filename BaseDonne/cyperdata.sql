drop table LesLocations;
drop table LesDVDs;
drop table LesFilmGenres;
drop table LesFilmActeurs;
drop table LesFilms;
drop table LesTechniciens;
drop table LesInterdits;
drop table LesCarteAbonnements;
drop table LesCarteBancaires;



create table LesCarteBancaires (
    noCarte number(20),
    libelle varchar2(20),
    code varchar2(20),
    dateExp date,
    constraint LesCarteBancaire_PK primary key (libelle)
);



create table LesCarteAbonnements (
    noCarte number(20),
    libelle varchar2(20),
    dateIns date,
    carteBancaire varchar(20),
    constraint LesCarteAbonnement_PK primary key (libelle),
	constraint LescarteAbonnement_FK foreign key(carteBancaire) references LesCarteBancaires(libelle)
);

create table LesInterdits(
    carteAbonnement varchar (20),
    genreInterdit varchar (12),
    constraint LesInterdits_FK foreign key (carteAbonnement) references LesCarteAbonnements(libelle),
	constraint LesInterdits_C check (genreInterdit in ('Horreur','Comédie','Fiction','Documentaire','Drama'))
);



create table LesTechniciens(
  	nom varchar (20),
  	password varchar(20)  not null,
  	constraint LesTechniciens_PK primary key(nom)
);


create table LesFilms(
  	titre varchar(30),
  	LocalDate date,
  	genre varchar(12),
  	realisateur varchar(20),
  	constraint LesFilms_PK primary key(titre),
	constraint LesFilms_C check (genre in ('Horreur','Comédie','Fiction','Documentaire','Drama'))
);

create table LesFilmActeurs(
  	nomFilm varchar(30),
  	nomActeur varchar(20)
);
create table LesFilmGenres(
  	nomFilm varchar(30),
  	nomGenre varchar(12),
  	constraint LesFilmGenre_C check (nomGenre in ('Horreur','Comédie','Fiction','Documentaire','Drama'))
);

create table LesDVDs (
    codeBarre number(10),
    endommage varchar(5),
    film varchar(30),
    constraint LesDVD_PK primary key (codeBarre),
    constraint LesDVD_FK foreign key(film) references LesFilms(titre),
	constraint LesDVD_C check (endommage in ('oui','non'))
);

create table LesLocations(
  	DVD number(10),
  	dateDebut date ,
  	nbJours number(5),
  	rendu varchar(5),
 	endommage varchar(5),
	carteLoueur varchar(20),
 	constraint LesLocations_FK foreign key (DVD) references LesDVDs (codeBarre),
	constraint LesLocations_PK primary key (DVD,dateDebut,carteLoueur),
	constraint LesLocations_C1 check (rendu in ('oui','non')),
	constraint LesLocations_C2 check (endommage in ('oui','non'))
);


 

------------------------------------------------
insert into LesCarteBancaires values (100000 ,  'abcdaaaa', '142857',to_date('2021-04-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100001 ,  'abcdbbbb', '285714',to_date('2023-03-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100002 ,  'abcdcccc', '428571',to_date('2024-07-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100003 ,  'abcddddd', '571428',to_date('2025-08-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100004 ,  'abcdeeee', '571428',to_date('2021-06-22','YYYY-MM-DD'));

  
insert into LesCarteAbonnements values (900000, 'dcbaaaaa', to_date('2019-02-22','YYYY-MM-DD'), 'abcdaaaa');
insert into LesCarteAbonnements values (900001, 'dcbabbbb', to_date('2018-07-27','YYYY-MM-DD'), 'abcdbbbb');
insert into LesCarteAbonnements values (900002, 'dcbacccc', to_date('2017-12-17','YYYY-MM-DD'), 'abcdcccc');


insert into LesInterdits values ('dcbaaaaa' ,  'Horreur');
insert into LesInterdits values ('dcbaaaaa' ,  'Drama');
insert into LesInterdits values ('dcbabbbb' ,  'Fiction');
insert into LesInterdits values ('dcbacccc' ,  'Documentaire');


insert into LesFilms values ('The Godfather' , to_date('1972-11-20','YYYY-MM-DD'), 'Fiction', 'Francis Ford Coppola');
insert into LesFilms values ('The Shawshank Redemption' , to_date('1994-05-20','YYYY-MM-DD') , 'Drama', 'Frank Darabont');
insert into LesFilms values ('E.T. The Extra-Terrestrial' ,to_date('1982-05-20','YYYY-MM-DD'), 'Comédie', 'Steven Spielberg');
insert into LesFilms values ('Star Wars' , to_date('1977-05-20','YYYY-MM-DD'), 'Fiction', 'George Lucas');
insert into LesFilms values ('Forrest Gump' , to_date('1994-05-20','YYYY-MM-DD'), 'Drama', 'Robert Zemeckis');
insert into LesFilms values ('Resident Evil' ,to_date('2002-05-20','YYYY-MM-DD'), 'Horreur', 'Paul W. S. Anderson');


insert into LesFilmActeurs values ('The Godfather' ,'Marlon Brando');
insert into LesFilmActeurs values ('The Shawshank Redemption' ,'Tim Robbins');
insert into LesFilmActeurs values ('The Shawshank Redemption' ,'Morgan Freeman');
insert into LesFilmActeurs values ('E.T.The Extra-Terrestrial' , 'Henry Thomas');
insert into LesFilmActeurs values ('Star Wars',  'Mark Hamill');

insert into LesFilmActeurs values ('Star Wars',  'Jackie Chan');---
insert into LesFilmActeurs values ('Star Wars',  'Jet Li');
insert into LesFilmActeurs values ('Forrest Gump' , 'Tom Hanks');
insert into LesFilmActeurs values ('Forrest Gump' , 'Jackie Chan');
insert into LesFilmActeurs values ('Resident Evil' , ' Milla Jovovich');
insert into LesFilmActeurs values ('Resident Evil' , ' Jet Li');

--un film pour pliusieurs genre? 
insert into LesFilmGenres values ('The Godfather' ,  'Fiction');
insert into LesFilmGenres values ('The Godfather' ,  'Drama');
insert into LesFilmGenres values ('The Shawshank Redemption' , 'Drama');
insert into LesFilmGenres values ('E.T. The Extra-Terrestrial' , 'Comédie');
insert into LesFilmGenres values ('Star Wars' , 'Fiction');
insert into LesFilmGenres values ('Forrest Gump' , 'Drama');
insert into LesFilmGenres values ('Resident Evil' ,'Horreur');


 
insert into LesDVDs values (2000000 ,  'non', 'The Godfather');
insert into LesDVDs values (2000001 ,  'non', 'The Godfather');
insert into LesDVDs values (2000010 ,  'oui', 'The Shawshank Redemption');
insert into LesDVDs values (2000011 ,  'non', 'The Shawshank Redemption');
insert into LesDVDs values (2000020 ,  'non', 'E.T. The Extra-Terrestrial');

insert into LesDVDs values (2000021 ,  'non', 'E.T. The Extra-Terrestrial');
insert into LesDVDs values (2000030 ,  'oui', 'Star Wars');
insert into LesDVDs values (2000031 ,  'non', 'Star Wars');
insert into LesDVDs values (2000032 ,  'non', 'Star Wars');

insert into LesDVDs values (2000040 ,  'non', 'Forrest Gump');
insert into LesDVDs values (2000041 ,  'non', 'Forrest Gump');
insert into LesDVDs values (2000042 ,  'non', 'Forrest Gump');
insert into LesDVDs values (2000043 ,  'non', 'Forrest Gump');

insert into LesDVDs values (2000050 ,  'non', 'Resident Evil');
insert into LesDVDs values (2000051 ,  'non', 'Resident Evil');
insert into LesDVDs values (2000052 ,  'non', 'Resident Evil');


insert into LesLocations values (2000000 ,to_date('2020-11-02','YYYY-MM-DD'), 5  ,'oui','non','abcdaaaa');
insert into LesLocations values (2000011 ,to_date('2020-11-10','YYYY-MM-DD'), 25  ,'non','non','abcdaaaa');
insert into LesLocations values (2000020 ,to_date('2020-11-03','YYYY-MM-DD'), 10  ,'oui','non','abcdbbbb');
insert into LesLocations values (2000021 ,to_date('2020-11-20','YYYY-MM-DD'), 15  ,'non','non','abcdcccc');
insert into LesLocations values (2000030 ,to_date('2020-10-30','YYYY-MM-DD'), 6  ,'oui','oui','abcddddd');
insert into LesLocations values (2000052 ,to_date('2020-10-30','YYYY-MM-DD'), 6  ,'non','non','abcdeeee');


insert into LesTechniciens values ('Chirac',  '10086');
insert into LesTechniciens values ('Sarkozy',  '10087');
insert into LesTechniciens values ('Holland' ,  '10088');
insert into LesTechniciens values ('Macron' ,  '10089');
----------------------------------------------------------------------
 

select * from LesCarteBancaires;
select * from LesCarteAbonnements;
select * from LesInterdits;
select * from LesFilms;
select * from LesFilmActeurs;
select * from LesFilmGenres;
select * from LesDVDs;
select * from LesLocations;


