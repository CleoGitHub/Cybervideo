
drop table LesLocations;
drop table LesDVDs;
drop table LesFilmGenres;
drop table LesFilmActeurs;
drop table LesFilmsRealisateur;
drop table LesFilms;
drop table LesTechniciens;
drop table LesInterdits;
drop table LesCarteAbonnements;
drop table LesCarteBancaires;
drop table LesCartes;
drop table LesRealisateurs;
drop table LesActeurs;

create table LesCartes (
    noCarte number(20),
    libelle varchar2(20),
    constraint LesCartes_PK primary key (noCarte)
);

create table LesCarteBancaires (
    noCarte number(20),
    code varchar2(20),
    dateExp date,
    constraint LesCarteBancaire_PK primary key (noCarte),
    constraint LesCarteBancaires_FK2 foreign key(noCarte) references LesCartes(noCarte)
);


---------
create table LesCarteAbonnements (
    noCarte number(20),
    dateIns date,
    solde number(20),
    carteBancaire varchar(20),
    constraint LesCarteAbonnement_PK primary key (noCarte),
    constraint LescarteAbonnement_FK2 foreign key(noCarte) references LesCartes(noCarte),
    constraint LescarteAbonnement_C check (solde>=0) 
);

--------
create table LesInterdits(
    carteAbonnement number (20),
    genreInterdit varchar (12),
    constraint LesInterdits_PK primary key(carteAbonnement,genreInterdit),
    constraint LesInterdits_FK foreign key (carteAbonnement) references LesCarteAbonnements(noCarte),
    constraint LesInterdits_C check (genreInterdit in ('Horreur','Comédie','Fiction','Documentaire','Drama'))
);



create table LesTechniciens(
  	nom varchar (20),
  	password varchar(20)  not null,
  	constraint LesTechniciens_PK primary key(nom)
);
create table LesActeurs(
  	nomActeur varchar(20),
  	constraint LesActeurs_PK primary key(nomActeur)
);
create table LesRealisateurs(
  	nomRealisateur varchar(30),
  	constraint LesRealisateurs_PK primary key(nomRealisateur)
);

create table LesFilms(
  	titre varchar(30),
  	LocalDate date,
  	constraint LesFilms_PK primary key(titre)
);
create table LesFilmActeurs(
  	nomFilm varchar(30),
  	nomActeur varchar(20),
  	constraint LesFilmActeurs_PK primary key(nomFilm,nomActeur),
  	constraint LesFilmActeurs_FK1 foreign key (nomFilm) references LesFilms(titre),
  	constraint LesFilmActeurs_FK2 foreign key (nomActeur) references LesActeurs(nomActeur)
);
create table LesFilmsRealisateur(
  	nomFilm varchar(30),
  	nomRealisateur varchar(30),
  	constraint LesFilmsRealisateur_PK primary key(nomFilm,nomRealisateur),
  	constraint LesFilmsRealisateur_FK1 foreign key (nomFilm) references LesFilms(titre),
  	constraint LesFilmsRealisateur_FK2 foreign key (nomRealisateur) references LesRealisateurs(nomRealisateur)
);
create table LesFilmGenres(
  	nomFilm varchar(30),
  	nomGenre varchar(12),
  	constraint LesFilmGenre_PK primary key(nomFilm,nomGenre),
  	constraint LesFilmGenre_FK foreign key (nomFilm) references LesFilms(titre),
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
	carteLoueur number(20),
	constraint LesLocations_FK1 foreign key (DVD) references LesDVDs (codeBarre),
	constraint LesLocations_FK2 foreign key (carteLoueur) references LesCartes (noCarte),
	constraint LesLocations_PK primary key (DVD,dateDebut,carteLoueur),
	constraint LesLocations_C1 check (rendu in ('oui','non'))
);



insert into LesCartes values (100000 ,  'abcdaaaa');
insert into LesCartes values (100001 ,  'abcdbbbb');
insert into LesCartes values (100002 ,  'abcdcccc');
insert into LesCartes values (100003 ,  'abcddddd');
insert into LesCartes values (100004 ,  'abcdeeee');

