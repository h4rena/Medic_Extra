package main.java.medic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.medic.model.Utilisateur;
import main.java.utils.Database;

public class UtilisateurRepository {

    private final Database db;

    public UtilisateurRepository(Database db) {
        this.db = db;
    }

    public Utilisateur findByLogin(String login) throws SQLException {
        String sql = "SELECT id_utilisateur, login, mot_de_passe, role, id_medecin, id_patient "
                + "FROM utilisateur WHERE login = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("login"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role"),
                        rs.getString("id_medecin"),
                        rs.getString("id_patient")
                );
            }
        }
    }
}
