package main.java.medic.controllers;

import java.sql.SQLException;

import main.java.annotation.FWController;
import main.java.annotation.RequireAuth;
import main.java.annotation.UrlMapping;
import main.java.medic.model.Utilisateur;
import main.java.medic.service.UtilisateurService;
import main.java.model.ModelAndView;
import main.java.model.Session;
import main.java.utils.Database;

@FWController
public class HomeController {

    private Session session;
    private Database db;

    public Session getSession() {
        return session;
    }

    public Database getDb() {
        return db;
    }

    @UrlMapping(value = "/hello", method = "GET")
    public String hello() {
        return "hello depuis Medic";
    }

    @UrlMapping(value = "/accueil", method = "GET")
    public ModelAndView accueil() {
        ModelAndView mv = new ModelAndView();
        mv.setView("accueil");
        mv.addObject("message", "Bienvenue dans Medic");
        return mv;
    }

    @UrlMapping(value = "/db/test", method = "GET")
    public String testDb() {
        return getDb().tester();
    }

    @UrlMapping(value = "/login", method = "POST")
    public String login(String login, String mot_de_passe) throws SQLException {
        getSession().clear();
        Utilisateur u = new UtilisateurService(getDb()).authentifier(login, mot_de_passe);
        if (u == null) {
            return "login ou mot de passe incorrect";
        }
        String roleEffectif = "patient".equals(u.getRole()) ? "patient" : "admin";
        getSession().set("user", u.getLogin());
        getSession().set("role", roleEffectif);
        getSession().set("roleDb", u.getRole());
        getSession().set("id_medecin", u.getId_medecin());
        getSession().set("id_patient", u.getId_patient());
        return "connecte = " + u.getLogin() + " (role = " + roleEffectif + ", role DB = " + u.getRole() + ")";
    }

    @UrlMapping(value = "/logout", method = "GET")
    public String logout() {
        getSession().clear();
        return "deconnecte";
    }

    @UrlMapping(value = "/profil", method = "GET")
    @RequireAuth
    public String profil() {
        return "user en session = " + getSession().get("user")
                + " (role DB = " + getSession().get("roleDb") + ")";
    }

    @UrlMapping(value = "/admin", method = "GET")
    @RequireAuth(role = "admin")
    public String admin() {
        return "espace admin/medecin de " + getSession().get("user");
    }
}
