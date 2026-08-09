# TestTech - Guide de Voyage

## Présentation

Ce projet est une API REST développée avec Spring Boot dans le cadre d'un test technique.

L'objectif est de permettre la gestion de guides de voyage et de leurs activités tout en appliquant une gestion des utilisateurs avec différents niveaux de droits.

Deux types d'utilisateurs sont disponibles :

- **ADMIN**
- **USER**

Un administrateur peut gérer les guides, les activités et les utilisateurs, tandis qu'un utilisateur simple ne peut consulter que les guides auxquels il a été invité.

---

## Technologies utilisées

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Maven
- Swagger / OpenAPI

---

## Fonctionnalités

### Authentification

- Connexion avec JWT
- Gestion des rôles ADMIN / USER
- Protection des endpoints

### Utilisateurs

- Création d'un utilisateur
- Consultation de la liste des utilisateurs (ADMIN uniquement)
- Suppression d'un utilisateur

### Guides

- Création d'un guide
- Consultation des guides
- Consultation d'un guide
- Modification d'un guide
- Suppression d'un guide

### Activités

- Ajout d'une activité à un guide
- Consultation des activités
- Modification d'une activité
- Suppression d'une activité

### Invitations

- Invitation d'un utilisateur à un guide
- Un utilisateur ne voit que les guides auxquels il est invité
- Un administrateur voit tous les guides

### Validation

Les données reçues par l'API sont validées avant d'être enregistrées.

### Gestion des erreurs

Les erreurs sont centralisées grâce à un `@RestControllerAdvice` afin de retourner des réponses JSON claires.

### Documentation

L'API est documentée avec Swagger.

---

## Installation

### Cloner le projet

```bash
git clone https://github.com/VOTRE_USERNAME/TestTech.git
```

Puis :

```bash
cd TestTech
```

---

## Base de données

Créer une base PostgreSQL :

```
TestTech
```

Configurer ensuite le fichier :

```
src/main/resources/application.properties
```

Exemple :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TestTech
spring.datasource.username=postgres
spring.datasource.password=motdepasse

spring.jpa.hibernate.ddl-auto=update
```

---

## Lancement

Depuis IntelliJ ou avec Maven :

```bash
mvn spring-boot:run
```

L'application démarre sur :

```
http://localhost:8080
```

---

## Documentation Swagger

Une fois l'application démarrée :

```
http://localhost:8080/swagger-ui/index.html
```

---

## Authentification

Après connexion :

```
POST /api/auth/login
```

un token JWT est retourné.

Ce token doit être envoyé dans les requêtes protégées :

```
Authorization: Bearer <token>
```

---

## Structure du projet

```
config/
security/

auth/

user/
    controller/
    service/
    repository/
    entity/
    dto/

guide/
    controller/
    service/
    repository/
    entity/
    dto/

activity/
    controller/
    service/
    repository/
    entity/
    dto/

exception/
```

---

## Choix techniques

Quelques choix ont été faits pendant le développement :

- séparation Controller / Service / Repository
- utilisation de DTO pour éviter d'exposer directement les entités
- UUID comme identifiants
- mots de passe chiffrés avec BCrypt
- authentification basée sur JWT
- contrôle des accès selon le rôle de l'utilisateur
- documentation Swagger
- gestion centralisée des exceptions

---

