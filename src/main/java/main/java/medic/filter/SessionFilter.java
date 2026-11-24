package main.java.medic.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import main.java.model.Session;

public class SessionFilter implements Filter {

    private Session session;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext ctx = config.getServletContext();
        session = (Session) ctx.getAttribute("session");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession http = req.getSession();

        if (session != null) {
            session.clear();
            for (String nom : Collections.list(http.getAttributeNames())) {
                session.set(nom, http.getAttribute(nom));
            }
        }

        chain.doFilter(request, response);

        if (session != null) {
            for (String nom : Collections.list(http.getAttributeNames())) {
                http.removeAttribute(nom);
            }
            for (Map.Entry<String, Object> entree : session.getData().entrySet()) {
                http.setAttribute(entree.getKey(), entree.getValue());
            }
        }
    }

    @Override
    public void destroy() {
    }
}
