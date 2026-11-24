<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Medic</title></head>
<body>
    <h1>${message}</h1>
    <p><a href="${pageContext.request.contextPath}/login.jsp">Connexion</a></p>
    <p><a href="${pageContext.request.contextPath}/url/logout">Déconnexion</a></p>
    <p><a href="${pageContext.request.contextPath}/url/hello">/url/hello</a></p>
    <p><a href="${pageContext.request.contextPath}/url/db/test">/url/db/test</a></p>
    <p><a href="${pageContext.request.contextPath}/url/profil">/url/profil</a></p>
    <p><a href="${pageContext.request.contextPath}/url/admin">/url/admin</a></p>
    <p><a href="${pageContext.request.contextPath}/url/patients">/url/patients</a></p>
    <p><a href="${pageContext.request.contextPath}/url/api/patients">/url/api/patients</a></p>
    <p><a href="${pageContext.request.contextPath}/url/mon-dossier">/url/mon-dossier</a></p>
</body>
</html>
