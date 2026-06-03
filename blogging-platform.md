# 📌 PROYECTO: Blogging Platform API

---

## 1. CONTEXTO

Sistema de blog personal con soporte para publicaciones, categorías y comentarios,
accesible mediante una API RESTful.

**Objetivo real:**
- Permite a un usuario crear y gestionar artículos con categorías y comentarios
- Existe porque el blogging es uno de los dominios más comunes en el mercado laboral:
  casi todo sistema de contenido (noticias, documentación, foros) comparte esta
  estructura base

---

## 2. OBJETIVOS DE APRENDIZAJE

- **Módulo 6 – Spring Data JPA:** entidades, repositorios, query methods, JPQL
- **Módulo 7 – Relaciones JPA:** OneToMany, ManyToOne, ManyToMany, bidireccionales,
  cascada, @JsonIgnore
- **Módulo 8 – Validaciones:** Bean Validation en DTOs, respuestas de error
- **Módulo 1-2-3 (refuerzo):** DTOs, mappers, excepciones — área de dificultad
  identificada en el proyecto anterior

---

## 3. ARQUITECTURA OBLIGATORIA

com.blog
├── controller
├── service
│   └── impl
├── repository
├── domain
│   ├── entity
│   ├── dto
│   └── mapper
├── exception
└── config

**Reglas:**
- Usar DTOs en todas las entradas y salidas — nunca exponer entidades directamente
- Toda respuesta del controller debe ser `ResponseEntity<>`
- Un único `@RestControllerAdvice` para manejo de errores
- Validaciones solo en los DTOs de request con jakarta.validation

---

## 4. MODELO DE DOMINIO

**Entidades:**
- `Post` — artículo del blog (título, contenido, fecha de creación, estado)
- `Category` — categoría del post (nombre, descripción)
- `Tag` — etiqueta del post (nombre)
- `Comment` — comentario de un post (contenido, fecha)

**Relaciones:**
- `Post` → `Category`: ManyToOne (un post tiene una categoría, una categoría
  puede tener muchos posts)
- `Post` → `Tag`: ManyToMany (un post puede tener muchas etiquetas,
  una etiqueta puede estar en muchos posts)
- `Post` → `Comment`: OneToMany (un post puede tener muchos comentarios,
  un comentario pertenece a un solo post)

---

## 5. ROADMAP DE IMPLEMENTACIÓN

### 🔹 Paso 1 — Setup del proyecto y entidad Post

**Objetivo técnico:**
- Inicializar el proyecto con las dependencias correctas
- Crear la entidad `Post` con sus campos básicos
- Configurar la conexión a MySQL

**Conceptos:**
- Módulo 1: estructura del proyecto Spring Boot
- Módulo 6: @Entity, @Id, @GeneratedValue, @PrePersist

**Preguntas guía:**
- ¿Qué dependencias necesitás agregar en el pom.xml respecto al proyecto anterior?
- ¿Qué campos debería tener un Post en un blog real?
- ¿Qué tipo de dato usarías para la fecha de creación y por qué?
- ¿Qué anotación usarías para que la fecha se asigne automáticamente al persistir?

**Errores comunes:**
- Olvidar `@NoArgsConstructor` en la entidad
- Usar `java.util.Date` en lugar de `LocalDateTime`
- No configurar correctamente el `application.properties` para MySQL

**Criterio de finalización:**
- La aplicación levanta sin errores y crea la tabla `post` automáticamente en MySQL

---

### 🔹 Paso 2 — Repositorio, DTOs y Mapper de Post

**Objetivo técnico:**
- Crear `PostRepository`, `PostRequest`, `PostResponse` y `PostMapper`
- Aplicar validaciones en el DTO de request

**Conceptos:**
- Módulo 6: JpaRepository, query methods
- Módulo 8: Bean Validation (@NotBlank, @NotNull, @Size)
- Módulo 1: DTOs (área de dificultad identificada)

**Preguntas guía:**
- ¿Qué diferencia hay entre `PostRequest` y `PostResponse`? ¿Qué campos
  tiene cada uno?
- ¿Por qué el mapper es una clase separada y no lo hacés directamente
  en el service?
- ¿Qué campos del Post no debería poder enviar el cliente en el request?
- ¿Qué validaciones tiene sentido aplicar al título y al contenido?

**Errores comunes:**
- Poner los mismos campos en Request y Response sin pensar en qué expone cada uno
- Olvidar `@NoArgsConstructor` en el DTO para que Jackson pueda deserializar
- Mapper que accede directamente al repositorio (rompe la separación de capas)

**Criterio de finalización:**
- `PostMapper` convierte correctamente de `PostRequest` a `Post` y de
  `Post` a `PostResponse` sin lógica de negocio adentro

---

### 🔹 Paso 3 — Service y Controller de Post (CRUD básico)

**Objetivo técnico:**
- Implementar `PostService` + `PostServiceImpl` con operaciones CRUD
- Implementar `PostController` con todos los endpoints

**Conceptos:**
- Módulo 1: @RestController, ResponseEntity, status codes
- Módulo 2: inyección de dependencias por constructor
- Módulo 3: ResourceNotFoundException

**Preguntas guía:**
- ¿Qué métodos debería definir la interfaz `PostService`?
- ¿Por qué inyectás el repositorio en el service y no en el controller?
- ¿Qué status HTTP corresponde a cada operación CRUD?
- ¿Cómo manejás el caso en que el post no existe?

