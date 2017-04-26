Projet DevOps
=============

## Stockage clé-valeur

BIZARD Astor  
BOUVIER-DENOIX Gabriel  
GRANIER Jonathan  
LAWSON Thibault  


---------------------------  
### Exécuter le projet :

Lancer le serveur : aller dans le dossier Serveur et faire :  
> $ mvn exec:java  

Lancer le client  : aller dans le dossier Client et faire :  
> $ mvn exec:java  

Pour avoir la liste des commandes client, taper "man"


---------------------------  
### Pour lancer les tests :

A la racine :
> $ mvn test  



---------------------------  
### Pour regénérer la couverture de code :

A la racine :
> $ mvn prepare-package  


---------------------------  
### Travail effectué :

Voir le Wiki sur le Dépot git  
Lien direct : https://github.com/Jonathan-Granier/DevOps/wiki  
Lien pour cloner : https://github.com/Jonathan-Granier/DevOps.wiki.git  


---------------------------  
### Problème(s) connu(s) :

Travis renvoie des erreurs (de compilation) depuis quelques commits.
Pourtant, l'exécution manuelle de "mvn test" ne provoque aucune erreur.
Nous n'avons pas déterminé la cause de ce problème.
