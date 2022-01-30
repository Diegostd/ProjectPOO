# ChatSystem Project SC
Projet développé en java sur un système d'échange de messages avec une base de données centralisée, utilisant la plateforme de développement logiciel Eclipse et une base de données MySQL.

## Auteurs
Le projet a été développé par les étudiants suivants :
- Diego López
- Vatosoa Razafiniary


## Pré-requis

Afin d'ouvrir le projet, les points suivants doivent être respectés :

- Avoir la version 2021-09 (4.21.0) de l'IDE Eclipse.
- Avoir  installées les librairies requises, qui sont essentiellement utilisées par SWING pour la section graphique et des librairies supplémentaires pour la connexion à la base de données MySQL.
- Utiliser la bibliothèque JavaSE en version 1.8 sur le project.
- Pour accéder à la base de données centrale, il vous faudra être connecté au VPN de l'INSA et saisir les lignes de commandes suivantes : 
                ``` mysql -h srv-bdens -P 3306 -u tp_servlet_012 -p ```
                Puis saisissez le mot de passe :  ``` Thi0zaes ```


## Pour lancer l'application
Pour exécuter ce projet, il vous suffit de suivre les étapes suivantes

- Importez le projet
- Positionnez-vous sur le package chat.System.controller
- Exécutez la classe appelée Main.java afin que le programme puisse être appelé

## Pour l'utilisateur
Pour le moment, la plupart des fonctions sont dans le backend, leur fonctionnement est donc vérifié par des tests, tels que la connexion tcp, l'enregistrement des messages dans la base de données et la gestion correcte des classes créées. Pour l'utilisateur, il est actuellement possible d'entrer son pseudo et son numéro de téléphone, ce qui lui permettra de voir les principales interfaces et une vérification des fonctions écrites dans le backend qui vérifient l'unicité du pseudo, la connexion UDP, la connexion TCP, la création de la liste d'utilisateurs connectés pour chaque utilisateur et le fonctionnement de certains boutons sur l'écran principal. Les fonctions des boutons sont:

- Bouton Login: Après avoir entré le pseudo et le numéro de téléphone, ce bouton est utilisé pour enregistrer les informations, vérifier l'unicité du pseudo et également lier le pseudo à son ID correspondant, qui dans ce cas est le numéro de téléphone.
- Bouton Update List : Ce bouton est utilisé pour mettre à jour manuellement la liste, afin que nous puissions voir s'il y a de nouveaux utilisateurs dans le programme.
- Bouton Change Pseudo: Ce bouton est utilisé pour changer notre pseudo, qui envoie un message UDP à tous les utilisateurs notifiant ce changement afin qu'il puisse être modifié dans leurs listes.
- Bouton Disconnect: Ce bouton est utilisé pour se déconnecter du programme et le fermer, après avoir appuyé sur ce bouton, il enverra un message UDP à tous les utilisateurs notifiant que cet utilisateur n'est plus connecté, ils procéderont donc à sa suppression de leur liste d'utilisateurs connectés.
- Bouton Send 
    1. Tout d'abord, pour que ce bouton fonctionne correctement, vous devez cliquer sur un utilisateur dans la liste des utilisateurs connectés.
    2. Commencez à taper le texte à envoyer dans le champ de texte blanc au-dessus du bouton et cliquez sur envoyer. Le premier clic sur l'utilisateur souhaité nous aidera à récupérer votre identifiant, ce qui nous aidera à sauvegarder votre utilisateur et à pouvoir générer un historique. Ces messages sont prévus pour être vus sur la console, puisque la section interface n'a pas encore été implémentée à 100% dans cette section. La partie test de ce bouton est également en cours de développement car sa fonctionnalité n'est pas complète à 100 %.
    
-Bouton File: Ce bouton est encore en développement.    

> Ici se termine le manuel d'utilisation.
