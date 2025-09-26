/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.util.*;
import javax.swing.*;
import javaapplication2.exceptions.ClienteNoEncontrado;
import javaapplication2.exceptions.MascotaNoEncontrada;


/**
 *
 * @author usuario
 */

//Clase principal donde se controla el menu y opercaiones para cliente, mascotas y servicios
public class JavaApplication2 {
    //mapa de clientes para almcenar usando rut como clave
    static final HashMap<String, Cliente> clientes = new HashMap<>();
    static final Veterinaria servicio = new Veterinaria(clientes);
    //contador para asignar ID unico a cada mascota
    static int mascotaId = 1;
    /**
     * @param args the command line arguments
     */
    //Metodo main donde cargamos datos, mostramos menu y guardamos la informacion 
    public static void main(String[] args) {
        
        clientes.putAll(DatosCSV.cargar());
        //agregarDatosIniciales();
        while(true) { 
            String[] opciones = {
                "Agregar Cliente",
                "Agregar Mascota",
                "Mostrar mascotas del cliente",
                "Agendar servicio",
                "Mostrar historial de servicios",
                "Editar Mascota",
                "Eliminar Mascota",
                "Filtrar Mascotas",
                "Editar Cliente",
                "Eliminar Cliente",
                "Buscar Mascota (global)",
                "Generar Reporte",
                "Salir"
            };
            
            String opcion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción",
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            if(opcion == null || opcion.equals("Salir")) break;
            switch (opcion){
                case "Agregar Cliente": agregarCliente(); break;
                case "Agregar Mascota": agregarMascota(); break;
                case "Mostrar mascotas del cliente": mostrarMascotaPorCliente(); break;
                case "Agendar servicio": agendarServicio(); break;
                case "Mostrar historial de servicios": mostrarHistorialServicios(); break;
                case "Editar Mascota": editarMascota(); break;
                case "Eliminar Mascota": eliminarMascota(); break;
                case "Filtrar Mascotas": filtrarMascotas(); break;
                case "Editar Cliente": editarCliente(); break;
                case "Eliminar Cliente": eliminarCliente(); break;
                case "Buscar Mascota (global)": buscarMascotaGlobal(); break;
                case "Generar Reporte": Reporte.generar(clientes); break;
                default: JOptionPane.showMessageDialog(null, "Opción inválida"); break; 
            }      
        }
        DatosCSV.guardar(clientes);
        Reporte.generar(clientes);
    }
    
    public static String pedirTextoObligatoria(String mensaje) {
        String input;
        while(true) {
            input = JOptionPane.showInputDialog(mensaje);
            if(input == null){
                return null;
            }
            if (!input.isBlank()){
                return input.trim();
            }
            JOptionPane.showMessageDialog(null,"El campo no puede estar vacío.");
        }
    }
    
