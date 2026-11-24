package main.java.medic.controllers;

import java.sql.SQLException;
import java.util.List;

import main.java.annotation.FWController;
import main.java.annotation.RequireAuth;
import main.java.annotation.UrlMapping;
import main.java.annotation.WebApi;
import main.java.medic.model.Patient;
import main.java.medic.service.PatientService;
import main.java.model.ModelAndView;
import main.java.model.Session;
import main.java.utils.Database;

@FWController
public class PatientController {

    private Session session;
    private Database db;

    public Session getSession() {
        return session;
    }

    public Database getDb() {
        return db;
    }

    private PatientService service() {
        return new PatientService(getDb());
    }

    @UrlMapping(value = "/patients", method = "GET")
    @RequireAuth(role = "admin")
    public ModelAndView liste() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setView("patient/liste");
        mv.addObject("patients", service().getAll());
        return mv;
    }

    @UrlMapping(value = "/patient", method = "GET")
    @RequireAuth(role = "admin")
    public ModelAndView formulaire(String id) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setView("patient/form");
        Patient patient = id == null ? new Patient() : service().getById(id);
        mv.addObject("patient", patient != null ? patient : new Patient());
        return mv;
    }

    @UrlMapping(value = "/patient/save", method = "POST")
    @RequireAuth(role = "admin")
    public ModelAndView save(Patient p) throws SQLException {
        service().save(p);
        return liste();
    }

    @UrlMapping(value = "/patient/delete", method = "POST")
    @RequireAuth(role = "admin")
    public ModelAndView delete(String id) throws SQLException {
        service().delete(id);
        return liste();
    }

    @UrlMapping(value = "/api/patients", method = "GET")
    @WebApi
    @RequireAuth(role = "admin")
    public List<Patient> apiListe() throws SQLException {
        return service().getAll();
    }

    @UrlMapping(value = "/api/patient", method = "GET")
    @WebApi
    @RequireAuth(role = "admin")
    public Patient apiUn(String id) throws SQLException {
        return service().getById(id);
    }

    @UrlMapping(value = "/mon-dossier", method = "GET")
    @RequireAuth(role = "patient")
    public ModelAndView monDossier() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setView("patient/mon_dossier");
        Object id = getSession().get("id_patient");
        Patient patient = id == null ? null : service().getById(id.toString());
        mv.addObject("patient", patient);
        return mv;
    }

    @UrlMapping(value = "/api/mon-dossier", method = "GET")
    @WebApi
    @RequireAuth(role = "patient")
    public Patient apiMonDossier() throws SQLException {
        Object id = getSession().get("id_patient");
        return id == null ? null : service().getById(id.toString());
    }
}
