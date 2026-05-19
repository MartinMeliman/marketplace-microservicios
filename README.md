# marketplace-microservicios
Proyecto Semestral DSY1103 - Marketplace Microservicios 

## Integrantes del Equipo
Ignacio Lapierre 
y
Martin Meliman 

## Descripción del Proyecto
Sistema de Marketplace (similar a MercadoLibre) basado en arquitectura de microservicios independientes. Cada microservicio tiene su propia base de datos MySQL, expone una API REST completa y se comunica con otros servicios a través de FeignClient cuando necesita datos de otro dominio.
El sistema permite gestionar usuarios, productos, categorías, pedidos, carrito de compras, pagos, envíos, reseñas y notificaciones de forma distribuida e independiente.

---

Microservicios Implementados

| # | Microservicio | Puerto | Base de Datos | Llama a |
|---|--------------|--------|---------------|---------|
| 1 | ms-usuarios | 8081 | db_usuarios | — |
| 2 | ms-autenticacion | 8082 | db_autenticacion | ms-usuarios |
| 3 | ms-productos | 8083 | db_productos | ms-categorias |
| 4 | ms-categorias | 8084 | db_categorias | — |
| 5 | ms-pedidos | 8085 | db_pedidos | ms-productos |
| 6 | ms-carrito | 8086 | db_carrito | ms-productos |
| 7 | ms-pagos | 8087 | db_pagos | — |
| 8 | ms-envios | 8088 | db_envios | — |
| 9 | ms-resenas | 8089 | db_resenas | ms-productos |
| 10 | ms-notificaciones | 8090 | db_notificaciones | — |

---

## Funcionalidades Implementadas

### ms-usuarios
- CRUD completo de usuarios con roles (ADMIN, VENDEDOR, COMPRADOR)
- Validación de email único
- Soft delete (desactivar usuario sin eliminar)
- Búsqueda por nombre

### ms-autenticacion
- Login con generación de token de sesión
- Logout e invalidación de token
- Validación de token activo
- Bloqueo por intentos fallidos (máximo 5 en 15 minutos)

### ms-productos
- CRUD completo de productos con SKU único
- Búsqueda por nombre y por categoría
- Descuento de stock al confirmar pedido
- Enriquecimiento con nombre de categoría via FeignClient

### ms-categorias
- CRUD de categorías con soporte de subcategorías
- Árbol jerárquico de categorías
- 7 categorías iniciales cargadas automáticamente

### ms-pedidos
- Creación de pedidos con múltiples items
- Cálculo automático del total
- Verificación de existencia de productos via FeignClient
- Descuento de stock automático al confirmar
- Gestión de estados: PENDIENTE → PAGADO → ENVIADO → ENTREGADO → CANCELADO

### ms-carrito
- Carrito de compras por usuario
- Agregar, modificar y eliminar items
- Verificación de stock disponible via FeignClient
- Precio congelado al momento de agregar

### ms-pagos
- Registro de pagos por pedido
- Un solo pago por pedido (regla de negocio)
- Gestión de estados: PENDIENTE → APROBADO → RECHAZADO
- No permite modificar un pago ya APROBADO

### ms-envios
- Registro de envíos con código de seguimiento automático
- Historial de estados del envío
- Estados: PREPARANDO → EN_CAMINO → ENTREGADO

### ms-resenas
- Reseñas de productos con rating 1-5
- Una reseña por usuario por producto
- Cálculo de promedio de rating
- Verificación de existencia del producto via FeignClient

### ms-notificaciones
- Registro de notificaciones por tipo
- Endpoints para bienvenida, pago, envío y pedido
- Historial de notificaciones por usuario

---

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Data JPA + Hibernate**
- **Spring Cloud OpenFeign**
- **Bean Validation (JSR 380)**
- **MySQL 8** (via Laragon)
- **Lombok**
- **SLF4J para logs**
- **Postman** para pruebas REST

---

La comunicación se implementó con **FeignClient**. Solo se conectan los microservicios que necesitan datos de otro dominio:

| Quien llama | A quien llama | Para qué |
|-------------|---------------|----------|
| ms-autenticacion | ms-usuarios | Verificar que el usuario existe al hacer login |
| ms-productos | ms-categorias | Obtener el nombre de la categoría |
| ms-carrito | ms-productos | Verificar stock y precio antes de agregar |
| ms-pedidos | ms-productos | Verificar producto y descontar stock |
| ms-resenas | ms-productos | Verificar que el producto existe |

---
