# Sistema de Gestión de Clientes y Mascotas (Java)

## Descripción
Este proyecto consiste en una aplicación desarrollada en Java que permite la gestión integral de clientes, sus mascotas y los servicios veterinarios que reciben en una clínica o centro de cuidado animal.

El sistema fue creado con un enfoque modular y escalable, utilizando conceptos de programación orientada a objetos (POO), colecciones de Java (HashMap y ArrayList), manejo de excepciones personalizadas, así como funcionalidades de persistencia de datos en archivos CSV y generación de reportes en TXT.

La aplicación está diseñada para funcionar tanto en modo consola como con ventanas gráficas simples (Swing) que guían al usuario en la interacción.
Su objetivo principal es ofrecer una herramienta sencilla pero completa para la administración de información veterinaria, garantizando orden, accesibilidad y trazabilidad 

---

## Requisitos previos
- Java JDK 8 o superior instalado:
- NetBeans IDE instalado:  

---

## Instrucciones para descargar y ejecutar el proyecto en NetBeans

### 1. Descargar el proyecto
- Descarga el archivo ZIP del proyecto o clona el repositorio.  
- Descomprime el archivo ZIP en una carpeta de tu preferencia (ejemplo: `C:\JavaProjects\SistemaMascotas`).

### 2. Abrir el proyecto en NetBeans
- Abre NetBeans.  
- Ve a `Archivo` > `Abrir proyecto`.  
- Navega hasta la carpeta donde descomprimiste el proyecto (ejemplo: `SistemaMascotas`).  
- Selecciona la carpeta raíz del proyecto (donde está la carpeta `src`).  
- Haz clic en `Abrir proyecto`.

### 3. Verificar estructura del proyecto
- En el panel de proyectos de NetBeans, expande la carpeta `Source Packages` o `Paquetes de origen`.  
- Debe estar visible el paquete `javaapplication2` con las clases:
- `JavaApplication2.java` → clase principal.  
- `Cliente.java`, `Mascota.java` → entidades.  
- `Servicio.java` (abstracta).  
- `Peluqueria.java`, `ConsultaGeneral.java` → heredan de `Servicio`.  
- `DatosCSV.java` → persistencia en CSV.  
- Excepciones: `ClienteNoEncontrado.java`, `MascotaNoEncontrada.java`.


### 4. Ejecutar la aplicación
- Haz clic derecho sobre la clase `JavaApplication2.java` dentro del paquete `javaapplication2`.  
- Selecciona `Run File` o `Ejecutar archivo`.  
- Se abrirá una consola donde podrás usar el menú para interactuar con la aplicación.

---

## Uso del sistema
El menú ofrece estas opciones:

- Agregar Cliente → crea un nuevo cliente.  
- Agregar Mascota → vincula mascota a un cliente.  
- Mostrar mascotas del cliente → lista mascotas asociadas a un RUT.  
- Agendar servicio → programa consulta o peluquería.  
- Mostrar historial de servicios → de una mascota o de todas.  
- Editar y eliminar clientes o mascotas → actualiza datos.  
- Filtrar mascotas → búsqueda por especie y rango de peso.  
- Búsqueda global → busca mascotas por nombre o ID.  
- Generar reporte → crea un informe en TXT.  
- Salir → guarda en CSV y genera reporte final.  

---

## Funcionalidades principales

### Gestión de Clientes
- Registro, edición y eliminación de clientes.  
- Listado de clientes con sus mascotas.  

### Gestión de Mascotas
- Agregar, editar y eliminar mascotas.  
- Filtrar por especie y rango de peso.  
- Listar todas las mascotas de un cliente.  

### Gestión de Servicios
- Agendar consultas médicas y peluquería.  
- Registrar fecha, profesional y observaciones.  
- Mantener historial por mascota.  

### Persistencia
- Guardado automático en CSV.  
- Reporte automático en TXT al cerrar el sistema.  

### Búsquedas Globales
- Buscar mascotas por nombre o ID, con su cliente asociado.  

### Manejo de Excepciones
- `ClienteNoEncontrado` y `MascotaNoEncontrada`.  
- Uso de **try-catch** en las operaciones críticas.  

---

## Notas importantes
- No cambiar la estructura de carpetas ni los nombres de paquetes.  
- El archivo `clientes.csv` no debe estar abierto en Excel mientras corre el programa.  
- Los servicios registrados solo se guardan en el **reporte TXT** (no en CSV).  
- Incluye datos de prueba precargados para facilitar validación.  

---


¡Gracias por usar el sistema!
