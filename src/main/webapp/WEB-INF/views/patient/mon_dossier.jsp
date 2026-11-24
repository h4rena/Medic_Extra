<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="main.java.medic.model.Patient" %>
<html>
<head><title>Mon dossier</title></head>
<body>
    <h2>Mon dossier</h2>
    <%
        Patient p = (Patient) request.getAttribute("patient");
        if (p == null) {
    %>
    <p>Aucun dossier patient associé à ce compte.</p>
    <%
        } else {
    %>
    <p>ID : <%= p.getId_patient() %></p>
    <p>Nom : <%= p.getNom() %></p>
    <p>Prénom : <%= p.getPrenom() %></p>
    <%
        }
    %>
    <p><a href="${pageContext.request.contextPath}/url/accueil">Accueil</a></p>
</body>
</html>
