<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="main.java.medic.model.Patient" %>
<html>
<head><title>Patient</title></head>
<body>
    <h2>Fiche patient</h2>
    <%
        Patient p = (Patient) request.getAttribute("patient");
        if (p == null) p = new Patient();
    %>
    <form method="post" action="${pageContext.request.contextPath}/url/patient/save">
        <input type="hidden" name="id_patient" value="<%= p.getId_patient() == null ? "" : p.getId_patient() %>">
        <p>Nom : <input type="text" name="nom" value="<%= p.getNom() == null ? "" : p.getNom() %>"></p>
        <p>Prénom : <input type="text" name="prenom" value="<%= p.getPrenom() == null ? "" : p.getPrenom() %>"></p>
        <p><button type="submit">Enregistrer</button></p>
    </form>
    <p><a href="${pageContext.request.contextPath}/url/patients">Retour à la liste</a></p>
</body>
</html>