**Errores comunes:**
- Inyectar el repositorio directamente en el controller
- Retornar la entidad directamente en lugar del DTO
- Usar el status HTTP incorrecto (200 en lugar de 201 para POST)

**Criterio de finalización:**
- CRUD completo de Post funciona correctamente en Postman con los
  status HTTP correctos

---

### 🔹 Paso 4 — Entidad Category y relación ManyToOne

**Objetivo técnico:**
- Crear la entidad `Category` y relacionarla con `Post` (ManyToOne)
- Actualizar DTOs y mapper para incluir la categoría

**Conceptos:**
- Módulo 7: @ManyToOne, @JoinColumn
- Módulo 6: repositorio y query methods para Category

**Preguntas guía:**
- ¿En qué entidad va la anotación @ManyToOne y en cuál el @OneToMany?
- ¿Qué es @JoinColumn y qué columna genera en la base de datos?
- ¿Qué pasa si intentás guardar un Post con una Category que no existe?
- ¿Cómo mostrás la categoría en el PostResponse sin exponer la entidad?

**Errores comunes:**
- Poner @ManyToOne en la entidad incorrecta
- Exponer la entidad Category dentro de PostResponse en lugar de un DTO
- No validar que la categoría exista antes de asignarla al post

**Criterio de finalización:**
- Podés crear un Post asignándole una Category existente y el response
  muestra el nombre de la categoría correctamente

---

### 🔹 Paso 5 — Entidad Tag y relación ManyToMany

**Objetivo técnico:**
- Crear la entidad `Tag` y relacionarla con `Post` (ManyToMany)
- Manejar la tabla de enlace intermedia

**Conceptos:**
- Módulo 7: @ManyToMany, @JoinTable, tabla de enlace

**Preguntas guía:**
- ¿Cómo se representa una relación ManyToMany en base de datos?
- ¿Qué anotación genera esa tabla intermedia en JPA?
- ¿Qué pasa con los tags si eliminás un post?
- ¿En qué lado de la relación ponés el @JoinTable?

**Errores comunes:**
- Generar bucles infinitos en la serialización JSON
- No definir correctamente el @JoinTable y que JPA genere nombres incorrectos
- Olvidar que en ManyToMany la cascada hay que pensarla bien antes de aplicarla

**Criterio de finalización:**
- Un Post puede tener múltiples Tags y los tags aparecen en el response
  sin bucles de serialización

---

### 🔹 Paso 6 — Entidad Comment y relación OneToMany

**Objetivo técnico:**
- Crear la entidad `Comment` relacionada con `Post` (OneToMany)
- Implementar CRUD de comentarios anidado bajo el post

**Conceptos:**
- Módulo 7: @OneToMany, @ManyToOne bidireccional, cascada, @JsonIgnore

**Preguntas guía:**
- ¿Cómo estructurás la URL para comentarios de un post? (pista: recursos anidados)
- ¿Qué pasa con los comentarios si eliminás el post? ¿Cómo lo configurás?
- ¿Cómo evitás el bucle infinito entre Post y Comment en la serialización?
- ¿Tiene sentido exponer todos los comentarios dentro del PostResponse?

**Errores comunes:**
- No configurar la cascada correctamente al eliminar un post
- Bucle infinito entre Post → Comment → Post en la serialización JSON
- Endpoint de comentarios que no valida que el post exista

**Criterio de finalización:**
- Podés agregar comentarios a un post, listarlos y eliminarlos. Al eliminar
  un post sus comentarios se eliminan en cascada.

---

### 🔹 Paso 7 — GlobalExceptionHandler y validaciones finales

**Objetivo técnico:**
- Completar el manejo de errores con todos los casos del proyecto
- Asegurar que todas las validaciones del request devuelvan errores claros

**Conceptos:**
- Módulo 3: @RestControllerAdvice, excepciones personalizadas
- Módulo 8: manejo de MethodArgumentNotValidException

**Preguntas guía:**
- ¿Qué excepciones necesitás capturar en este proyecto?
- ¿Cómo capturás los errores de validación de @Valid y los devolvés
  de forma legible?
- ¿Qué status HTTP corresponde a un error de validación?

**Errores comunes:**
- No capturar MethodArgumentNotValidException y dejar que Spring devuelva
  su error por defecto
- GlobalExceptionHandler que devuelve stacktraces en lugar de mensajes claros

**Criterio de finalización:**
- Todos los errores (404, 400, 500) devuelven la estructura estandarizada:
  `timestamp`, `status`, `message`, `path`

---

## 6. REGLAS DE EJECUCIÓN PARA LA IA

- No dar código completo si el usuario no mostró un intento previo
- Guiar con preguntas antes de cualquier explicación de código
- Validar el razonamiento del usuario antes de avanzar al siguiente paso
- No saltar pasos aunque el usuario lo pida — podés explicar por qué el
  orden importa
- Si el usuario comete un error técnico, señalarlo explícitamente y exigir
  confirmación antes de continuar
- Prestar especial atención al mapper y los DTOs — área de dificultad
  identificada en el proyecto anterior

---

## 7. ESTADO DEL PROYECTO

<progress_log>
Paso actual: 1 — Setup del proyecto y entidad Post
Pasos completados: ninguno
Errores recurrentes: mapper y DTOs (dificultad del proyecto anterior)
Bloqueos actuales: ninguno
</progress_log>