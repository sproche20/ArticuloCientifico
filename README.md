# Estrategia de Migración de API REST hacia GraphQL para Empresas en Crecimiento mediante Arquitectura Hexagonal

Este repositorio contiene el código fuente y el entorno experimental desarrollado para el artículo de investigación titulado **"Estrategia de Migración de API REST hacia GraphQL para Empresas en Crecimiento mediante Arquitectura Hexagonal"**.

El objetivo del proyecto es demostrar cómo una arquitectura desacoplada basada en **Puertos y Adaptadores** facilita la transición tecnológica de servicios REST tradicionales hacia grafos de datos dinámicos con GraphQL, permitiendo a las empresas escalar su infraestructura sin comprometer la lógica de negocio.

## 🏗️ Arquitectura del Sistema

La aplicación implementa **Arquitectura Hexagonal** para asegurar que el núcleo del negocio sea independiente de los frameworks y de la capa de entrega:

*   **Dominio (Domain):** Definición de modelos (Java Records) y contratos de entrada/salida (Ports).
*   **Aplicación (Application):** Orquestación de lógica de negocio y casos de uso.
*   **Infraestructura (Infrastructure):** 
    *   **Adaptadores de Entrada:** Controladores REST y Resolvers de GraphQL compartiendo la misma lógica de negocio.
    *   **Adaptadores de Salida:** Persistencia con JPA y PostgreSQL.

## 🚀 Tecnologías

*   **Java 21**
*   **Spring Boot 3.3.x**
*   **Spring GraphQL**
*   **PostgreSQL 16** (vía Docker)
*   **k6** (Herramienta de Benchmarking)

## 🛠️ Configuración y Ejecución

1.  **Base de Datos:** Levantar el contenedor de PostgreSQL:
    ```bash
    docker-compose up -d
    ```
2.  **Aplicación:** Ejecutar el proyecto Spring Boot:
    ```bash
    mvn spring-boot:run
    ```
    La API estará disponible en `http://localhost:8081`.

## 📊 Escenario Experimental (Data Seeding)

Para validar la eficiencia de GraphQL frente a REST en términos de *Over-fetching* y latencia, se han expuesto endpoints de utilidad para generar volúmenes masivos de datos:

*   **Clientes:** `POST /api/rest/clientes/generar?cantidad=50000`
*   **Productos:** `POST /api/rest/productos/generar?cantidad=10000`
*   **Pedidos:** `POST /api/rest/pedidos/generar?cantidad=200000&maxClienteId=50000`
*   **Detalles:** `POST /api/rest/detalles-pedido/generar?cantidad=500000&maxPedidoId=200000&maxProductoId=10000`

## 🧪 Pruebas de Rendimiento con k6

Los scripts de prueba se encuentran en la carpeta `k6-tests/`. El benchmark se ha centrado en los módulos de **Clientes, Productos, Pedidos y Detalles de Pedido**, ya que representan el mayor impacto en la transferencia de datos y complejidad de consultas anidadas.

**Nota:** El módulo de **Facturas** se incluye para demostrar la integridad funcional y la capacidad de extensión de la arquitectura, pero se excluyó del benchmark experimental debido a que su comportamiento de carga es similar al del módulo de Pedidos (relación 1:1), no aportando variaciones significativas a los resultados de eficiencia de GraphQL vs REST.

Cada script contiene:
1.  **Función Default:** Ejecuta la consulta GraphQL para medir la eficiencia del grafo.
2.  **Función Complementaria:** Endpoint REST para realizar la comparativa de rendimiento.

Ejemplo de ejecución:
```bash
k6 run k6-tests/pedidos-test.js
```

## 📝 Conclusiones del Estudio

El experimento busca evidenciar cómo GraphQL reduce el tamaño de los paquetes de datos transmitidos (*Payload*) y cómo la arquitectura hexagonal permitió una migración transparente, manteniendo el 100% de la lógica de negocio intacta durante la transición de protocolos.

---
*Este proyecto forma parte de una investigación académica sobre arquitecturas evolutivas.*
