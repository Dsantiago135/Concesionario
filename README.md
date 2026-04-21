# 🚗 Sistema de Gestión de Concesionarias de Vehículos

## 📌 Descripción del Proyecto

Este proyecto consiste en el diseño y desarrollo de un sistema de información orientado a la gestión de una empresa dedicada a la comercialización de vehículos a través de múltiples concesionarias dentro de una misma ciudad.

El sistema busca centralizar la información relacionada con inventario, ventas, empleados y clientes, con el objetivo de mejorar la consistencia de los datos, optimizar la toma de decisiones y facilitar el control operativo del negocio.

---

## 🎯 Objetivos

### Objetivo General

Desarrollar una solución que permita gestionar de manera eficiente las operaciones de una empresa de venta de vehículos, garantizando integridad, consistencia y disponibilidad de la información.

### Objetivos Específicos

* Centralizar la información de múltiples concesionarias.
* Controlar el inventario de vehículos en tiempo real.
* Registrar y gestionar las ventas realizadas.
* Administrar la información de empleados y clientes.
* Permitir la asignación y seguimiento de metas comerciales.
* Generar información útil para análisis de rendimiento.

---

## 🧠 Problemática

Actualmente, cada concesionaria maneja su información de manera independiente, lo que genera:

* Inconsistencias en el inventario de vehículos.
* Falta de actualización inmediata tras una venta.
* Dificultad para consolidar información de ventas.
* Problemas en la evaluación del rendimiento del personal.
* Limitaciones para el análisis del negocio.

---

## 🏗️ Alcance del Sistema

El sistema permitirá gestionar la información relacionada con:

* Concesionarias
* Vehículos (modelo)
* Unidades de vehículos (instancias físicas)
* Clientes
* Empleados (vendedores y gerentes)
* Ventas
* Metas comerciales

---

## 🧱 Arquitectura

El sistema se desarrolla bajo un enfoque de arquitectura por capas:

* **UI (Interfaz de Usuario):** Interacción con el usuario mediante aplicación de escritorio.
* **Controlador Único (Facade):** Punto de entrada que desacopla la interfaz de la lógica del sistema.
* **Dominio:** Contiene las reglas de negocio y lógica principal.
* **Servicios:** Proveen operaciones reutilizables para el dominio.
* **Sistema:** Incluye la persistencia de datos y tecnologías externas.

---

## 🗄️ Modelo de Datos

El sistema se basa en un modelo relacional que contempla:

* Separación entre vehículo (modelo) y unidad (instancia física)
* Control de disponibilidad mediante estado de las unidades
* Relaciones entre ventas, clientes y empleados
* Estructura preparada para consultas y reportes

Además, se contemplan mecanismos como:

* Triggers para control automático de estados
* Funciones para cálculos derivados
* Procedimientos para operaciones complejas

---

## 📋 Reglas de Negocio Principales

* Cada concesionaria debe tener un gerente asignado.
* Un vendedor pertenece a una sola concesionaria.
* Una unidad de vehículo solo puede venderse una vez.
* Cada venta debe estar asociada a un cliente y un vendedor.
* El estado de una unidad se actualiza automáticamente al realizar una venta.
* Un cliente puede realizar múltiples compras.

---

## 🚀 Tecnologías (Sujetas a ajuste)

* Lenguaje: Java
* Interfaz: 
* Backend: Spring Boot (posible)
* Base de datos: 
* Contenedores: Docker (para base de datos)

---

## 📎 Notas

Este proyecto tiene fines académicos y de portafolio, con un enfoque en buenas prácticas de diseño de software, arquitectura por capas y modelado de bases de datos.

---

## 👨‍💻 Autor

Desarrollado por Dsantiago135
