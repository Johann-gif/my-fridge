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

### Utilisateur

Créer un utilisateur en envoyant à la route **/users/add** les paramètres suivants : (**POST**)

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