insert into LesCarteBancaires values (100000, '142857',to_date('2021-04-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100001, '285714',to_date('2023-03-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100002, '428571',to_date('2024-07-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100003, '571428',to_date('2025-08-22','YYYY-MM-DD'));
insert into LesCarteBancaires values (100004, '571428',to_date('2021-06-22','YYYY-MM-DD'));

------
insert into LesCartes values (900000, 'dcbaaaaa');
insert into LesCartes values (900001, 'dcbabbbb');
insert into LesCartes values (900002, 'dcbacccc');

------
insert into LesCarteAbonnements values (900000, to_date('2019-02-22','YYYY-MM-DD'),13, 100000);
insert into LesCarteAbonnements values (900001, to_date('2018-07-27','YYYY-MM-DD'),200,100001);
insert into LesCarteAbonnements values (900002, to_date('2017-12-17','YYYY-MM-DD'),25,100002);

-------
insert into LesInterdits values (900000 ,  'Horreur');
insert into LesInterdits values (900000 ,  'Drama');
insert into LesInterdits values (900001 ,  'Fiction');
insert into LesInterdits values (900002 ,  'Documentaire');

insert into LesRealisateurs values ('Francis Ford Coppola');
insert into LesRealisateurs values ('Frank Darabont');
insert into LesRealisateurs values ('Steven Spielberg');
insert into LesRealisateurs values ('George Lucas');
insert into LesRealisateurs values ('Robert Zemeckis');
insert into LesRealisateurs values ('Paul W. S. Anderson');

insert into LesFilms values ('The Godfather' , to_date('1972-11-20','YYYY-MM-DD'));
insert into LesFilms values ('The Shawshank Redemption' , to_date('1994-05-20','YYYY-MM-DD'));
insert into LesFilms values ('E.T. The Extra-Terrestrial' ,to_date('1982-05-20','YYYY-MM-DD'));
insert into LesFilms values ('Star Wars' , to_date('1977-05-20','YYYY-MM-DD'));
insert into LesFilms values ('Forrest Gump' , to_date('1994-05-20','YYYY-MM-DD'));
insert into LesFilms values ('Resident Evil' ,to_date('2002-05-20','YYYY-MM-DD'));

insert into LesFilmsRealisateur values ('The Godfather','Francis Ford Coppola');
insert into LesFilmsRealisateur values ('The Shawshank Redemption','Frank Darabont');
insert into LesFilmsRealisateur values ('E.T. The Extra-Terrestrial','Steven Spielberg');
insert into LesFilmsRealisateur values ('Star Wars','George Lucas');
insert into LesFilmsRealisateur values ('Forrest Gump','Robert Zemeckis');
insert into LesFilmsRealisateur values ('Resident Evil' ,'Paul W. S. Anderson');

insert into LesActeurs values ('Marlon Brando');
insert into LesActeurs values ('Tim Robbins');
insert into LesActeurs values ('Morgan Freeman');
insert into LesActeurs values ('Henry Thomas');
insert into LesActeurs values ('Mark Hamill');
insert into LesActeurs values ('Jackie Chan');
insert into LesActeurs values ('Jet Li');
insert into LesActeurs values ('Tom Hanks');
insert into LesActeurs values ('Milla Jovovich');

insert into LesFilmActeurs values ('The Godfather' ,'Marlon Brando');
insert into LesFilmActeurs values ('The Shawshank Redemption' ,'Tim Robbins');
insert into LesFilmActeurs values ('The Shawshank Redemption' ,'Morgan Freeman');
insert into LesFilmActeurs values ('E.T. The Extra-Terrestrial' ,'Henry Thomas');
insert into LesFilmActeurs values ('Star Wars',  'Mark Hamill');

insert into LesFilmActeurs values ('Star Wars',  'Jackie Chan');
insert into LesFilmActeurs values ('Star Wars',  'Jet Li');
insert into LesFilmActeurs values ('Forrest Gump' , 'Tom Hanks');
insert into LesFilmActeurs values ('Forrest Gump' , 'Jackie Chan');
insert into LesFilmActeurs values ('Resident Evil' , 'Milla Jovovich');
insert into LesFilmActeurs values ('Resident Evil' , 'Jet Li');


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


insert into LesLocations values (2000000 ,to_date('2020-11-02','YYYY-MM-DD'), 5  , 'non',100000);
insert into LesLocations values (2000011 ,to_date('2020-11-10','YYYY-MM-DD'), 25  , 'non',100000);
insert into LesLocations values (2000020 ,to_date('2020-11-03','YYYY-MM-DD'), 10  , 'non',100001);
insert into LesLocations values (2000021 ,to_date('2020-11-20','YYYY-MM-DD'), 15  , 'non',100002);
insert into LesLocations values (2000030 ,to_date('2020-10-30','YYYY-MM-DD'), 6  , 'oui',100003);
insert into LesLocations values (2000052 ,to_date('2020-10-30','YYYY-MM-DD'), 6  , 'non',100004);


insert into LesTechniciens values ('Chirac',  '10086');
insert into LesTechniciens values ('Sarkozy',  '10087');
insert into LesTechniciens values ('Holland' ,  '10088');
insert into LesTechniciens values ('Macron' ,  '10089');
