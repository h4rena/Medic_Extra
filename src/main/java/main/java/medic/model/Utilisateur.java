package main.java.medic.model;

public class Utilisateur {

    private int id_utilisateur;
    private String login;
    private String mot_de_passe;
    private String role;
    private String id_medecin;
    private String id_patient;

    public Utilisateur() {
    }

    public Utilisateur(int id_utilisateur, String login, String mot_de_passe, String role,
                       String id_medecin, String id_patient) {
        this.id_utilisateur = id_utilisateur;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.role = role;
        this.id_medecin = id_medecin;
        this.id_patient = id_patient;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(String id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getId_patient() {
        return id_patient;
    }

    public void setId_patient(String id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Utilisateur{id_utilisateur=" + id_utilisateur + ", login='" + login + "', role='" + role
                + "', id_medecin='" + id_medecin + "', id_patient='" + id_patient + "'}";
    }
}
