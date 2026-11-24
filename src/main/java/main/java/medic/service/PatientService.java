package main.java.medic.service;

import java.sql.SQLException;
import java.util.List;

import main.java.medic.model.Patient;
import main.java.medic.repository.PatientRepository;
import main.java.utils.Database;

public class PatientService {

    private final PatientRepository repository;

    public PatientService(Database db) {
        this.repository = new PatientRepository(db);
    }

    public List<Patient> getAll() throws SQLException {
        return repository.findAll();
    }

    public Patient getById(String id) throws SQLException {
        return repository.findById(id);
    }

    public Patient save(Patient p) throws SQLException {
        if (p.getId_patient() == null || p.getId_patient().isBlank()) {
            return repository.insert(p);
        }
        repository.update(p);
        return p;
    }

    public void delete(String id) throws SQLException {
        repository.delete(id);
    }
}
