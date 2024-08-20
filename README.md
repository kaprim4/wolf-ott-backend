# WOLF OTT MANAGEMENT

## Description
"WOLF OTT MANAGEMENT" est une solution de gestion pour les services IPTV, développée en microservices avec Spring Boot. Elle permet la gestion des utilisateurs, des appareils, des flux IPTV, des serveurs, et plus encore. Ce projet est conçu pour être hautement évolutif, sécurisé et flexible, en utilisant une architecture de microservices.

## Architecture
Le projet est divisé en plusieurs microservices, chacun gérant une partie spécifique de l'application. Les services communiquent entre eux via des API REST, et tous sont centralisés à travers un service de découverte et une passerelle.

### Services et ports

- **discovery-server**: 8761  
  Service de découverte Eureka pour la détection automatique des microservices.

- **gateway-service**: 8181  
  Passerelle API, pour rediriger les requêtes vers les services appropriés.

- **config-server**: 9000  
  Serveur de configuration centralisée pour gérer les configurations des microservices.

- **auth-service**: 9001  
  Service d'authentification et gestion des utilisateurs.

- **device-service**: 9002  
  Gestion des appareils connectés à la plateforme IPTV.

- **epg-service**: 9003  
  Gestion des programmes électroniques (EPG) pour les chaînes IPTV.

- **line-service**: 9004  
  Gestion des lignes utilisateurs pour les abonnements IPTV.

- **notification-service**: 9005  
  Service d'envoi de notifications aux utilisateurs.

- **server-service**: 9006  
  Gestion des serveurs IPTV.

- **stream-service**: 9007  
  Gestion des flux IPTV en direct.

- **ticketing-service**: 9008  
  Gestion du système de ticketing pour le support des utilisateurs.

- **user-service**: 9009  
  Gestion des informations des utilisateurs.

## Prérequis

- **JDK 17**  
  Assurez-vous que Java 17 est installé sur votre machine.

- **Maven 3.x**  
  Utilisé pour la gestion des dépendances et la construction du projet.

- **Docker** (optionnel)  
  Pour exécuter les microservices dans des conteneurs.

## Lancer le projet

1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/your-repo/wolf-ott-management.git
   cd wolf-ott-management
