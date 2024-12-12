CREATE DATABASE DLibre;

USE DLibre;

CREATE TABLE costmer(
    id INT PRIMARY KEY ,
    nom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL
);

CREATE TABLE order(
    id INT PRIMARY KEY ,
    date DATE NOT NULL,
    amount DECIMAL(10,2),
    costmerId INT NOT NULL,
    FOREIGN KEY (costmerId) REFERENCES costmer(id)
);