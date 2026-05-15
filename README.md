# blogging-platform-api

API RESTful para un blog personal con soporte para publicaciones, categorías, etiquetas y comentarios.
 


## Tecnologías

- Java 21
- Spring Boot 3.x
- Spring Data JPA + Hibernate
- Spring Validation
- MySQL
- Lombok
- Maven
---

## Arquitectura

Arquitectura en capas (Layered Architecture):

```
com.analistas.bloggingplatform
├── controller
├── service
│   └── impl
├── repository
├── domain
│   ├── entity
│   ├── dto
│   └── mapper
└── exception
```


## Modelo de dominio

- **Post** — artículo del blog con título, contenido, subtítulo y estado
- **Category** — categoría del post (relación ManyToOne)
- **Tag** — etiquetas del post (relación ManyToMany)
- **Comment** — comentarios de un post (relación OneToMany)
---

## Endpoints

### Posts `/api/posts`

| Método | Endpoint | Descripción | Status |
|--------|----------|-------------|--------|
| GET | `/api/posts` | Listar todos los posts | 200 |
| GET | `/api/posts/{id}` | Obtener post por id | 200 |
| POST | `/api/posts` | Crear post | 201 |
| PUT | `/api/posts/{id}` | Actualizar post | 200 |
| DELETE | `/api/posts/{id}` | Eliminar post | 204 |

### Comments `/api/posts/{postId}/comments`

| Método | Endpoint | Descripción | Status |
|--------|----------|-------------|--------|
| GET | `/api/posts/{postId}/comments` | Listar comentarios de un post | 200 |
| GET | `/api/posts/{postId}/comments/{id}` | Obtener comentario por id | 200 |
| POST | `/api/posts/{postId}/comments` | Crear comentario | 201 |
| PUT | `/api/posts/{postId}/comments/{id}` | Actualizar comentario | 200 |
| DELETE | `/api/posts/{postId}/comments/{id}` | Eliminar comentario | 204 |
 
---

## Manejo de errores

Todos los errores siguen el estándar RFC 7807 (ProblemDetail):

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Post not found",
  "instance": "/api/posts/99"
}
```
 
---

## Cómo ejecutar el proyecto

### Requisitos

- Java 21
- Maven
- MySQL
### Configuración

Crear la base de datos (o dejar que Spring la cree automáticamente):

```sql
CREATE DATABASE blogging_platform;
```

Configurar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogging_platform?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Ejecutar

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`.