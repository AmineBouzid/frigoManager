# Projet Informatique Frigo 👋🧊🥛
![Version](https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000)

> Gestionnaire d'ingrédients et Génération automatique de recettes

## Authors
| Authors |
| ------ |
| 🤵Amine BOUZID |
| 🤵Mohammed LEKMAD |
| 🤵Thomas GAGNAIRE | 
| 🤵Thomas BLOMME |
| 🤵Guillaume BAYON | 
    
## Professors
| Name | Job |
| ------ | ------ |
| Jeremy Dubreuil  | Product Owner |
| Christophe Araud | Coach Scrum |
| Jules Chevalier  | Expert Java |

## Getting Started
FrigoManager est une application Java dont le but principal est de proposer différentes recettes à partir d’ingrédients présents dans la cuisine. Le logiciel propose donc des fonctionnalités permettant à l'utilisateur de gérer le contenu de son inventaire d’ingrédients avec une gestion des quantités et de dates de péremption. En fonction de ces ingrédients l’utilisateur peut choisir de générer des recettes, celles-ci indiquent les ingrédients possédés et ceux manquant. Ainsi que les étapes de préparation, et le nombre de portions; ce nombre peut être modulé en fonction du choix de l’utilisateur.
Aussi, l’utilisateur se doit de créer un profil, ce dernier inclut plusieurs informations, tels la date de naissance, le poids, la taille ainsi qu’un calcul automatique d'indice de masse corporelle et plusieurs autres informations intéressantes. Le profil permet aussi d’avoir une gestion personnalisée de recettes favorites. En effet, si l’utilisateur apprécie une recette, il peut la mettre en favori. Cette dernière sera disponible sur son profil et dans l’onglet notifications avec une liste d’ingrédients manquants pour la réaliser. De nombreux services sont aussi offerts par FrigoManager, tels que les alertes lorsqu’une date de péremption d’un aliment est proche, un affichage du nombre de calories par recette si l’utilisateur active le HealthyMode dans son profil, une proposition de recettes aléatoires alléchantes...etc

## Prerequisites
* Notre application est un fichier .jar (ou se lance sur un IDE java en clonant le projet git), il est donc nécessaire de posséder java sur la machine avant de lancer l’application. Pour des raisons de compatibilité, la version de java doit être au minimum Java11, ou une version supérieure.
* Il faut également vérifier que la base de donnée "pantrydb" soit correctement sitée dans le dossier *frigomanager* afin que l'application puisse se lancer correctement.
* L’application fonctionne à l'aide d’appels API, il faut donc veiller à ce que les crédits soient suffisants à chaque recherche (l’application notifiera l’utilisateur lorsqu’il a épuisé tous ses crédits auquel cas il devra attendre 24h ou en acheter).




