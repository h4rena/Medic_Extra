package main.java.medic.controllers;

import main.java.annotation.FWController;
import main.java.annotation.RequireAuth;
import main.java.annotation.UrlMapping;
import main.java.model.ModelAndView;
import main.java.model.Session;
import main.java.utils.Database;
import main.java.utils.RoleConfig;

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
    public String login(String user) {
        getSession().set("user", user);
        getSession().set("role", RoleConfig.rolePour(user));
        return "connecte = " + user + " (role = " + getSession().get("role") + ")";
    }

    @UrlMapping(value = "/logout", method = "GET")
    public String logout() {
        getSession().clear();
        return "deconnecte";
    }

    @UrlMapping(value = "/profil", method = "GET")
    @RequireAuth
    public String profil() {
        return "user en session = " + getSession().get("user");
    }

    @UrlMapping(value = "/admin", method = "GET")
    @RequireAuth(role = "admin")
    public String admin() {
        return "espace admin de " + getSession().get("user");
    }
}
