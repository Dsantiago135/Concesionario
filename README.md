# 🚗 Sistema de Gestión de Concesionarias de Vehículos

## 📌 Descripción del Proyecto

Sistema de información desarrollado para administrar una empresa dedicada a la comercialización de vehículos mediante múltiples concesionarias.

La aplicación centraliza la gestión de clientes, empleados, vehículos, unidades físicas, ventas y metas comerciales, permitiendo mantener la integridad de la información y optimizar los procesos operativos de la organización.

---

# 🎯 Objetivos

## Objetivo General

Desarrollar un sistema que permita gestionar de manera eficiente las operaciones comerciales de una red de concesionarias de vehículos, garantizando la consistencia, disponibilidad e integridad de los datos.

## Objetivos Específicos

* Centralizar la información de todas las concesionarias.
* Gestionar el inventario de vehículos y unidades disponibles.
* Registrar y administrar procesos de venta.
* Gestionar empleados y clientes.
* Administrar metas comerciales.
* Generar información para análisis y control de gestión.

---

# 🧠 Problemática

La gestión independiente de cada concesionaria genera dificultades como:

* Inconsistencias en el inventario.
* Información desactualizada después de una venta.
* Duplicidad de datos.
* Dificultad para consolidar información comercial.
* Limitaciones para realizar seguimiento al desempeño de vendedores y concesionarias.

Para solucionar estas problemáticas se implementa un sistema centralizado respaldado por una base de datos Oracle.

---

# 🏗️ Funcionalidades Implementadas

## Gestión de Concesionarias

* Registro de concesionarias.
* Consulta y actualización de información.
* Control de estado.

## Gestión de Clientes

* Registro de clientes.
* Consulta individual y general.
* Actualización de datos.
* Control de clientes activos e inactivos.

## Gestión de Empleados

* Registro de vendedores y gerentes.
* Asociación de empleados a concesionarias.
* Validación de roles.
* Administración de estados.

## Gestión de Vehículos

* Registro de modelos de vehículos.
* Gestión de marca, modelo y características.

## Gestión de Unidades

* Administración de unidades físicas.
* Asociación a concesionarias.
* Control de disponibilidad.

## Gestión de Ventas

* Registro de ventas.
* Asociación con cliente, vendedor y unidad.
* Control de estados de venta:

  * confirmed
  * cancelled
  * inprogress

## Gestión de Metas Comerciales

* Asignación de metas a empleados o concesionarias.
* Manejo de metas:

  * monthly
  * quarterly
  * yearly

---

# 🧱 Arquitectura del Sistema

El proyecto sigue una arquitectura por capas:

```text
├── Controller
│   └── Fachada principal del sistema
│
├── Service
│   └── Lógica de negocio y validaciones
│
├── Repository
│   └── Acceso a datos y procedimientos Oracle
│
├── Model
│   └── Entidades del dominio
│
└── Error
    └── Manejo de excepciones personalizadas
```

---

# 📂 Estructura del Proyecto

```text
src
│
├── main
│   ├── java
│   │   └── concesionaria
│   │       ├── Controller
│   │       ├── Service
│   │       ├── Repository
│   │       ├── Model
│   │       └── Error
│   │
│   └── resources
│       └── application.yaml
│
└── test
```

```text
DataBase
│
├── DDL.sql
├── DML.sql
├── ejemplares.sql
│
├── PKGCUSTOMER.sql
├── PKGDEALERSHIP.sql
├── PKGEMPLOYEE.sql
├── PKGSALE.sql
├── PKGSALESGOAL.sql
├── PKGUNIT.sql
├── PKGVEHICLE.sql
└── PKGVALIDACIONES.sql
```

---

# 🗄️ Base de Datos

La solución utiliza Oracle Database como sistema gestor de base de datos.

## Entidades Principales

* Customer
* Dealership
* Employee
* Vehicle
* Unit
* Sale
* SalesGoal

## Características Implementadas

* Restricciones de integridad.
* Llaves primarias y foráneas.
* Validaciones mediante PL/SQL.
* Procedimientos almacenados.
* Paquetes Oracle.
* Manejo de estados.
* Automatización de procesos mediante triggers.
* Consultas de gestión mediante vistas.

---

# ⚙️ Tecnologías Utilizadas

* Java
* Spring Boot
* Maven
* Oracle Database
* PL/SQL
* PowerDesigner
* Git
* GitHub

---

# 🚀 Ejecución del Proyecto

## Requisitos

* JDK 17 o superior
* Maven
* Oracle Database
* SQL Developer (opcional)

## Pasos

1. Crear la base de datos Oracle.
2. Ejecutar el script `DDL.sql`.
3. Ejecutar los paquetes PL/SQL.
4. Ejecutar los scripts de inserción de datos.
5. Configurar la conexión en `application.yaml`.
6. Compilar y ejecutar:

```bash
mvn clean install
mvn spring-boot:run
```

---

# 📊 Modelos del Sistema

* Modelo Conceptual
* Modelo Físico
* Diagrama de Clases
* Diagrama de Métodos
* Arquitectura por Capas
