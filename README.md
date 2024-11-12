# Application Backend GrapfiPix Java - Spring Boot
## Description
Cette application backend utilise **Spring Boot** pour fournir une API RESTful. Elle est conçue pour gérer les demandes et interactions avec le frontend d'une application, en traitant les données via des services et des contrôleurs. L'application prend en charge des fonctionnalités comme l'authentification, la gestion des utilisateurs, et des opérations CRUD (Create, Read, Update, Delete) sur les entités.

## Prérequis
Avant de pouvoir exécuter cette application, vous devez vous assurer que les éléments suivants sont installés sur votre machine :
- **OpenJDK 21**
- **Maven**
- **MySQL**

## Installation
### 1. Cloner le dépôt
Clonez ce dépôt à l'aide de la commande Git suivante :

```bash
git clone https://github.com/Moonbrite/-E-commerce_cda
```

### 2. Configurer la base de données
Créez une base de données dans MySQL (ou une autre base de données selon votre configuration) et configurez les informations de connexion dans le fichier `src/main/resources/application.properties` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base
spring.datasource.username=utilisateur
spring.datasource.password=mot_de_passe
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.profiles.active=dev
```

Remettre le fichier `application.properties` en mode production pour éviter de regenerer la base de données à chaque lancement de l'application
```properties
spring.profiles.active=prod
```

### 3. Installer les dépendances
Exécutez la commande suivante pour télécharger toutes les dépendances nécessaires via Maven :
```bash
mvn install
```

### 4. Lancer l'application
Une fois que vous avez configuré l'environnement et les dépendances, vous pouvez démarrer l'application avec la commande suivante :
```bash
mvn spring-boot:run
```

Ou, pour créer un fichier exécutable JAR :
```bash
mvn clean package
```

Puis lancez-le avec la commande suivante :
```bash
java -jar target/nom-du-fichier.jar
```

L'application devrait maintenant être accessible à l'adresse `http://localhost:8081`.

## Utilisation de l'API
L'API fournit des points de terminaison REST pour interagir avec les différentes ressources. Voici quelques exemples de points d'accès :

### 1. Créer un utilisateur
- **URL** : `/api/users`
- **Méthode** : `POST`
- **Body (JSON)** :
```json
{
  "firstName": "Toine",
  "lastName": "Toitoine",
  "email": "user@test.fr",
  "password": "password"
}
```

### 2. Obtenir la liste des utilisateurs
- **URL** : `/api/users`
- **Méthode** : `GET`

### 3. Obtenir un utilisateur par ID
- **URL** : `/api/users/{id}`
- **Méthode** : `GET`

### 4. Mettre à jour un utilisateur
- **URL** : `/api/users/email/{email}`
- **Méthode** : `GET`
- **Body (JSON)** :
```json
{
  "email": "john.doe@example.com"
}
```

### 5. Supprimer un utilisateur
- **URL** : `/api/users/{id}`
- **Méthode** : `DELETE`

## Tests
Pour exécuter les tests, utilisez la commande suivante :
```bash
mvn test
```

Cela exécutera tous les tests unitaires et d'intégration dans le projet.

### Technologies utilisées
- **Spring Boot** : Framework principal pour le backend, utilisé pour la gestion de la configuration, des services web, des API REST, et plus encore.
- **Swagger UI** : Pour la gestion des endpoints API et leur documentation via `springdoc-openapi-starter-webmvc-ui` et `springfox-boot-starter`.
- **MySQL** : Base de données relationnelle utilisée, avec la connectivité via le **MySQL Connector** (`mysql-connector-j`).
- **Maven** : Gestionnaire de dépendances et de build pour automatiser la compilation, la gestion des bibliothèques et l'exécution de l'application.
- **Spring Data JPA** : Pour la gestion des opérations CRUD avec la base de données.
- **Spring Security** : Utilisé pour sécuriser les endpoints avec l'authentification et l'autorisation (probablement avec JWT dans votre cas).
- **JWT (JSON Web Tokens)** : Pour la gestion de l'authentification et la création de tokens sécurisés avec `jjwt`.
- **Lombok** : Pour générer automatiquement du code répétitif comme les getters, setters, etc.
- **H2 Database** : Base de données en mémoire utilisée pour le développement ou les tests.
- **Jackson** : Pour la sérialisation et désérialisation JSON avec les dépendances `jackson-databind`, `jackson-core` et `jackson-annotations`.

## Contribuer
1. Forkez le projet.
2. Créez une branche (`git checkout -b feature/nouvelle-fonctionnalité`).
3. Faites vos modifications et committez-les (`git commit -am 'Ajout de fonctionnalité'`).
4. Poussez la branche (`git push origin feature/nouvelle-fonctionnalité`).
5. Ouvrez une Pull Request.