# DevTeamUTP-FacilityFinderUTP
Proyecto de DevTeamUTP: _Construir una web dinámica que permita mostrar en que ambiente le toca su clase a cualquier estudiante o docente_
## Comenzando 

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos 

- **Java17 Jdk 17**:
- **Node.js 10.24.1**:

## Dependencias

- **TypeScript 5.0.2**: TypeScript es un superconjunto de JavaScript que permite escribir y generar código de JavaScript que opera de manera fuertemente tipada y orientada a objetos.
- **svelte**: Es un compilador de JavaScript que está diseñado para crear interface de usuario de alto rendimiento.
- **Tailwind CSS v3.0**: Tailwind CSS es una potente herramienta para el desarrollo frontend, permite a los desarrolladores y diseñadores aplicar estilos a los sitios web de una manera ágil y optimizada..
- **gson**: es una biblioteca de Java que se utiliza para convertir objetos de Java a su representación JSON y viceversa.
- **opencsv**: es una biblioteca de Java que se utiliza para leer y escribir archivos CSV.
- **user.java.time**: es una biblioteca de Java que proporciona las clases de fecha y hora de Java 8.


## Instalación

### backend
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

### frontend
Te ubicas en el sub_proyecto Front
```console
    npm install
    npm run dev
```
## Ejecutando las pruebas ⚙️

_Explica como ejecutar las pruebas automatizadas para este sistema_
```http
http://localhost:8000/estudiante?codigo=U20303242
```
```json
{
estudiante: "SANCHEZ SUYON,MANUEL EDUARDO",
piso: "AV",
aula: "055",
horario: "08:00 - 10:15",
curso: "PROBLEMAS Y DESAFIOS EN EL PERU ACTUAL",
sede: "Chiclayo",
profesor: "VELIZ LLUNCOR,OSCAR EDUARDO",
torre: "AV",
dia: "AV",
pabellon: "AV"
}
```
## Despliegue 📦

### Front
Para copilar a html, css, javascrip
```console
    npm run build
```
Se crea la carpeta dist en el cual se encuentra el html, css, JavaScrip e imagenes

### Back

Desde el servidor LINUX:

Se nesecita tener creada una carpeta llamada resources, en la cual estarán el archivo .log y otra carpeta llamada csvs donde se encontrará el CSV en ISO_8859_1.
El jar debe estar en el directorio en el cual se encuentra resources.

antes de ejecutar el jar, es nesesario para que el servidor Linux pueda leer correctamente los caracteres especiales en el archivo CSV. Puedes hacerlo ejecutando el siguiente comando en la terminal del servidor Linux:

```
export LC_ALL=en_US.UTF-8
```

ahora ejecutamos el jar de la siguiente manera:

```
java -jar DevTeamUTP-FacilityFinderUTPfinal.jar -r ./resources/
```
LOS ARCHIVOS SE PUEDEN DESCARGAR EN LA CARPETA DENTRO DEL MISMO GITHUB

## Construido con 🛠️

* [Svelte](https://svelte.dev/) - Compilador Front
* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores ✒️

* **Manuel Eduardo Sánchez Suyón** - *Frontend* - [ManuEduardo](https://github.com/ManuEduardo)
* **Gabriel Antonio Paiva Quispe** - *Backend* - [DevGitGabo](https://github.com/DevGitGabo)
* **Silvia Carolina Sánchez Suyón** - *Design* - [moldenitrogeno](https://github.com/moldenitrogeno)

## Licencia 📄

Este proyecto tiene una licencia open source.

---
