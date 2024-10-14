# Stock Service App

Stock Service App es una API REST desarrollada con Spring Boot que permite gestionar información sobre stocks
financieros. Proporciona endpoints para crear, leer, actualizar y eliminar (CRUD) información de acciones financieras.

## Tecnologías Utilizadas

- **Java 17** o superior
- **Maven** 3.6 o superior
- **Spring Boot** 3.x
- **Postman** (opcional, para probar la API)
- **Swagger** para documentación
- **Docker** 
- **Jpa** para persistencia

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/teamcubation/byma-sbx-acciones.git
   ```

2. **Configurar variables de entorno (Opcional)**:

   Puedes configurar las variables de entorno como el puerto, el nombre de la aplicación y el nivel de logging. Por ejemplo:
   ```bash
   export APPLICATION_NAME=StockServiceApp
   export APPLICATION_PORT=8080
   export LOGGING_LEVEL=INFO
   ```

3. **Compilar el proyecto**:

   Si usas Maven, ejecuta:
   ```bash
   mvn clean install
   ```

   Si usas Gradle, ejecuta:
   ```bash
   ./gradlew build
   ```

4. **Configurar el archivo de propiedades**:

   Puedes modificar las propiedades del proyecto en `src/main/resources/application.properties`. Aquí un ejemplo básico:
   ```properties
   spring.application.name=${APPLICATION_NAME:StockServiceApp}
   server.port=${APPLICATION_PORT:8080}
   logging.level.root=${LOGGING_LEVEL:INFO}
   ```

5. **Ejecutar la aplicación**:

   Para ejecutar el proyecto con Maven, usa:
   ```bash
   mvn spring-boot:run
   ```

   O con Gradle:
   ```bash
   ./gradlew bootRun
   ```

6. **Acceder a la API**:

   Una vez que la aplicación esté corriendo, puedes acceder a los endpoints de la API en `http://localhost:8080/stock/`.

### Opcional: Importar en Postman

Para probar los endpoints de la API, puedes importar el archivo JSON de Postman, que contiene todos los endpoints preconfigurados. Este archivo se encuentra en el proyecto.

## Endpoints
La API proporciona los siguientes endpoints para gestionar la información de stocks. Todos los endpoints están prefijados con
`/stock` y permiten realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar).

### **1. Obtener todos los stocks**
- **Método**: `GET`
- **Ruta**: `/stock/`
- **Descripción**: Obtiene la lista de todos los stocks disponibles.
- **Ejemplo de respuesta**:
    ```json
    [
      {
        "id": 1,
        "name": "Apple",
        "price": 150.0,
        "dividend": 10.5
      },
      {
        "id": 2,
        "name": "Tesla",
        "price": 700.0,
        "dividend": 1.5
      }
    ]
    ```

### **2. Obtener un stock por ID**
- **Método**: `GET`
- **Ruta**: `/stock/{id}`
- **Descripción**: Obtiene los detalles de un stock específico por su ID.
- **Ejemplo de respuesta**:
    ```json
    {
      "id": 1,
      "name": "Apple",
      "price": 150.0,
      "dividend": 16.5
    }
    ```

### **3. Crear un nuevo stock**
- **Método**: `POST`
- **Ruta**: `/stock/`
- **Descripción**: Crea un nuevo stock en el sistema.
- **Ejemplo de request**:
    ```json
    {
      "name": "Amazon",
      "price": 3000.0,
      "dividend": 16.5
    }
    ```
- **Ejemplo de respuesta**:
    ```json
    {
      "id": 3,
      "name": "Amazon",
      "price": 3000.0,
      "dividend": 16.5
    }
    ```

### **4. Eliminar un stock por ID**
- **Método**: `DELETE`
- **Ruta**: `/stock/{id}`
- **Descripción**: Elimina un stock específico del sistema por su ID.


### **5. Actualizar un stock por ID**
- **Método**: `PUT`
- **Ruta**: `/stock/{id}`
- **Descripción**: Actualiza la información de un stock existente.
- **Ejemplo de request**:
    ```json
    {
      "name": "Apple Inc.",
      "price": 155.0
    }
    ```
- **Ejemplo de respuesta**:
    ```json
    {
      "id": 1,
      "name": "Apple Inc.",
      "price": 155.0,
      "dividend": 1.5
    }
    ```

### Importación de Endpoints en Postman

Los endpoints también han sido exportados en un archivo JSON de Postman que se encuentra en el proyecto. Puedes importar este archivo en Postman para probar los endpoints directamente.