Compte Rendu — TP Injection des Dépendances 


Structure du projet
![img_10.png](img_10.png)

Étape 1 — Création de l’interface IDao

L’interface IDao définit la méthode getData() qui doit être implémentée par les classes de la couche DAO (accès aux données).

![img.png](img.png)

Étape 2 — Implémentation de l’interface IDao

La classe DaoImpl implémente l’interface IDao.
Elle simule la récupération d’une donnée
![img_1.png](img_1.png)

Une deuxième version DaoImplV2 a été créée dans le package ext, pour simuler une autre source de données (ex : capteurs).
![img_2.png](img_2.png)

Étape 3 — Création de l’interface IMetier

L’interface IMetier définit la méthode calcul() qui représentera la logique métier de notre application.
![img_3.png](img_3.png)

Étape 4 — Implémentation de la classe MetierImpl

La classe MetierImpl dépend de IDao pour obtenir les données nécessaires au calcul.
On injecte donc IDao soit par constructeur, soit par un setter (pour un couplage faible).
![img_4.png](img_4.png)
Le calcul dépend du résultat retourné par le DAO, mais MetierImpl ne sait pas quelle implémentation de IDao est utilisée → Couplage faible.

Étape 5 — Injection des dépendances

a. Par instanciation statique

Dans cette première approche, nous créons directement les instances dans le code (fortement typé mais simple).
![img_5.png](img_5.png)

b. Par instanciation dynamique 

Ici, nous lisons les noms de classes à partir d’un fichier config.txt,
puis nous les chargeons dynamiquement avec Class.forName().
![img_6.png](img_6.png)
Fichier config.txt :
![img_7.png](img_7.png)
Ici, il suffit de changer la première ligne du fichier config.txt pour utiliser une autre version du DAO sans modifier le code Java.


Résultat final:

Lorsque config.txt contient dao.DaoImpl → message "Version base de données"

Lorsque config.txt contient ext.DaoImplV2 → message "Version capteurs"

Le calcul est exécuté par MetierImpl sans qu’il connaisse la source des données.

![img_8.png](img_8.png)

![img_9.png](img_9.png)

Alors cette application est maintenant ouverte à l’extension mais fermée à la modification.


c. Injection de dépendances avec Spring Framework
Version XML (PresSpringXML):

![img_11.png](img_11.png)

config.xml :

![img_12.png](img_12.png)
Spring crée les objets et gère leurs dépendances automatiquement.


Version Annotations (PresSpringAnnotation)

![img_13.png](img_13.png)

Dans ce cas, les classes MetierImpl ,DaoImpl et DaoImplV2 doivent être annotées avec @Component et l’injection se fait via @Autowired.


**Ce projet applique le principe de couplage faible :**

L’application est fermée à la modification mais ouverte à l’extension.

Le code de MetierImpl ne change pas lorsque l’on ajoute un nouveau DaoImpl.

On peut simplement ajouter de nouvelles classes DAO et modifier la configuration pour les utiliser.

Cela rend l’application extensible, maintenable et réutilisable.