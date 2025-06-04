# 🎬 MovieVault - Backend API

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)](https://www.docker.com/)

MovieVault es una aplicación **full-stack** completa para descubrir, explorar y gestionar una colección personal de películas. Este repositorio contiene el **backend API RESTful** desarrollado con Spring Boot, que proporciona servicios de autenticación, gestión de usuarios y administración de colecciones de películas. La aplicación se integra con **Supabase Auth** para autenticación segura y utiliza **PostgreSQL** para la persistencia de datos.

## 🌐 Aplicación Completa

MovieVault es un sistema full-stack que consta de:

- **🎯 Backend (Este repositorio)**: API RESTful con Spring Boot
- **🎨 Frontend**: Aplicación web moderna con Next.js 15 → [Ver Frontend](https://github.com/Juan-Valenzuela3/Movie_Vault_Frontend)
- **🚀 Demo en vivo**: [https://movie-vault-xi.vercel.app](https://movie-vault-xi.vercel.app)

## ✨ Características Principales

### 🔐 Autenticación y Seguridad
- **Sistema de Autenticación Completo**: Registro e inicio de sesión mediante Supabase Auth
- **Seguridad JWT**: Tokens JWT para autenticación y autorización de endpoints
- **Validación de Tokens**: Decodificación y validación segura de tokens JWT
- **Gestión de Sesiones**: Persistencia de sesiones de usuario autenticadas

### 👤 Gestión de Usuarios
- **Registro de Usuarios**: Creación de cuentas con validación de email único
- **Perfiles de Usuario**: Actualización de datos personales (nombre, email)
- **Confirmación de Email**: Proceso de verificación de correo electrónico
- **Integración con Supabase**: Sincronización de datos entre Supabase Auth y base de datos local

### 🎥 Gestión de Colecciones de Películas
- **CRUD Completo**: Crear, leer, actualizar y eliminar películas de la colección
- **Estados de Visualización**: Marcado de películas como "Pendiente" (`PENDING`) o "Visto" (`WATCHED`)
- **Colecciones Personalizadas**: Cada usuario tiene su propia colección privada
- **Integración TMDB**: Soporte para identificadores de The Movie Database
- **Metadatos de Películas**: Almacenamiento de título, categoría, imagen y estado

### 🛠️ Características Técnicas
- **API RESTful**: Endpoints bien estructurados siguiendo principios REST
- **CORS Configurado**: Preparado para integración con frontend en Vercel
- **Base de Datos Relacional**: Modelo de datos optimizado con PostgreSQL
- **Contenedorización**: Docker multi-stage build para despliegue eficiente
- **Validación de Datos**: Validaciones a nivel de entidad y servicio

## 🛠️ Stack Tecnológico

### Core Framework
- **Spring Boot 3.4.4**: Framework principal para desarrollo de aplicaciones Java
- **Java 21**: Versión LTS más reciente con características modernas
- **Maven 3.9+**: Gestión de dependencias y construcción del proyecto

### Persistencia y Base de Datos
- **Spring Data JPA**: Abstracción para operaciones de base de datos
- **Hibernate**: ORM para mapeo objeto-relacional
- **PostgreSQL**: Base de datos relacional robusta y escalable
- **Supabase**: Plataforma BaaS para autenticación y base de datos

### Seguridad y Autenticación
- **Supabase Auth**: Servicio de autenticación con JWT
- **JWT Tokens**: Autenticación stateless y segura
- **CORS**: Configuración para solicitudes cross-origin

### Herramientas de Desarrollo
- **Lombok**: Reducción de código boilerplate
- **RestTemplate**: Cliente HTTP para comunicación con APIs externas
- **Docker**: Contenedorización para despliegue
- **Maven Wrapper**: Ejecución consistente de Maven

## 📁 Estructura del Proyecto

```
MovieVault-Backend/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── 📂 java/Backend/
│   │   │   ├── 📄 MovieVaultBackendApplication.java    # Clase principal
│   │   │   ├── 📂 config/
│   │   │   │   └── 📄 CorsConfig.java                  # Configuración CORS
│   │   │   ├── 📂 controller/                          # Controladores REST
│   │   │   │   ├── 📄 UserController.java              # Endpoints de usuarios
│   │   │   │   └── 📄 MovieController.java             # Endpoints de películas
│   │   │   ├── 📂 service/                             # Lógica de negocio
│   │   │   │   ├── 📄 UserService.java                 # Servicios de usuario
│   │   │   │   └── 📄 MovieService.java                # Servicios de películas
│   │   │   ├── 📂 repository/                          # Acceso a datos
│   │   │   │   ├── 📄 UserRepository.java              # Repositorio de usuarios
│   │   │   │   └── 📄 MovieRepository.java             # Repositorio de películas
│   │   │   └── 📂 entity/                              # Modelos de datos
│   │   │       ├── 📄 Users.java                       # Entidad de usuario
│   │   │       ├── 📄 Movie.java                       # Entidad de película
│   │   │       ├── 📄 MovieStatus.java                 # Enum de estados
│   │   │       └── 📄 UserMovieStatus.java             # Relación usuario-película
│   │   └── 📂 resources/
│   │       └── 📄 application.properties               # Configuración de la aplicación
│   └── 📂 test/                                        # Tests unitarios
├── 📄 Dockerfile                                       # Configuración Docker
├── 📄 pom.xml                                         # Dependencias Maven
└── 📄 README.md                                       # Documentación
```

## 🚀 API Endpoints

### 👤 Gestión de Usuarios
| Método | Endpoint | Descripción | Headers Requeridos |
|--------|----------|-------------|-------------------|
| `POST` | `/users/register` | Registro de nuevos usuarios | `email`, `password`, `name` |
| `POST` | `/users/login` | Inicio de sesión de usuarios | `email`, `password` |
| `GET` | `/users/confirm-success` | Confirmación de correo electrónico | - |
| `PUT` | `/users/update` | Actualización de datos de usuario | `Authorization` |

### 🎬 Gestión de Películas
| Método | Endpoint | Descripción | Headers Requeridos |
|--------|----------|-------------|-------------------|
| `GET` | `/movies` | Obtener todas las películas del usuario | `Authorization` |
| `POST` | `/movies` | Añadir nueva película a la colección | `Authorization` |
| `PUT` | `/movies/{idMovie}` | Actualizar estado de una película | `Authorization` |
| `DELETE` | `/movies/{idMovie}` | Eliminar película de la colección | `Authorization` |

### 📋 Detalles de los Endpoints

#### Registro de Usuario
```http
POST /users/register
Headers:
  email: usuario@email.com
  password: contraseña123
  name: Nombre Usuario
```

#### Añadir Película
```http
POST /movies
Headers:
  Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "nameMovie": "Inception",
  "tmdbId": 27205,
  "category": "Sci-Fi",
  "image": "https://image.tmdb.org/...",
  "status": "PENDING"
}
```

## 🐳 Despliegue con Docker

El proyecto está optimizado para despliegue mediante **Docker** con un build multi-stage que garantiza imágenes ligeras y eficientes.

### Dockerfile Multi-Stage

#### Fase 1: Construcción
- **Base**: `maven:3.9.9-eclipse-temurin-21`
- **Proceso**: Compilación y empaquetado con Maven
- **Salida**: JAR ejecutable optimizado

#### Fase 2: Ejecución
- **Base**: `openjdk:21-slim`
- **Proceso**: Imagen ligera para producción
- **Puerto**: 8080 (expuesto)

### Comandos de Despliegue

```bash
# Construir la imagen
docker build -t movievault-backend .

# Ejecutar el contenedor
docker run -p 8080:8080 \
  -e JDBC_DATABASE_URL="jdbc:postgresql://host:5432/db" \
  -e JDBC_DATABASE_USERNAME="username" \
  -e JDBC_DATABASE_PASSWORD="password" \
  movievault-backend
```

### Variables de Entorno Requeridas

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `JDBC_DATABASE_URL` | URL de conexión PostgreSQL | `jdbc:postgresql://localhost:5432/movievault` |
| `JDBC_DATABASE_USERNAME` | Usuario de la base de datos | `movievault_user` |
| `JDBC_DATABASE_PASSWORD` | Contraseña de la base de datos | `secure_password` |

## 🌐 Integración Full-Stack

### Frontend - Next.js 15
El backend está perfectamente integrado con una aplicación frontend moderna desarrollada con:

- **Framework**: Next.js 15 con App Router
- **UI/UX**: React 19, Tailwind CSS, shadcn/ui
- **Animaciones**: Framer Motion para transiciones fluidas
- **Autenticación**: JWT tokens sincronizados con Supabase
- **Despliegue**: Vercel ([Demo en vivo](https://movie-vault-xi.vercel.app))

### Comunicación API
- **CORS**: Configurado para permitir solicitudes desde `https://movie-vault-xi.vercel.app`
- **Autenticación**: Headers JWT para todos los endpoints protegidos
- **Formato**: JSON para todas las comunicaciones
- **Validación**: Validación de datos en ambos extremos

### Flujo de Datos
1. **Frontend** → Autenticación con Supabase
2. **Frontend** → Solicitudes a Backend API con JWT
3. **Backend** → Validación de tokens y procesamiento
4. **Backend** → Respuesta con datos de usuario/películas
5. **Frontend** → Renderizado de UI actualizada

**🔗 Repositorio Frontend**: [https://github.com/Juan-Valenzuela3/Movie_Vault_Frontend](https://github.com/Juan-Valenzuela3/Movie_Vault_Frontend)

## 🗄️ Modelo de Datos

### Entidad Usuario (`Users`)
```java
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue
    private Long id;                    // Identificador único
    
    @Column(nullable = false)
    private String name;                // Nombre del usuario
    
    @Column(nullable = false, unique = true)
    private String email;               // Email único
    
    @ManyToMany
    private Set<Movie> collection;      // Colección de películas
}
```

### Entidad Película (`Movie`)
```java
@Entity
@Table(name = "movies")
public class Movie {
    @Id @GeneratedValue
    private Long idMovie;               // Identificador único
    
    @Column(nullable = false)
    private String userId;              // ID del propietario
    
    @Column(nullable = false)
    private String nameMovie;           // Título de la película
    
    private Long tmdbId;                // ID en TMDB (opcional)
    private String category;            // Categoría/género
    private String image;               // URL del poster
    
    @Enumerated(EnumType.STRING)
    private MovieStatus status;         // PENDING o WATCHED
}
```

### Estados de Película (`MovieStatus`)
```java
public enum MovieStatus {
    PENDING,    // Pendiente por ver
    WATCHED     // Ya vista
}
```

### Relaciones
- **Usuario ↔ Película**: Relación Many-to-Many
- **Un usuario** puede tener **múltiples películas**
- **Una película** puede estar en **múltiples colecciones** (diferentes usuarios)

## 🔒 Seguridad y Autenticación

### JWT Token Management
- **Proveedor**: Supabase Auth como servicio de autenticación
- **Algoritmo**: HS256 para firma de tokens
- **Validación**: Decodificación y verificación de tokens en cada request
- **Extracción**: Automática del header `Authorization: Bearer {token}`

### Flujo de Seguridad
1. **Registro**: Usuario se registra a través de Supabase
2. **Login**: Autenticación genera JWT token
3. **Requests**: Cada solicitud incluye token en headers
4. **Validación**: Backend valida token antes de procesar
5. **Autorización**: Acceso solo a recursos propios del usuario

### Protección de Endpoints
```java
// Ejemplo de extracción de userId desde JWT
private String extractUserIdFromToken(String authToken) {
    String token = authToken.replace("Bearer ", "");
    // Decodificación segura del token
    // Extracción del subject (userId)
    return userId;
}
```

### CORS Security
```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // Permitir solo frontend autorizado
        config.addAllowedOrigin("https://movie-vault-xi.vercel.app");
        config.setAllowCredentials(true);
    }
}
```

## 🛠️ Desarrollo Local

### Prerrequisitos
- **Java 21+**: OpenJDK o Oracle JDK
- **Maven 3.9+**: Para gestión de dependencias
- **PostgreSQL**: Base de datos local o remota
- **Docker** (opcional): Para contenedorización

### Configuración del Entorno

#### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/MovieVault-Backend.git
cd MovieVault-Backend
```

#### 2. Configurar Variables de Entorno
```bash
# Crear archivo .env o configurar variables del sistema
export JDBC_DATABASE_URL="jdbc:postgresql://localhost:5432/movievault"
export JDBC_DATABASE_USERNAME="tu_usuario"
export JDBC_DATABASE_PASSWORD="tu_contraseña"
```

#### 3. Configurar Supabase
```properties
# application.properties
supabase.url=https://tu-proyecto.supabase.co
supabase.anon-key=tu_anon_key
```

#### 4. Ejecutar la Aplicación
```bash
# Con Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Con Maven instalado
mvn spring-boot:run

# Con Docker
docker build -t movievault-backend .
docker run -p 8080:8080 movievault-backend
```

### Verificación
- **API Health**: `GET http://localhost:8080/actuator/health`
- **Documentación**: `GET http://localhost:8080/swagger-ui.html` (próximamente)

## 👨‍💻 Autor

**Juan Valenzuela**  
Full Stack Developer & Movie Enthusiast

[![GitHub](https://img.shields.io/badge/GitHub-Juan--Valenzuela3-black?style=for-the-badge&logo=github)](https://github.com/Juan-Valenzuela3)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Juan%20Valenzuela-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/juan-valenzuela-camelo)

## 🙏 Reconocimientos

- **Supabase**: Por el excelente servicio de autenticación
- **Spring Team**: Por el increíble framework Spring Boot
- **TMDb**: Por proporcionar la API de datos cinematográficos
- **Comunidad Open Source**: Por las librerías y herramientas utilizadas

---

## 📄 Licencia

Este proyecto está licenciado bajo [Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License](https://creativecommons.org/licenses/by-nc-nd/4.0/).

### ✅ Permisos
- **Ver y compartir**: Puedes ver y compartir este código
- **Uso educativo**: Perfecto para aprender y referencia
- **Fork personal**: Para proyectos de aprendizaje

### ❌ Restricciones
- **No modificaciones**: No puedes modificar y redistribuir
- **No uso comercial**: No puedes usar este código comercialmente
- **Sin derivados**: No puedes crear trabajos basados en este código

### 📝 Atribución Requerida
Al usar o referenciar este código, debes dar crédito:
```
MovieVault Backend por Juan Valenzuela
GitHub: https://github.com/Juan-Valenzuela3/MovieVault-Backend
```

---

<div align="center">

### 🎬 ¡Gracias por explorar MovieVault Backend! 🍿

**Construido con ❤️ para los amantes del cine**

Si te gusta el proyecto, ¡no olvides darle una ⭐!

[🌐 Demo Frontend](https://movie-vault-xi.vercel.app) • [📱 Ver Frontend Repo](https://github.com/Juan-Valenzuela3/Movie_Vault_Frontend)

</div>