    //permite registrar un nuevo cliente ingresando sus datos
    public static void agregarCliente() {
        try {
            String rut = pedirTextoObligatoria("RUT cliente: ");
            if(rut == null) return;
            String nombre = pedirTextoObligatoria("Nombre cliente: ");
            if(nombre == null) return;
            String telefono = pedirTextoObligatoria("Teléfono: ");
            if(telefono == null) return;
            String correo = pedirTextoObligatoria("Correo: ");
            if(correo == null) return;
            String direccion = pedirTextoObligatoria("Direccion: ");
            if(direccion == null) return;
            
            Cliente cliente = new Cliente(rut, nombre, telefono, correo, direccion);
            clientes.put(rut,cliente);
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
           
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //permite registrar una mascota a un cliente existente :0
    public static void agregarMascota() {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del dueño de la mascota: ");
            if(rut == null) return;
            Cliente cliente = clientes.get(rut);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            String nombreM = pedirTextoObligatoria("Nombre de la mascota: ");
            if(nombreM == null) return;
            String especie = pedirTextoObligatoria("Especie: ");
            if(especie == null) return;
            String raza = pedirTextoObligatoria("Raza: ");
            if(raza == null) return;
            String edadST = pedirTextoObligatoria("Edad: ");
            if(edadST == null) return;
            int edad = Integer.parseInt(edadST);
            String pesoST = pedirTextoObligatoria("Peso: ");
            if(pesoST == null) return;
            double peso = Double.parseDouble(pesoST);
            

            Mascota mascota = new Mascota(mascotaId++, nombreM, especie, raza, edad, peso);
            cliente.agregarMascota(mascota);
            JOptionPane.showMessageDialog(null, "Mascota agregada al cliente " + cliente.getNombre());
            
        }catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Edad/Peso inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar mascota", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    //muestra todas las mascotas de un cliente ingresando rut
    public static void mostrarMascotaPorCliente()  {
        try {
            String rut = pedirTextoObligatoria("Ingrese RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = clientes.get(rut);
            if(cliente == null){
                JOptionPane.showMessageDialog(null, "Cliente no encontrado :(");
                return;
            }
            List<Mascota> mascotas = cliente.getMascotas();

            if(mascotas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tiene mascotas registradas :0");
                return;
            }

            String[] mostrar = {"ID", "Nombre", "Especie", "Raza", "Edad", "Peso"};
            Object[][] datos = new Object[mascotas.size()][6];
            for (int i = 0; i < mascotas.size();i++) {
                Mascota m = mascotas.get(i);
                datos[i][0] = m.getId();
                datos[i][1] = m.getNombre();
                datos[i][2] = m.getEspecie();
                datos[i][3] = m.getRaza();
                datos[i][4] = m.getEdad();
                datos[i][5] = m.getPeso();
            }
            JTable table = new JTable(datos,mostrar);
            JScrollPane scroll = new JScrollPane(table);
            JOptionPane.showMessageDialog(null,scroll,"Mascotas de " + cliente.getNombre(), JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //agrega algunos datos por defecto al sistema para pruebas
    /*public static void agregarDatosIniciales(){
        Cliente c1 = new Cliente("22222222-2", "Emilia Perez", "900000000", "emi@perez", "av. argentina");
        c1.agregarMascota(new Mascota(mascotaId++, "Puppy"));
        c1.agregarMascota(new Mascota(mascotaId++, "Luna", "Perro" , "Pug" , 4, 5.5));
        
        clientes.put(c1.getRut(), c1);
    }*/

    //permite agendar un servicio para una mascota de un cliente
    public static void agendarServicio() {
        try {
            String rut = pedirTextoObligatoria("RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = servicio.getClienteOrThrow(rut);

            String idSt = pedirTextoObligatoria("ID mascota: ");
            if(idSt == null ) return;

            Mascota mascota;
            try {
                mascota = servicio.getMascotaOrThrow(cliente, Integer.parseInt(idSt));
            } catch (NumberFormatException nfe) {
                mascota = servicio.getMascotaOrThrow(cliente, idSt);
            }
            String[] tipos = {"Consulta General", "Peluquería"};
            String tipo = (String) JOptionPane.showInputDialog(
                    null,"Tipo de servicio:","Agendar servicio",
                    JOptionPane.QUESTION_MESSAGE,null,tipos,tipos[0]
            );
            if(tipo == null) return;
            String fecha = pedirTextoObligatoria("Fecha (dd-mm-aaaa): ");
            if(fecha == null ) return;
            String profesional = pedirTextoObligatoria("Nombre del profesional: ");
            if(profesional == null) return;
            String observaciones = pedirTextoObligatoria("Observaciones: ");
            if(observaciones == null) return;
            //sobrecargas
            if("Consulta General".equals(tipo)){
                mascota.agregarServicio(new ConsultaGeneral(fecha,profesional,observaciones));
            } else if("Peluquería".equals(tipo)){
                mascota.agregarServicio(new Peluqueria(fecha,profesional,observaciones,mascota.getPeso()));
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de servicio desconocido.");
                return;
            }
            JOptionPane.showMessageDialog(null, "Servicio agendado.");
        }catch (ClienteNoEncontrado | MascotaNoEncontrada e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agendar servicio", "Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
    //permite mostrar historial de servicios 
    public static void mostrarHistorialServicios() {
        try {
            String[] opciones = {"Historial de una mascota", "Historial completo de todas las mascotas"};
            String opcion = (String) JOptionPane.showInputDialog(
                   null, "¿Qué historial desea ver?", "Historial",
                   JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
            if (opcion == null) return;

            if (opcion.equals(opciones[0])){   
                String rut = pedirTextoObligatoria("RUT del cliente: ");
                if(rut == null) return;
                Cliente cliente = servicio.getClienteOrThrow(rut);
                if(cliente.getMascotas().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Cliente no tiene mascotas.");
                    return;
                }
                String idSt = pedirTextoObligatoria("ID mascota: ");
                if(idSt == null ) return;
                Mascota mascota = servicio.getMascotaOrThrow(cliente, idSt);

                List<Servicio> historial = mascota.getHistorialServicios();
                if(historial.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No hay servicios registrados para esta mascota :(");
                    return;
                }
                String[] columnas = {"Tipo", "Fecha", "Profesional", "Observacion"};
                Object[][] datos = new Object[historial.size()][4];
                for(int i = 0; i < historial.size(); i++){
                    Servicio s = historial.get(i);
                    datos[i][0] = s.getTipoServicio();
                    datos[i][1] = s.getFecha();
                    datos[i][2] = s.getProfesional();
                    datos[i][3] = s.getObservaciones();
                }
                JTable table = new JTable(datos, columnas);
                JScrollPane scroll = new JScrollPane(table);
                JOptionPane.showMessageDialog(null, scroll, 
                    "Historial de " + mascota.getNombre(), JOptionPane.INFORMATION_MESSAGE);

            } else {
                List<Object[]> filas = new ArrayList<>();
                for(Cliente cliente : clientes.values()){
                    for(Mascota mascota : cliente.getMascotas()){
                        for(Servicio s : mascota.getHistorialServicios()){
                            filas.add(new Object[]{
                                cliente.getNombre(),mascota.getNombre(),s.getTipoServicio(),
                                s.getFecha(), s.getProfesional(), s.getObservaciones()
                            });
                        }
                    }
                }
                if (filas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay servicios registrados en el sistema.");
                    return;
                }
                 String[] columnas = {"Cliente", "Mascota", "Servicio", "Fecha", "Profesional", "Observaciones"};
                Object[][] datos = filas.toArray(new Object[filas.size()][]);

                JTable table = new JTable(datos, columnas);
                JScrollPane scroll = new JScrollPane(table);
                JOptionPane.showMessageDialog(null, scroll,
                    "Historial completo de servicios", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClienteNoEncontrado | MascotaNoEncontrada e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    //elimina la mascota de un cliente
    public static void eliminarMascota()  {
        try {
            String rut = pedirTextoObligatoria("Ingrese RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = servicio.getClienteOrThrow(rut);
            if(cliente.getMascotas().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tiene mascotas registradas :0");
                return;
            }
            String idST = pedirTextoObligatoria("Ingrese ID de la mascota a eliminar:");
            if(idST == null) return;
            Mascota mascota = servicio.getMascotaOrThrow(cliente, idST);
            int confirma = JOptionPane.showConfirmDialog(null,
                    "Seguro que de desea eliminar a " + mascota.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION ){
                cliente.eliminarMascotaCliente(mascota);
                JOptionPane.showMessageDialog(null,"Mascota eliminada");   
            }
        }catch (ClienteNoEncontrado | MascotaNoEncontrada e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al elimiar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Podemos editar datos de la mascota de un cliente
    public static void editarMascota()  {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = servicio.getClienteOrThrow(rut);
            List<Mascota> mascotas = cliente.getMascotas();

            if(mascotas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tiene mascotas registradas :0");
                return;
            }
            String id = pedirTextoObligatoria("Ingrese ID de la mascota a editar:");
            if(id == null) return;
            Mascota mascota = servicio.getMascotaOrThrow(cliente, id);
            String nombre = JOptionPane.showInputDialog("Nuevo nombre: ", mascota.getNombre());
            if(nombre == null )return;
            if(!nombre.isBlank()) mascota.setNombre(nombre.trim());
            String especie = JOptionPane.showInputDialog("Nueva especie:", mascota.getEspecie());
            if(especie == null )return;
            if(!especie.isBlank()) mascota.setEspecie(especie.trim());
            String raza = JOptionPane.showInputDialog("Nueva raza:", mascota.getRaza());
            if(raza == null )return;
            if(!raza.isBlank()) mascota.setRaza(raza.trim());
            String edad = JOptionPane.showInputDialog("Nueva edad:", mascota.getEdad());
            if (edad == null) return;
            if (!edad.isBlank()) {
                try {
                    mascota.setEdad(Integer.parseInt(edad.trim()));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Edad inválida.");
                }
            }
            String peso = JOptionPane.showInputDialog("Nuevo peso:", mascota.getPeso());
            if(peso == null) return;
            if (!peso.isBlank()) {
                try {
                    mascota.setPeso(Double.parseDouble(peso.trim()));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Peso inválido.");
                }
            }
            JOptionPane.showMessageDialog(null,"Mascota editada correctamente :)");
        }catch (ClienteNoEncontrado | MascotaNoEncontrada e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Filtramos mascotas segun especie y peso
    public static void filtrarMascotas()  {
        try {
            String especie = pedirTextoObligatoria("Ingrese especie a filtrar (eje: perro, gato): ");
            if(especie == null) return;
            
            Double pesoMin = null;
            while (pesoMin == null){
                String mi = JOptionPane.showInputDialog("Ingrese peso mínimo: ");
                if (mi == null) return;
                mi = mi.trim();
                if(mi.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No puede estar vacío.");
                    continue;
                }
                try {
                    pesoMin = Double.parseDouble(mi);
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
                    
            }
            Double pesoMax = null;
            while (pesoMax == null){
                String ma = JOptionPane.showInputDialog("Ingrese peso máximo: ");
                if (ma == null) return;
                ma = ma.trim();
                if(ma.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No puede estar vacío.");
                    continue;
                }
                try {
                    pesoMax = Double.parseDouble(ma);
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }       
            }
            if(pesoMin > pesoMax){
                double tmp = pesoMin;
                pesoMin = pesoMax;
                pesoMax = tmp;
            }
            List<Mascota> filtradas = new ArrayList<>();
            
            for(Cliente cliente : clientes.values()) {
                for(Mascota m : cliente.getMascotas()){
                    if(m.getEspecie() != null && m.getEspecie().equalsIgnoreCase(especie) 
                            && m.getPeso() >= pesoMin && m.getPeso()<= pesoMax){
                        filtradas.add(m);
                    }
                }
            }
            if(filtradas.isEmpty()){
                JOptionPane.showMessageDialog(null,"No se encontraron mascotas con ese criterio.");
                return;
            }
            String[] columnas = {"ID","Nombre","Especie","Raza","Edad","Peso"};
            Object[][] datos = new Object[filtradas.size()][6];
            
            for(int i = 0; i < filtradas.size(); i++){
                Mascota m = filtradas.get(i);
                datos[i][0] = m.getId();
                datos[i][1] = m.getNombre();
                datos[i][2] = m.getEspecie();
                datos[i][3] = m.getRaza();
                datos[i][4] = m.getEdad();
                datos[i][5] = m.getPeso();   
            }
            JTable table = new JTable(datos, columnas);
            JScrollPane scroll = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scroll,  "Mascotas filtradas", JOptionPane.INFORMATION_MESSAGE);
           } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al filtrar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    //Podemos editar los datos del cliente
    public static void editarCliente()  {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = servicio.getClienteOrThrow(rut);
            
            String nombre = JOptionPane.showInputDialog("Nuevo nombre: ", cliente.getNombre());
            if(nombre == null )return;
            
            String telefono = JOptionPane.showInputDialog("Nueva teléfono:", cliente.getTelefono());
            if(telefono == null )return;
            String correo = JOptionPane.showInputDialog("Nuevo correo:", cliente.getCorreo());
            if(correo == null )return;
            String direccion = JOptionPane.showInputDialog("Nueva direccion:", cliente.getDireccion());
            if(direccion == null )return;
            if (!nombre.isBlank())    cliente.setNombre(nombre.trim());
            if (!telefono.isBlank())  cliente.setTelefono(telefono.trim());
            if (!correo.isBlank())    cliente.setCorreo(correo.trim());
            if (!direccion.isBlank()) cliente.setDireccion(direccion.trim());

            JOptionPane.showMessageDialog(null,"Cliente editado correctamente :)");
        }catch (ClienteNoEncontrado e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar cliente","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Podemos eliminar un cliente y sus mascotas
    public static void eliminarCliente()  {
        try {
            String rut = pedirTextoObligatoria("Ingrese RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = servicio.getClienteOrThrow(rut);
            
            int confirma = JOptionPane.showConfirmDialog(null,
                    "Se eliminará el cliente \"" + cliente.getNombre() + "\" y TODAS sus mascotas.\n¿Confirmar?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            
            if(confirma != JOptionPane.YES_OPTION ) return; 
            clientes.remove(rut);
            JOptionPane.showMessageDialog(null, "Cliente eliminado.");   
        }catch (ClienteNoEncontrado e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al elimiar cliente","Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    //Permite buscar una mascota en todos los clientes (por nombre o por ID)
    public static void buscarMascotaGlobal() {
        try {
            String criterio = pedirTextoObligatoria("Ingrese nombre o ID de la mascota a buscar: ");
            if (criterio == null) return;

            List<Object[]> resultados = new ArrayList<>();
            for (Cliente cliente : clientes.values()) {
                for (Mascota mascota : cliente.getMascotas()) {
                    // Coincidencia por nombre (ignora mayúsculas/minúsculas)
                    if (mascota.getNombre().toLowerCase().contains(criterio.toLowerCase())) {
                        resultados.add(new Object[]{
                            cliente.getNombre(),
                            mascota.getId(),
                            mascota.getNombre(),
                            mascota.getEspecie(),
                            mascota.getRaza(),
                            mascota.getEdad(),
                            mascota.getPeso()
                        });
                    }
                    // Coincidencia por ID
                    else if (String.valueOf(mascota.getId()).equals(criterio)) {
                        resultados.add(new Object[]{
                            cliente.getNombre(),
                            mascota.getId(),
                            mascota.getNombre(),
                            mascota.getEspecie(),
                            mascota.getRaza(),
                            mascota.getEdad(),
                            mascota.getPeso()
                        });
                    }
                }
            }

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron mascotas con ese criterio.");
                return;
            }
            String[] columnas = {"Cliente", "ID", "Nombre", "Especie", "Raza", "Edad", "Peso"};
            Object[][] datos = resultados.toArray(new Object[0][]);

            JTable table = new JTable(datos, columnas);
            JScrollPane scroll = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scroll, "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mascota", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

