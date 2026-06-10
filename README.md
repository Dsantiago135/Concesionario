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
  
  <img width="468" height="417" alt="{49EBDFAF-0D73-49E1-A0F0-2FA801512531}" src="https://github.com/user-attachments/assets/8ad879be-77ee-4b09-accd-6a4dbe0a8fec" />
* Modelo Físico
  
  <img width="465" height="416" alt="{65CF2DBB-DB43-40EA-ABD8-B10F809EF7D9}" src="https://github.com/user-attachments/assets/4f1ee38d-33a0-44fa-967a-72c485a2530b" />
* Diagrama de Clases
 ** <img width="667" height="361" alt="{361586BA-11E0-40CF-890E-920583B6450B}" src="https://github.com/user-attachments/assets/54ea4f99-e0be-4c8e-80d0-729e251001eb" />
 ** <img width="407" height="417" alt="{972BDC71-26D6-4150-9FBD-319CCD5AB2DD}" src="https://github.com/user-attachments/assets/fefb7569-456d-4a27-b43f-464017ea1f7a" />
 ** <img width="674" height="427" alt="{9D3651B0-558B-4916-8F51-6AE1A2C50CE7}" src="https://github.com/user-attachments/assets/be92b750-312a-4b37-8555-24ff87d1d46b" />

* Arquitectura por Capas

  <img width="216" height="284" alt="{5A9EB695-A02D-46C8-85DC-C6F2C0306C08}" src="https://github.com/user-attachments/assets/dbaea6af-e72b-4bdc-a115-caf82e2ffd7b" />

