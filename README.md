# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact

#Execute docker compose
docker-compose up -d

java -jar -Xmx512m target/autentificacion.jar 



Para ejecutar uberjar
java -jar payara-micro-5.2020.4.jar --deploy autentificacion.war --outputUberJar autentificacion.jar

#Crear  el Uberjar
java -jar   /home/avbravo/software/payara/payara-micro-5.2020.4.jar --deploy /home/avbravo/NetBeansProjects/utp/autentificacion/autentificacion/target/autentificacion.war --outputUberJar /home/avbravo/Descargas/autentificacion.jar 


#Ejecutar el war

java -jar -Xmx512m /home/avbravo/software/payara/payara-micro-5.2020.4.jar  --deploy /home/avbravo/NetBeansProjects/microprofile/metricas/target/metricas.war --nocluster --logo --port 9001


JAR

$ java -jar -Xmx512m autentificacion.jar --nocluster --logo --port 9001

Swagger
http://avbravo:9001/autentificacion/resources/openapi-ui/index.html