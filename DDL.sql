DROP DATABASE IF EXISTS Formula1;
CREATE DATABASE Formula1;
\c Formula1;

CREATE TABLE ESCUDERIA
(
    idEscuderia SERIAL PRIMARY KEY,
    nombre      VARCHAR(50),
    pais        VARCHAR(50),
    director    VARCHAR(100),
    campeonatos INT,
    monoplaza   VARCHAR(50)
);

CREATE TABLE PILOTO
(
    numeroCarrera INT PRIMARY KEY,
    nombre        VARCHAR(50),
    apellido      VARCHAR(50),
    nacionalidad  VARCHAR(50),
    fechaNac      DATE,
    poles         INT,
    victorias     INT,
    idEscuderia   INT,
    FOREIGN KEY (idEscuderia) REFERENCES ESCUDERIA (idEscuderia)
);

CREATE TABLE CIRCUITO
(
    idCircuito SERIAL PRIMARY KEY,
    nombre     VARCHAR(50),
    pais       VARCHAR(50),
    ciudad     VARCHAR(50),
    longitud   FLOAT,
    vueltas    INT
);
