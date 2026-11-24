package main.java.medic.model;

public class Patient {

    private String id_patient;
    private String nom;
    private String prenom;

    public Patient() {
    }

    public Patient(String id_patient, String nom, String prenom) {
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId_patient() {
        return id_patient;
    }

    public void setId_patient(String id_patient) {
        this.id_patient = id_patient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Patient{id_patient='" + id_patient + "', nom='" + nom + "', prenom='" + prenom + "'}";
    }
}
