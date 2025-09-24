/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class JavaApplication2 {
    //mapa para almcenar clientes usando rut como clave
    static HashMap<String, Cliente> clientes = new HashMap<>();
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //contador para asignar ID unico a cada mascota
    static int mascotaId = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
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
                case "Mostrar mascotas del cliente": mostrarMascotaPorCliente();break;
                case "Agendar servicio": agendarServicio(); break;
                case "Mostrar historial de servicios": mostrarHistorialServicios();break;
                case "Editar Mascota": editarMascota(); break;
                case "Eliminar Mascota": eliminarMascota(); break;
                case "Filtrar Mascotas": filtrarMascotas(); break;
                default: JOptionPane.showMessageDialog(null, "Opción inválida"); break;
                    
                
            }
        
                
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
    public static void agregarMascota() throws IOException{
        try {
            String rut = JOptionPane.showInputDialog("Ingrese RUT del dueño de la mascota: ");
            Cliente cliente = clientes.get(rut);
            if (cliente == null) {
                System.out.print("Dueño no encontrado :(");
                return;
            }
            //datos de la mascota
            System.out.print("Nombre de la mascota: ");
            String nombreM = br.readLine();
            System.out.print("Especie: ");
            String especie = br.readLine();
            System.out.print("Raza: ");
            String raza = br.readLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(br.readLine());
            System.out.print("Peso: ");
            double peso = Double.parseDouble(br.readLine());

            Mascota mascota = new Mascota(mascotaId++, nombreM, especie, raza, edad, peso);
            cliente.agregarMascota(mascota);
            System.out.println("Mascota agregada al cliente " + cliente.getNombre());
        }
        
    }
    
    
    
    
    //muestra todas las mascotas de un cliente ingresando rut
    public static void mostrarMascotaPorCliente() throws IOException {
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
    public static void agendarServicio() throws IOException {
        System.out.print("RUT del cliente: ");
        String rut = br.readLine();
        Cliente cliente = clientes.get(rut);
        if (cliente == null){
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.print("ID mascota: ");
        int idMascota = Integer.parseInt(br.readLine());
        Mascota mascota = null;
        for(Mascota m : cliente.getMascotas()) {
            if (m.getId() == idMascota ){
                mascota = m;
                break;
            }
        }
        if (mascota == null){
            System.out.println("Mascota no encontrada.");
            return;
        }
        System.out.println("Seleccione tipo de servicio:");
        System.out.println("1. Consulta General");
        System.out.println("2. Peluquería");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(br.readLine());
        
        String tipoServicio = "";
        if(tipo == 1){
            tipoServicio = "Consulta General";
        } else if (tipo == 2){
            tipoServicio = "Peluquería";
        } else {
            System.out.println("Opción inválida");
            return;
        }
        
        System.out.print("Fecha (dd-mm-aaaa): ");
        String fecha = br.readLine();
        System.out.print("Nombre del profesional: ");
        String profesional = br.readLine();
        System.out.print("Observaciones: ");
        String observaciones = br.readLine();
        
        
        mascota.agregarServicio(tipoServicio,fecha,profesional,observaciones); //sobrecarga metodo
        System.out.println("Servicio agendado.");
    }
    //permite mostrar historial de servicios por mascota o por 
    //toda la veterinaria
    public static void mostrarHistorialServicios() throws IOException {
        System.out.println("¿Qué historial desa ver?");
        System.out.println("1. Historial de una mascota");
        System.out.println("2. Historial completo de todas las mascotas");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(br.readLine());
        
        if(opcion == 1) {   
            System.out.print("RUT del cliente: ");
            String rut = br.readLine();
            Cliente cliente = clientes.get(rut);
            if(cliente == null ){
                System.out.println("Cliente no encontrado.");
                return;
            }
            System.out.print("ID de la mascota: ");
            int id = Integer.parseInt(br.readLine());
            Mascota mascota = null;
            for( Mascota m : cliente.getMascotas()) {
                if(m.getId() == id) {
                    mascota = m;
                    break;
                }
            }
            if (mascota == null) {
                System.out.println("Mascota no encontrada.");
                return;
            }


            List<Servicio> historial = mascota.getHistorialServicios();
            if(historial.isEmpty()){
                System.out.println("No hay servicios registrados para esta mascota :(");
                return;
            }
            System.out.println("Historial de servicios para " + mascota.getNombre() + ":");
            for(Servicio s : historial) {
                System.out.println("---------");
                System.out.println("Tipo: " + s.getTipoServicio());
                System.out.println("Fecha: " + s.getFecha());
                System.out.println("Profesional: " + s.getProfesional());
                System.out.println("Observaciones: " + s.getObservaciones());
            }
            
        } else if (opcion == 2) {
            boolean hayServicios = false;
            for(Cliente cliente : clientes.values()){
                for(Mascota mascota : cliente.getMascotas()){
                    List<Servicio> historial = mascota.getHistorialServicios();
                    if(!historial.isEmpty()){
                        hayServicios = true;
                        System.out.println("Cliente: " + cliente.getNombre() + " (RUT: " + cliente.getRut() + ")");
                        System.out.println("Mascota: " + mascota.getNombre() + " (ID: " + mascota.getId() + ")");
                        for(Servicio s : historial) {
                            System.out.println("---------");
                            System.out.println("Tipo: " + s.getTipoServicio());
                            System.out.println("Fecha: " + s.getFecha());
                            System.out.println("Profesional: " + s.getProfesional());
                            System.out.println("Observaciones: " + s.getObservaciones());
                            
                        }
                        System.out.println();
                    }
                }
            }
            if(!hayServicios){
                System.out.println("No hay servicios registrados :(");
            }
            
            
            
        }else {
            System.out.println("Opción inválida");
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
            if(confirma == JOptionPane.YES_NO_OPTION ){
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
            
            
       
            JOptionPane.showMessageDialog(null,"Mascota editada correctamente :)");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al filtrar mascotas","Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    public class 
    
    
    
    
}
