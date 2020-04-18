# Guide de démarrage rapide

## Configuration requise
Pour pouvoir exécuter l'application, il est nécessaire de disposer d'une base de données Postgresql.  Les informations nécessaires pour l'accès à la base de données doivent être renseignées dans le fichier de configuration `application.properties`.

```
spring.datasource.url=jdbc:postgresql://ip_base_données:port/nom_bd
spring.datasource.username=utilisateur
spring.datasource.password=mot_de_passe
```

Remplacez les éléments suivants:
 * `ip_base_données` par l'adresse IP de la machine où s'exécute la base de données
 * `port` par le port sur lequel la base de données est accessible
 * `nom_bd` par le nom de la base de données à utiliser
 * `utilisateur` par le nom d'utilisateur à utiliser pour accéder à la base de données
 * `mot_de_passe` par le mot de passe à utiliser pour accéder à la base de données

## Ajustement des paramètres
En fonction de l'environnement dans lequel va s'exécuter l'application, adaptez le nombre de threads alloués pour les différentes activités de l'application.  Les paramètres par défaut permettent à l'application de s'exécuter mais n'offrent pas les meilleures performances.

## Lancement de l'application
Une fois les paramètres personnalisés dans le fichier de configuration, lancez l'application avec la commande suivante.

```shell
java -jar ct-1.0.0-PROJECT.jar
```

Pour accéder à l'interface, connectez vous au port `8090` (par défaut) de la machine sur laquelle s'exécute l'application.

## Utilisation de l'application
Pour utiliser les fonctionnalités de l'application, il faut lui fournir au moins un serveur où trouver des logs.  Pour commencer rapidement, rendez-vous sur la page `Servers` et ajoutez un nouveau serveur avec les informations suivantes.

```
Argon 2020
https://ct.googleapis.com/logs/argon2020/
```

Cliquez ensuite sur `Start` en regard du serveur qui vient de s'ajouter à la liste.