
# 📄 README de Cambios - Proyecto Covid19Stats

## ✅ Descripción General
Este proyecto consume una API pública de estadísticas de COVID-19 para Estados Unidos (USA) y almacena los datos en base de datos.  
Se realizaron mejoras aplicando principios SOLID y convenciones de codificación recomendadas.

---

## 🚀 Mejoras Implementadas

### 1. Control de Ejecución de Hilo Automático
- Se agregó un control previo para evitar ejecuciones repetidas del hilo (`AutomaticThread`) por país y fecha.
- Se creó una nueva entidad `ExecutedReport` y su repositorio correspondiente `ExecutedReportRepository`.
- Antes de ejecutar la recolección de datos, el hilo consulta la tabla `executed_reports`:
  - **Si ya existe** el registro → Omite la ejecución y registra en logs.
  - **Si no existe** → Ejecuta normalmente y guarda un nuevo registro.
- Se parametrizó la fecha de ejecución (`covid.report.date`) en el archivo `application.properties`.

---

### 2. Consulta de Datos por Fecha e ISO
- Se implementó el método `getReportsByDateAndCountry` en el servicio `ReportService`.
- Este método:
  - Recibe una **fecha** y un **código ISO de país**.
  - Consulta los reportes almacenados.
  - Agrupa los resultados en un `TreeMap<String, Report>`, eliminando duplicados automáticamente.
  - Ordena los datos por nombre de provincia y muestra la información en consola.

---

## 🛠️ Tecnologías Utilizadas
- Java 17
- Spring Boot
- Maven
- JPA / Hibernate
- MariaDB / MySQL

---

## 🧠 Principios Aplicados
- **Single Responsibility Principle (SRP)**: Separación clara de responsabilidades en servicios, entidades y repositorios.
- **Open-Closed Principle (OCP)**: Se agregó funcionalidad sin modificar la estructura existente.

---

## 📋 Consideraciones Adicionales
- La tabla `executed_reports` debe ser creada previamente en la base de datos.
- No se permiten duplicados en la combinación de `execution_date` y `country_iso`.

---

## 👨‍💻 Autor
Proyecto adaptado por: **[Tu Nombre Aquí]**  
Fecha: **Abril 2025**
