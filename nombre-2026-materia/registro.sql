-- Base de datos y tabla del proyecto "Registro de estudiantes"
-- Ejecutar en MySQL Workbench / phpMyAdmin antes de abrir el programa.

CREATE DATABASE IF NOT EXISTS Registro
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE Registro;

CREATE TABLE IF NOT EXISTS estudiante (
    id       INT          NOT NULL AUTO_INCREMENT,
    nombre   VARCHAR(45)  NOT NULL,
    apellido VARCHAR(45)  NOT NULL,
    edad     INT          NOT NULL,
    ciudad   VARCHAR(45)  NOT NULL,
    PRIMARY KEY (id)
);

-- Para verificar los datos guardados:
-- SELECT * FROM Registro.estudiante;
