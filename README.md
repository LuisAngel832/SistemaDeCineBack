# 🎬 Sistema de Gestión de Cine – Backend

Este backend fue desarrollado con **Spring Boot** y proporciona una API REST para gestionar funciones de cine, boletos, compras, películas y estadísticas.

---

## 📦 Tecnologías

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL / MySQL (ajustable)
- Maven

---

## 🚀 Cómo ejecutar

```bash
# Clonar el repositorio
git clone https://github.com/LuisAngel832/SistemaDeCineBack.git

# Entrar al proyecto
cd SistemaDeCineBack

# Ejecutar
./mvnw spring-boot:run
```

Asegúrate de tener configurado `application.properties` con tus credenciales de base de datos.

---

## 🧩 Estructura del proyecto

```
src/
 └── main/
     ├── java/com/example/metrix/
     │   ├── controller/
     │   ├── service/
     │   ├── repository/
     │   ├── model/
     │   └── DTO/
     └── resources/
         └── application.properties
```

---

## 📡 Endpoints Principales

### 🎟️ Boletos

| Método   | Endpoint                             | Descripción                                 |
|----------|--------------------------------------|---------------------------------------------|
| GET      | `/api/boletos/cantidad-boletos/{id}` | Cantidad de boletos vendidos por función    |
| GET      | `/api/boletos/{id}/boletos`          | Lista de boletos vendidos de una función    |
| DELETE   | `/api/boletos/cancelar-boleto/{id}`  | Cancela un boleto                           |

### 🛒 Compras

| Método   | Endpoint                   | Descripción                           |
|----------|----------------------------|---------------------------------------|
| POST     | `/api/compra`              | Registrar una nueva compra            |
| DELETE   | `/api/compra/{idCompra}`   | Cancelar una compra existente         |

### 🎬 Funciones

| Método   | Endpoint                                      | Descripción                                             |
|----------|-----------------------------------------------|---------------------------------------------------------|
| GET      | `/api/funciones`                              | Obtener todas las funciones                            |
| GET      | `/api/funciones/{id}`                         | Obtener detalles de una función                        |
| POST     | `/api/funciones`                              | Registrar nueva función con película                   |
| POST     | `/api/funciones/asociar?idPelicula={id}`      | Asociar una función a una película                     |
| PUT      | `/api/funciones/actualizar/{id}`              | Actualizar una función                                 |
| DELETE   | `/api/funciones/eliminarFuncion/{id}`         | Eliminar una función si no tiene boletos vendidos      |
| DELETE   | `/api/funciones/borrarTodasFunciones`         | Eliminar todas las funciones                           |

### 🎥 Películas

| Método   | Endpoint                             | Descripción                      |
|----------|--------------------------------------|----------------------------------|
| GET      | `/api/peliculas/todas-peliculas`     | Obtener todas las películas      |

### 📈 Datos históricos

| Método   | Endpoint       | Descripción                                |
|----------|----------------|--------------------------------------------|
| GET      | `/{id}`         | Obtener datos históricos por función        |

---

## 📌 Ejemplo de JSON (compra)

```json
{
  "funcion": {
    "id": 1
  },
  "boletos": [
    { "asiento": "A1" },
    { "asiento": "A2" }
  ]
}
```

---

## 🧠 Notas

- Todos los endpoints usan `@CrossOrigin` para permitir conexión con el frontend.
- Se manejan errores con `IllegalArgumentException` y códigos de estado HTTP apropiados.
- La base de datos relacional permite controlar relaciones entre películas, funciones, boletos y compras.

---

## 👨‍💻 Autor

- **Luis Ángel**
- [GitHub](https://github.com/LuisAngel832)
