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
o	JavaApplication2.java → clase principal del sistema.
o	Cliente.java → entidad cliente con atributos y métodos para administrar sus datos.
o	Mascota.java → entidad mascota con historial de servicios.
o	Servicio.java → clase base para modelar servicios veterinarios.
o	Peluqueria.java y ConsultaGeneral.java → clases que heredan de Servicio y sobreescriben métodos.
o	DatosCSV.java → maneja la persistencia en archivos CSV.
o	Excepciones: ClienteNoEncontrado.java, MascotaNoEncontrada.java.


### 4. Ejecutar la aplicación
- Haz clic derecho sobre la clase `JavaApplication2.java` dentro del paquete `javaapplication2`.  
- Selecciona `Run File` o `Ejecutar archivo`.  
- Se abrirá una consola donde podrás usar el menú para interactuar con la aplicación.

---

## Uso
El menú ofrece las siguientes opciones interactivas:
•	Agregar cliente → crea un nuevo cliente con sus datos básicos.
•	Registrar mascota → vincula una mascota a un cliente existente.
•	Mostrar mascotas del cliente → lista mascotas asociadas a un RUT.
•	Agendar servicio → programa consultas o peluquería para una mascota.
•	Consultar historial de servicios → muestra las atenciones recibidas.
•	Editar y eliminar clientes o mascotas → actualiza o borra registros.
•	Filtrar mascotas → búsqueda por especie y rango de peso.
•	Búsqueda global → busca mascotas por nombre o ID.
•	Generar reporte → crea un informe en TXT con toda la información.
•	Salir → guarda los datos en el CSV y genera un reporte final.


---
Funcionalidades principales
El sistema cuenta con un menú interactivo que permite realizar diversas operaciones:
1. Gestión de Clientes
•	Registrar un nuevo cliente con datos como: RUT, nombre, dirección, teléfono y correo.
•	Editar información de clientes existentes.
•	Eliminar clientes del sistema (con confirmación).
•	Listar clientes registrados junto con sus mascotas asociadas.
2. Gestión de Mascotas
•	Agregar una o varias mascotas por cliente.
•	Editar los datos de las mascotas (nombre, especie, raza, edad, peso).
•	Eliminar mascotas de un cliente.
•	Filtrar mascotas por especie y/o rango de peso.
•	Listar todas las mascotas de un cliente.
3. Gestión de Servicios Veterinarios
•	Agendar servicios como consultas médicas y peluquería.
•	Registrar fecha, profesional encargado y observaciones.
•	Mantener un historial de servicios por mascota.
•	Consultar historial de una mascota o de todas las mascotas.
4. Persistencia de la Información
•	Manejo de archivos CSV mediante la clase DatosCSV, que permite cargar y guardar información.
•	Generación de reportes automáticos en formato TXT, los cuales se guardan al salir del programa.
5. Reportes
•	Reporte de clientes, mascotas y servicios.
•	Historial general con detalle de cada atención realizada.
•	Reporte final automático al cerrar la aplicación.
6. Búsquedas Globales
•	Permite buscar mascotas en todo el sistema por nombre o ID, mostrando los resultados asociados al cliente dueño.
7. Manejo de Excepciones
•	Uso de excepciones personalizadas como ClienteNoEncontrado y MascotaNoEncontrada.
•	Bloques try-catch en métodos clave (agregarCliente, agregarMascota, etc.) para robustez y control de errores.

## Notas
- No cambies la estructura de carpetas ni los nombres de paquetes para evitar errores de compilación.  
- El programa solo corre cuando se está en Java, es decir, si uno abre otra página, archivo o se va de la aplicación, este deja de correr y se cierra el programa sin guardar nada.
- El archivo clientes.csv no debe estar abierto en Excel mientras se ejecuta el programa, de lo contrario no se guardarán los cambios.
- Los servicios registrados mientras corran el programa, solo se guardarán en el reporte txt. Una vez que corra el sistema nuevamente y se genere otro reporte, este no contendrá los servicios pasados.
- El programa fue probado con datos precargados (clientes y mascotas) que facilitan la validación de las funciones sin necesidad de ingresar información manual cada vez.


---
¡Gracias por usar el sistema!

¡Gracias por usar el sistema!
