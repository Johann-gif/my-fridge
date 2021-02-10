[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=Johann-gif_my-fridge)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Johann-gif_my-fridge&metric=alert_status)](https://sonarcloud.io/dashboard?id=Johann-gif_my-fridge)

# my-fridge

## Description du projet

my-fridge est un projet d'API de reminder, répondant à une problématique du gachis de nourriture.
L'utilisateur vient enregistrer ses produits dans l'application ainsi que ses quantités et ses dates de péremptions.

## Developpeur de l'API

Johann DE ALMEIDA

## Installation et lancement

Pour créer l'image Docker
`docker build -t my-fridge .`

Pour lancer l'image Docker
`docker run --rm -it -p 8080:8080 my-fridge:latest`

## Fonctionnalités

Sur my-fridge, il est possible d'effectuer les actions suivantes :

### Swagger (Api Documentation)

[Lien vers Swagger](http://localhost:8080/swagger-ui.html)

### Utilisateur

- Créer un utilisateur en envoyant à la route **/users/add** les paramètres suivants : (**POST**)

    - pseudo
    - password
    - surname
    - firstname
    - email
    - number

- Afficher tous les utilisateurs existant en base via la route **/users** (**GET**)

- Afficher un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**GET**)

- Supprimer un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**DELETE**)

- Modifier un utilisateur existant en base en renseignant son id via la route **/users/{id}** et en envoyant les paramètres suivants : (**PUT**)
    
    - pseudo
    - surname
    - firstname
    - email
    - number

- Rechercher un utilisateur existant en base en renseignant son nom ou son prénom via la route **/users/search/{name}** (**GET**)

### Aliment

- Afficher les aliments existants en base via la route **/foods** (**GET**)

- Afficher un aliment existant en base en renseignant son id via la route **/foods/{id}** (**GET**)

- Créer un aliment en envoyant à la route **/foods/add** les paramètres suivants : (**POST**)

    - codean
    - libelle
    - quantite
    - peremption
    - perime
    - userid
    
- Supprimer un aliment existant en base en renseignant son id via la route **/foods/{id}** (**DELETE**)

- Modifier un aliment existant en base en renseignant son id via la route **/foods/{id}** et en envoyant les paramètres suivants : (**PUT**)

    - codean
    - libelle
    - quantite
    - peremption
    - perime
    - userid

- Rechercher un aliment existant en base en renseignant une partie de son nom via la route **/foods/search/{libelle}** (**GET**)

- Rechercher un aliment existant en base en renseignant son code barre via la route **/foods/codean/{codean}** (**GET**)

- Rechercher les aliments existants en base associés à un utilisateur en renseignant l'id de l'utilisateur via la route **/foods/user/{id}** (**GET**)

### Console SQL

- Une console H2-SQL est disponnible via la route **/h2-console**
    - Database : `jdbc:h2:mem:testdb`
    - Login : `admin`
    - Password : `admin`

### Collection Postman

Le fichier postman est à la racine du projet et a pour nom **my-fridge.postman_collection.json**.

### Objectifs de Sécurité

- **C** `2/5` Les données à caractère personnelle sont accessibles par tout le monde à condtion d'avoir la route vers l'endpoint concerné.
- **I** `1/5` Aucune éxigence, les données peuvent être modifiées sans problème à conditon de respecter le format des champs.
- **D** `3/5` Disponnible à tout moment à condition d'avoir lancé le conteneur Docker mais pas disponnible pour les personnes extérieures au réseau (tourne sur localhost).
- **T** `1/5` Aucune égixence de traçabilité, aucune action n'est loggée.

### Les vulnérabilités

A l'heure actuelle je me rends compte que mon choix n'a pas été judicieux quant à réaliser le TP sur une API REST en Spring Boot.

En effet, Spring Boot utilise un module nommé JPA qui empêche les injections SQL, il tombe en erreur au lieu d'éxecuter la requête.

Par ailleurs je n'ai aucune notion de XSS étant donné qu'il s'agit d'une API et non une WebApp.

J'ai scanné mon application afin d'en trouver les failles à l'aide d'un utilitaire "Owasp Zap".

La seule vulnérabilité remonté **au niveau du code** est "X-Content-Type-Options Header Missing".

Il existe néanmois des problèmes liés à sécurité des données.
En effet à l'heure actuelle l'application ne dispose d'aucune restriction sur l'accès des données et les données ne sont pas anonymisées.
Ce qui permet à n'importe qui connaissant les endpoints de reccueillir tout type de données sur les différents utilisateurs présents en base.

La solution aurait été de gérer des droits utilisateurs à l'aide de https://auth0.com/fr/.
Limitant ainsi l'accès à certain endpoints en fonction du rôle de l'utilisateur qui s'y connecte.

Pour se faire il aurait du y avoir également une phase de connexion, de mon côté n'ayant pas maitrisé cette notion dans le cours de Monsieur SIX j'ai préféré éviter de l'implémenter.

On peut aussi remonté qu'il manque une validation de certificat quant à l'url de l'application car oui l'url est toujours en http et non en https.
Il aurait été facile de le faire à l'aide de https://letsencrypt.org/fr/ si l'applicatif avait été hébergé sur heroku par exemple.

L'hébergement n'étant pas une nécessité étant donné que l'application est lancée à partir d'un Docker.

Afin de combler ce manque de vulnérabilité j'ai tenté d'intégrer des outils permettant de valider la qualité du code, trouver les vulnérabilités si elles existent et d'autres outils pratiques tels que Swagger permettant de documenter l'api de manière dynamique.

### Sonar Cloud

Afin de corriger l'ensemble des code smell et des bugs éventuels j'ai ajouté Sonar Cloud au projet.
J'avais dans l'optique qu'il repère également une ou deux vulnérabilités mais ce n'est pas le cas.
J'ai également ajouté quelques tests d'intégration à l'application,on les retrouve bien sur Sonar.

[Lien vers Sonar Cloud](https://sonarcloud.io/dashboard?id=Johann-gif_my-fridge)


### CodeQL

Comme vous l'avez expressement demandé dans votre mail j'ai rajouté CodeQL à mon repository git.
Il fonctionne enfin en compilation custom' !
Le workflow se déroule jusqu'à la fin sans encombre.

### DependaBot

J'ai également ajouté DépendaBot à mon repository git suite à votre mail.
Je n'ai néanmoins reçu aucun mail depuis lors m'indiquant des problèmes de dépendances.
