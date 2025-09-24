/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author usuario
 */
public class JavaApplication2 {
    //mapa para almcenar clientes usando rut como clave
    static HashMap<String, Cliente> clientes = new HashMap<>();
    
    //contador para asignar ID unico a cada mascota
    static int mascotaId = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        agregarDatosIniciales(); //carga datos inciales para comprobar
        //menu principal del sistema
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
                default: JOptionPane.showMessageDialog(null, "Opción inválida"); break;
                    
                
            }
        
                
        }
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
            String rut = JOptionPane.showInputDialog("RUT cliente: ");
            String nombre = JOptionPane.showInputDialog("Nombre cliente: ");
            String telefono = JOptionPane.showInputDialog("Teléfono: ");
            String correo = JOptionPane.showInputDialog("Correo: ");
            String direccion = JOptionPane.showInputDialog("Direccion: ");

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
                
            //datos de la mascota
            
            
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
            System.out.println("Mascota agregada al cliente " + cliente.getNombre());
        }catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Edad/Peso inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar mascota", "Error", JOptionPane.ERROR_MESSAGE);
    }
        
        
    }
    
    
    
    
    //muestra todas las mascotas de un cliente ingresando rut
    public static void mostrarMascotaPorCliente()  {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del cliente: ");

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
    public static void agregarDatosIniciales(){
        Cliente c1 = new Cliente("22222222-2", "Emilia Perez", "900000000", "emi@perez", "av. argentina");
        c1.agregarMascota(new Mascota(mascotaId++, "Puppy"));
        c1.agregarMascota(new Mascota(mascotaId++, "Luna", "Perro" , "Pug" , 4, 5.5));
        
        clientes.put(c1.getRut(), c1);
    }
    
    
    
    
    
    //permite agendar un servicio para una mascota de un cliente
    public static void agendarServicio() {
        String rut = pedirTextoObligatoria("RUT del cliente: ");
        if(rut == null) return;
        Cliente cliente = clientes.get(rut);
        if (cliente == null){
            JOptionPane.showMessageDialog(null,"Cliente no encontrado.");
            return;
        }
        if(cliente.getMascotas().isEmpty()){
            JOptionPane.showMessageDialog(null,"Cliente no tiene mascotas.");
            return;
        }
        String idSt = pedirTextoObligatoria("ID mascota: ");
        if(idSt == null ) return;
        
        Mascota mascota = null;
        for(Mascota m : cliente.getMascotas()) {
            if (String.valueOf(m.getId()).equals(idSt)){
                mascota = m;
                break;
            }
        }
        if (mascota == null){
            JOptionPane.showMessageDialog(null,"Mascota no encontrada.");
            return;
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
        if("Consulta General".equals(tipo)){
            mascota.agregarServicio(new ConsultaGeneral(fecha,profesional,observaciones)); //sobrecarga metodo
        
        } else {
            mascota.agregarServicio(new ConsultaGeneral(fecha,profesional,observaciones)); //sobrecarga metodo
        
        }
        JOptionPane.showMessageDialog(null, "Servicio agendado.");
    }
    //permite mostrar historial de servicios por mascota o por 
    //toda la veterinaria
    
    
    
    
    public static void mostrarHistorialServicios() {
        String[] opciones = {"Historial de una mascota", "Historial completo de todas las mascotas"};
        String opcion = (String) JOptionPane.showInputDialog(
               null, "¿Qué historial desea ver?", "Historial",
               JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if (opcion == null) return;

        if (opcion.equals(opciones[0])){   
            String rut = pedirTextoObligatoria("RUT del cliente: ");
            if(rut == null) return;
            Cliente cliente = clientes.get(rut);
            if(cliente == null ){
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                return;
            }
            if(cliente.getMascotas().isEmpty()){
                JOptionPane.showMessageDialog(null,"Cliente no tiene mascotas.");
                return;
            }
            String idSt = pedirTextoObligatoria("ID mascota: ");
            if(idSt == null ) return;
            Mascota mascota = null;
            for( Mascota m : cliente.getMascotas()) {
                if (String.valueOf(m.getId()).equals(idSt)){
                    mascota = m;
                    break;
                }
            }
            if (mascota == null) {
                JOptionPane.showMessageDialog(null,"Mascota no encontrada.");
                return;
            }


            List<Servicio> historial = mascota.getHistorialServicios();
            if(historial.isEmpty()){
                JOptionPane.showMessageDialog(null,"No hay servicios registrados para esta mascota :(");
                return;
            }
            String[] columnas = {"Tipo", "Fecha", "Profesional", "Observaciones"};
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
            Object[][] datos = filas.toArray(new Object[0][]);

            JTable table = new JTable(datos, columnas);
            JScrollPane scroll = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scroll,
                "Historial completo de servicios", JOptionPane.INFORMATION_MESSAGE);

            
        }
    }
    
    
    
    
    
    
    public static void eliminarMascota()  {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del cliente: ");

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
            
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la mascota a eliminar:"));
            Mascota mascota = null;
            for (Mascota m : mascotas) {
                if(m.getId() == id){
                    mascota = m;
                    break;
                }

            }
            
            if (mascota == null){
                JOptionPane.showMessageDialog(null,"Mascota no encontrada");
                return;
            }
            
            int confirma = JOptionPane.showConfirmDialog(null,
                    "Seguro que de desea eliminar a " + mascota.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION ){
                cliente.eliminarMascotaCliente(mascota);
                JOptionPane.showMessageDialog(null,"Mascota eliminada");
                
                
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al elimiar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    
    
    
    
    
    public static void editarMascota()  {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del cliente: ");

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
            
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la mascota a eliminar:"));
            Mascota mascota = null;
            for (Mascota m : mascotas) {
                if(m.getId() == id){
                    mascota = m;
                    break;
                }

            }
            
            if (mascota == null){
                JOptionPane.showMessageDialog(null,"Mascota no encontrada");
                return;
            }
            String nombre = JOptionPane.showInputDialog("Nuevo nombre: ", mascota.getNombre());
            String especie = JOptionPane.showInputDialog("Nueva especie:", mascota.getEspecie());
            String raza = (JOptionPane.showInputDialog("Nueva raza:", mascota.getRaza()));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Nueva edad:", mascota.getEdad()));
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Nuevo peso:", mascota.getPeso()));

            mascota.setNombre(nombre);
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
            mascota.setEdad(edad);
            mascota.setPeso(peso);
            
       
            JOptionPane.showMessageDialog(null,"Mascota editada correctamente :)");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void filtrarMascotas()  {
        try {
            String especie = JOptionPane.showInputDialog("Ingrese especie a filtrar (eje: perro, gato): ");
            double pesoMin = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso mínimo: "));
            double pesoMax = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso máximo: "));
            
            List<Mascota> filtradas = new ArrayList<>();
            
            for(Cliente cliente : clientes.values()) {
                for(Mascota m : cliente.getMascotas()){
                    if(m.getEspecie().equalsIgnoreCase(especie) && m.getPeso() >= pesoMin && m.getPeso()<= pesoMax){
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
    
   
    
    
    
}
