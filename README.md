# DevTeamUTP-FacilityFinderUTP
Proyecto de DevTeamUTP: _Construir una web dinámica que permita mostrar en que ambiente le toca su clase a cualquier estudiante o docente_
## Comenzando 

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos 

_Que cosas necesitas para instalar el software y como instalarlas_

```
Da un ejemplo
```

## Dependencias

- **gson**: es una biblioteca de Java que se utiliza para convertir objetos de Java a su representación JSON y viceversa.
- **opencsv**: es una biblioteca de Java que se utiliza para leer y escribir archivos CSV.
- **user.java.time**: es una biblioteca de Java que proporciona las clases de fecha y hora de Java 8.

## Instalación

Para agregar estas dependencias a un proyecto Maven, debe agregar las siguientes líneas a su archivo `pom.xml` dentro de la sección `dependencies`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.7.1</version>
</dependency>
<dependency>
    <groupId>user.java.time</groupId>
    <artifactId>user.java.time</artifactId>
    <version>2020.267.10158</version>
</dependency>
```

Además, para poder descargar las dependencias desde sus respectivos repositorios, es necesario agregar el repositorio de Clojars en el archivo `pom.xml`, dentro de la etiqueta `<repositories>`:

```
<repository>
    <id>clojars</id>
    <name>Clojars</name>
    <url>https://repo.clojars.org/</url>
</repository>
```

Una vez agregadas estas líneas en el archivo `pom.xml`, se pueden instalar las dependencias utilizando el comando `mvn install`.

## Ejecutando las pruebas ⚙️

_Explica como ejecutar las pruebas automatizadas para este sistema_

## Despliegue 📦

_Agrega notas adicionales sobre como hacer deploy_

## Construido con 🛠️

* Agregar...
* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores ✒️

* **Ingresa su info aquí** - *Trabajo Inicial* - [villanuevand](https://github.com/villanuevand)
* **Ingresa su info aquí** - *Trabajo Inicial* - [villanuevand](https://github.com/villanuevand)
* **Gabriel Paiva** - *Backend* - [DevGitGabo](https://github.com/DevGitGabo)

## Licencia 📄

Este proyecto tiene una licencia open source.

---
