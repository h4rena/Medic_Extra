CREATE DATABASE medicextra;
\c medicextra

CREATE SEQUENCE patient_id_seq START 1;

CREATE TABLE patient (
    id_patient VARCHAR(50) PRIMARY KEY
        DEFAULT 'PAT' || LPAD(nextval('patient_id_seq')::text, 4, '0'),
    nom VARCHAR(100),
    prenom VARCHAR(100),
    date_naissance DATE
);