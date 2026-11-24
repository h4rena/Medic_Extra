# Antécédent médical (avec mon framework)

## Objectif
Système permettant aux médecins de prescrire des ordonnances ...
On donne un identifiant unique au patient (mettre un QR code associé au patient).

## Le QR code
- On passe l’URL (du QR code) vers l’URL de l’identifiant du patient.
- À faire avec PHP : appel du web service dans le Java du framework, qui donne l’info à propos du patient.
- Mettre une sécurisation : ce n’est pas tout le monde qui peut avoir ça ni l’avoir.
- Si c’est dans le Java que le médecin entre, il pourra voir tout le monde quand on introduit un symptôme.

## Périmètre
- Inclus : patients, antécédents, ordonnances, QR code, API Java, service PHP, sécurisation.
- Bonus : prédiction de maladie à partir de symptômes (déjà noté plus bas).

## Acteurs
- Médecin : rôle `medecin` dans le framework (l’ancien rôle `admin` correspond au médecin).
- Patient : rôle `patient`.
- Service QR : génère et publie les images.

## Architecture
- Backend Java (WAR `Medic.war`, framework) : CRUD métier + API `@WebApi`.
- Service autonome (Spring Boot) : génération des QR codes, séparé pour monter en charge ce module.
- PHP : endpoint public `https://[host]:7777/patients/[id]` qui appelle l’API Java.
- Apache : virtualhost + CDN statique `/usr/local/cdn/patient/[id]/qrcode/[id]_qrcode.jpeg`.

## Modèle de données (à créer)
- `patient(id_patient PK, nom, prenom, date_naissance, ...)`
- `antecedent(id, id_patient FK, description, date, ...)`
- `ordonnance(id, id_patient FK, id_medecin FK, date, contenu, ...)`
- `utilisateur(id, login, role, ...)`

## API backend Java (`@WebApi`)
- `GET  /url/patients`
- `GET  /url/patient/{id}`
- `POST /url/patient`
- `PUT  /url/patient/{id}`
- `DELETE /url/patient/{id}`

## Bonus (optionnel)
- À voir, si possible à finir : si on arrive à faire un modèle de prédiction, c’est-à-dire on donne les symptômes et le système propose de quelle maladie il s’agit.

## Sprint 1 (S1)
- [ ] Tables patient
- [ ] Un simple MVC
- [ ] Patient id (système)
- [ ] Mettre une architecture microservice

- Microservice : on découpe en morceau les grandes fonctionnalités pour avoir un bundle (deployable (war)) qui parle avec l’API (la base peut être différente et le langage).
- Monolithique : bundle 1 à déployer (ex : war, ...).
- Ça ne peut pas pour le projet car ça peut ne pas supporter la montée en charge : par exemple si l’on préfère un seul module à augmenter pour la montée en charge.

## Sprint 2 (S2)
- [ ] Finalité : relier avec QR code le patient et on l’envoie dans la base PHP — `url?id=`
- [ ] Faire programme autonome : check dans la base si le patient n’a pas encore de QR code ; si le patient n’en a pas, générer un QR code jpeg relié au patient.
- [ ] Faire un répertoire pour mettre le dossier de tous les patients : `id_patient/QrCode`
- [ ] On n’envoie pas sur Tomcat mais en mode CDN.
- [ ] CDN : Content Delivery Network.

Donc ce qu’il faut faire :
- [ ] CRUD
- [ ] Programme autonome `Main.java` (couche service, repository) : prend les patients dans la base (`select *`) et vérifie dans le répertoire ; s’il n’y a pas de QR code, en créer un par rapport à l’URL qui pointe vers le PHP (à implémenter plus tard) : `localhost:8080/patient/PAT0001` (toutes des variables : ip, port…).
- [ ] On peut définir le répertoire n’importe où, et son nom.
- [ ] Configurer Apache pour faire un virtualhost.

Réexplication :
- On a un backend : `PatientController` avec des CRUD.
- `getAll()` (`@WebApi`).
- Créer l’autonome (Spring Boot) : récupère les patients (appel webapi), pour chaque patient génère un QR code, sauvegarde du QR code (l’image) par défaut.
- Apache avec virtualhost : `/usr/local/cdn/patient/[id]/qrcode/[id]_qrcode.jpeg`
- On peut livrer différents domaines avec Apache (domaine = dossier).
- REWRITE URL : le patient peut avoir d’autres docs dans le dossier patient.
- Le contenu du QR code : `https://[localhost]:7777/patients/[id]`
- Le PHP appelle `getPatientById` en webapi à notre Java.

## Questions ouvertes
- Domaine et port réels pour l’URL du QR (`7777`) ?
- Service QR : Spring Boot ou simple `Main.java` ?
