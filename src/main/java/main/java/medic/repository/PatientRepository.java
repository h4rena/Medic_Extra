package main.java.medic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.medic.model.Patient;
import main.java.utils.Database;

public class PatientRepository {

    private final Database db;

    public PatientRepository(Database db) {
        this.db = db;
    }

    public List<Patient> findAll() throws SQLException {
        String sql = "SELECT id_patient, nom, prenom FROM patient ORDER BY id_patient";
        List<Patient> liste = new ArrayList<>();
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                liste.add(map(rs));
            }
        }
        return liste;
    }

    public Patient findById(String id) throws SQLException {
        String sql = "SELECT id_patient, nom, prenom FROM patient WHERE id_patient = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public Patient insert(Patient p) throws SQLException {
        String sql = "INSERT INTO patient(nom, prenom) VALUES (?, ?) RETURNING id_patient";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setId_patient(rs.getString("id_patient"));
                }
            }
        }
        return p;
    }

    public void update(Patient p) throws SQLException {
        String sql = "UPDATE patient SET nom = ?, prenom = ? WHERE id_patient = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getId_patient());
            ps.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id_patient = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    private Patient map(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getString("id_patient"),
                rs.getString("nom"),
                rs.getString("prenom")
        );
    }
}
