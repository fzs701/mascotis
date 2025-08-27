/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
import java.io.*;
import java.util.*;

/**
 *
 * @author usuario
 */
public class JavaApplication1 {
    
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int mascotaId = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        agregarDatosIniciales();
        int opcion;
        do {
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
                    break;
                case 5:
                    break;
                    
                
            }
        
                
        }while (opcion != 6);
    }
    
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
    
    public static void agregarMascota() throws IOException{
        System.out.print("Ingrese RUT del dueño de la mascota: ");
        String rut = br.readLine();
        
        Cliente cliente = clientes.get(rut);
        if (cliente == null) {
            System.out.print("Dueño no encontrado :(");
            return;
        }
        
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
    public static void agregarDatosIniciales(){
        Cliente c1 = new Cliente("22222222-2", "Emilia Perez", "900000000", "emi@perez", "av. argentina");
        c1.agregarMascota(new Mascota(mascotaId++, "Puppy"));
        c1.agregarMascota(new Mascota(mascotaId++, "Luna", "Perro" , "Pug" , 4, 5.5));
        clientes.put(c1.getRut(), c1);
    }
    
}
