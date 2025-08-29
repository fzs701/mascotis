/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.io.*;
import java.util.*;

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
        int opcion;
        do { 
            //menu principal del sistema
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar mascota");
            System.out.println("3. Mostrar mascotas del cliente");
            System.out.println("4. Agendar servicio");
            System.out.println("5. Mostrar historial de servicios");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(br.readLine());
           
            switch (opcion){
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    agregarMascota();
                    break;
                case 3:
                    mostrarMascotaPorCliente();
                    break;
                case 4:
                    agendarServicio();
                    break;
                case 5:
                    mostrarHistorialServicios();
                    break;
                    
                
            }
        
                
        }while (opcion != 6);
    }
    //permite registrar un nuevo cliente ingresando sus datos
    public static void agregarCliente() throws IOException {
        System.out.print("RUT cliente: ");
        String rut = br.readLine();
        System.out.print("Nombre cliente: ");
        String nombre = br.readLine();
        System.out.print("Teléfono: ");
        String telefono = br.readLine();
        System.out.print("Correo: ");
        String correo = br.readLine();
        System.out.print("Direccion: ");
        String direccion = br.readLine();
        
        Cliente cliente = new Cliente(rut, nombre, telefono, correo, direccion);
        clientes.put(rut,cliente);
        System.out.println("Cliente agregado exitosamente");
        
    }
    //permite registrar una mascota a un cliente existente :0
    public static void agregarMascota() throws IOException{
        System.out.print("Ingrese RUT del dueño de la mascota: ");
        String rut = br.readLine();
        
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
    //muestra todas las mascotas de un cliente ingresando rut
    public static void mostrarMascotaPorCliente() throws IOException {
        System.out.print("Ingrese RUT del cliente: ");
        String rut = br.readLine();
        
        Cliente cliente = clientes.get(rut);
        if(cliente == null){
            System.out.println("Cliente no encontrado :(");
            return;
        
        }
        ArrayList<Mascota> mascotas = cliente.getMascotas();
        if(mascotas.isEmpty()) {
            System.out.println("No tiene mascotas registradas :0");
            return;
        }
        System.out.println("\nMascotas de " + cliente.getNombre() + ":");
        for (Mascota m : mascotas) {
            System.out.println("-------------");
            System.out.printf("ID: %d\nNombre: %s\nEspecie: %s\nRaza: %s\nEdad %d\nPeso: %.2f kg\n",m.getId(),
                    m.getNombre() , m.getEspecie() , m.getRaza() , m.getEdad() , m.getPeso());
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


            ArrayList<Servicio> historial = mascota.getHistorialServicios();
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
                    ArrayList<Servicio> historial = mascota.getHistorialServicios();
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
}
