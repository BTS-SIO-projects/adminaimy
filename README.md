🏥 AdminAIMY - Application d'administration du site AIMY

📌 Description
AdminAIMY est une application Java développée avec Eclipse permettant aux administrateurs de gérer les données du site AIMY, une plateforme de gestion de rendez-vous médicaux. Cette application facilite la gestion des utilisateurs, des praticiens, des rendez-vous et du contenu du site.

🚀 Fonctionnalités principales
✅ Gestion des utilisateurs (patients et praticiens)
✅ Gestion des rendez-vous (création, modification, suppression)
✅ Gestion des spécialités médicales
✅ Gestion du contenu du site (textes, bannières, informations générales)
✅ Suivi des statistiques (nombre de rendez-vous, utilisateurs actifs, etc.)
✅ Authentification sécurisée des administrateurs
✅ Exportation des données en format CSV / PDF

🛠️ Technologies utilisées
Langage : Java (JDK 17+)
IDE : Eclipse
Base de données : MySQL
Frameworks : JavaFX / Swing
Connexion DB : JDBC

📂 Structure du projet
bash
Copier
Modifier
AdminAIMY/
│── src/
│   ├── controllers/       # Gestion des actions utilisateurs
│   ├── models/            # Classes métier (User, RendezVous, Praticien, etc.)
│   ├── views/             # Interfaces graphiques (si Swing ou JavaFX)
│   ├── utils/             # Fonctions utilitaires (BD, validation, etc.)
│── lib/                   # Bibliothèques externes (JDBC, PDF, etc.)
│── config/                # Fichiers de configuration (connexion BD)
│── README.md              # Documentation
│── AdminAIMY.sql          # Script de la base de données
│── AdminAIMY.jar          # Version exécutable

⚡ Installation et exécution

📥 1. Prérequis
Java JDK 17+ installé
Eclipse IDE installé
MySQL ou MariaDB configuré
🔧 2. Configuration
Cloner le projet :

git clone https://github.com/votre-utilisateur/AdminAIMY.git
cd AdminAIMY
Importer le projet dans Eclipse :

Ouvrir Eclipse
Aller dans File → Import → Existing Projects into Workspace
Sélectionner le dossier du projet et valider
Configurer la base de données :

Importer le fichier aimy.sql dans MySQL
Modifier config/database.properties avec vos identifiants MySQL
Exécuter l'application :

Lancer la classe principale Main.java
Ou exécuter la version .jar avec :

java -jar AdminAIMY.jar

🔒 Sécurité et accès
Seuls les administrateurs enregistrés peuvent accéder à l'application
Les mots de passe sont stockés de manière sécurisée (hashés avec BCrypt)
Connexion sécurisée via JDBC et requêtes préparées

📅 Équipe de développement
👨‍💻 Développé par une équipe de 4 élèves BTS SIO dans le cadre du projet de fin d'année.

📄 Documentation
👉 📑 Documentation complète sur [Google Drive](https://drive.google.com/drive/folders/11beOHZ8IXOhjBWiJSU4ImkI0frGeOW6U?usp=drive_link)

**Dépôt GitHub** : [Lien vers le repository](https://github.com/BTS-SIO-projects/adminaimy)

🚀 Date de livraison prévue : 1er juin 2025
