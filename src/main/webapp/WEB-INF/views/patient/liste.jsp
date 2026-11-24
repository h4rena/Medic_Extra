<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.medic.model.Patient" %>
<html>
<head><title>Liste des patients</title></head>
<body>
    <h2>Liste des patients</h2>
    <p><a href="${pageContext.request.contextPath}/url/patient">Nouveau patient</a></p>
    <table border="1">
        <tr><th>ID</th><th>Nom</th><th>Prénom</th><th>Actions</th></tr>
        <%
            List<Patient> patients = (List<Patient>) request.getAttribute("patients");
            if (patients != null) {
                for (Patient p : patients) {
        %>
        <tr>
            <td><%= p.getId_patient() %></td>
            <td><%= p.getNom() %></td>
            <td><%= p.getPrenom() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/url/patient?id=<%= p.getId_patient() %>">Modifier</a>
                <form method="post" action="${pageContext.request.contextPath}/url/patient/delete" style="display:inline">
                    <input type="hidden" name="id" value="<%= p.getId_patient() %>">
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <p><a href="${pageContext.request.contextPath}/url/accueil">Accueil</a></p>
</body>
</html>
