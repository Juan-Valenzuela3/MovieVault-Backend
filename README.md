# MovieVault - Backend

MovieVault es una aplicación web para descubrir y gestionar una colección personal de películas. Este repositorio contiene el backend desarrollado con Spring Boot, que proporciona una API RESTful para gestionar usuarios, autenticación y colecciones de películas. El backend se integra con Supabase para la autenticación y utiliza una base de datos relacional para almacenar la información de usuarios y películas.

## Características

- **Autenticación de Usuarios**: Sistema completo de registro e inicio de sesión mediante Supabase Auth.
- **Gestión de Usuarios**: Creación de cuentas, actualización de perfil y gestión de datos personales.
- **Colección de Películas**: API para añadir, eliminar y actualizar películas en la colección personal.
- **Estados de Visualización**: Marcado de películas como "Pendiente" o "Visto".
- **Seguridad JWT**: Autenticación basada en tokens JWT para proteger los endpoints.
- **Integración con TMDB**: Soporte para películas con identificadores de The Movie Database.
- **CORS Configurado**: Preparado para integrarse con el frontend desplegado en Vercel.

## Tecnologías Utilizadas

- **Backend**: Spring Boot, Java 21
- **Persistencia**: JPA/Hibernate, PostgreSQL
- **Autenticación**: Supabase Auth, JWT
- **Despliegue**: Docker
- **Documentación**: Swagger/OpenAPI (pendiente)

## Librerías y herramientas principales

- **Spring Boot**: Framework para desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la persistencia de datos y operaciones con la base de datos.
- **Lombok**: Reduce el código boilerplate en entidades y clases.
- **RestTemplate**: Para comunicación con APIs externas (Supabase).
- **Docker**: Contenedorización para facilitar el despliegue.

## Estructura del Proyecto

- **controller**: Endpoints REST para usuarios y películas.
- **service**: Lógica de negocio para usuarios y películas.
- **repository**: Interfaces para acceso a datos.
- **entity**: Modelos de datos (Users, Movie).
- **config**: Configuraciones del sistema (CORS, etc.).

## API Endpoints

### Usuarios
- **POST /users/register**: Registro de nuevos usuarios.
- **POST /users/login**: Inicio de sesión.
- **GET /users/confirm-success**: Confirmación de correo electrónico.
- **PUT /users/update**: Actualización de datos de usuario.

### Películas
- **GET /movies**: Obtener todas las películas del usuario.
- **POST /movies**: Añadir una nueva película a la colección.
- **PUT /movies/{idMovie}**: Actualizar el estado de una película.
- **DELETE /movies/{idMovie}**: Eliminar una película de la colección.

## Despliegue

El proyecto está configurado para ser desplegado mediante Docker. El Dockerfile incluido construye la aplicación en dos fases:

1. **Fase de construcción**: Utiliza Maven para compilar y empaquetar la aplicación.
2. **Fase de ejecución**: Crea una imagen ligera con OpenJDK 21 para ejecutar la aplicación.

## Integración con Frontend

El backend está configurado con CORS para permitir solicitudes desde el frontend desplegado en Vercel (https://movie-vault-xi.vercel.app).

## Modelo de Datos

### Usuario (Users)
- **id**: Identificador único
- **name**: Nombre del usuario
- **email**: Correo electrónico (único)
- **collection**: Colección de películas del usuario

### Película (Movie)
- **idMovie**: Identificador único
- **userId**: ID del usuario propietario
- **nameMovie**: Título de la película
- **tmdbId**: ID de la película en TMDB (opcional)
- **category**: Categoría o género
- **image**: URL de la imagen o poster
- **status**: Estado (PENDING o WATCHED)

## Seguridad

La aplicación utiliza tokens JWT para autenticar las solicitudes a los endpoints protegidos. Cada solicitud debe incluir un encabezado de autorización con el token JWT proporcionado por Supabase Auth.

## Requisitos

- Java 21 o superior
- Maven 3.9+
- Docker (para despliegue)
- Base de datos PostgreSQL

## Licencia

Este proyecto está licenciado bajo [Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License](https://creativecommons.org/licenses/by-nc-nd/4.0/).
