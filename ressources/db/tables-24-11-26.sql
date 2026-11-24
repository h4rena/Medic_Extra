CREATE DATABASE medicextra;
\c medicextra

CREATE SEQUENCE patient_id_seq START 1;

CREATE TABLE patient (
    id_patient VARCHAR(50) PRIMARY KEY
    DEFAULT 'PAT' || LPAD(nextval('patient_id_seq')::text, 4, '0'),
    nom VARCHAR(100),
    prenom VARCHAR(100)
);

CREATE SEQUENCE medecin_id_seq START 1;

CREATE TABLE medecin (
    id_medecin VARCHAR(50) PRIMARY KEY
    DEFAULT 'MED' || LPAD(nextval('medecin_id_seq')::text, 4, '0'),
    nom VARCHAR(100),
    prenom VARCHAR(100)
);

CREATE TABLE utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'medecin', 'patient')),
    id_medecin VARCHAR(50) REFERENCES medecin(id_medecin),
    id_patient VARCHAR(50) REFERENCES patient(id_patient)
);

INSERT INTO medecin(nom, prenom) VALUES ('Martin', 'Claire');

INSERT INTO utilisateur(login, mot_de_passe, role)
VALUES ('admin', 'admin', 'admin');

INSERT INTO utilisateur(login, mot_de_passe, role, id_medecin)
SELECT 'medecin', 'medecin', 'medecin', id_medecin
FROM medecin
ORDER BY id_medecin
LIMIT 1;