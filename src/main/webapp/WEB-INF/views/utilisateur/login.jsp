<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Connexion - Medic</title></head>
<body>
    <h2>Connexion</h2>
    <form method="post" action="${pageContext.request.contextPath}/url/login">
        <p>Login : <input type="text" name="login" value="medecin"></p>
        <p>Mot de passe : <input type="password" name="mot_de_passe" value="medecin"></p>
        <p><button type="submit">Se connecter</button></p>
    </form>
    <p>Comptes de test : <b>admin/admin</b> et <b>medecin/medecin</b>.</p>
    <p><a href="${pageContext.request.contextPath}/url/accueil">Accueil</a></p>
</body>
</html>
