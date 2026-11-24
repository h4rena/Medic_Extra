package main.java.medic.model;

public class Medecin {

    private String id_medecin;
    private String nom;
    private String prenom;

    public Medecin() {
    }

    public Medecin(String id_medecin, String nom, String prenom) {
        this.id_medecin = id_medecin;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(String id_medecin) {
        this.id_medecin = id_medecin;
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
        return "Medecin{id_medecin='" + id_medecin + "', nom='" + nom + "', prenom='" + prenom + "'}";
    }
}
