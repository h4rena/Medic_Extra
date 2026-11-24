package main.java.medic.service;

import java.sql.SQLException;

import main.java.medic.model.Utilisateur;
import main.java.medic.repository.UtilisateurRepository;
import main.java.utils.Database;

public class UtilisateurService {

    private final UtilisateurRepository repository;

    public UtilisateurService(Database db) {
        this.repository = new UtilisateurRepository(db);
    }

    public Utilisateur authentifier(String login, String motDePasse) throws SQLException {
        if (login == null || login.isBlank()) {
            return null;
        }
        Utilisateur u = repository.findByLogin(login);
        if (u == null || motDePasse == null || !motDePasse.equals(u.getMot_de_passe())) {
            return null;
        }
        return u;
    }
}
