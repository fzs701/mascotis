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
    
    static HashMap<Integer, Cliente> clientes = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int opcion;
        do {
           System.out.println("\n--- MENÚ ---");
           System.out.println("1. Agregar cliente y mascota/s");
           System.out.println("2. Agendar servicio");
           System.out.println("3. Mostrar historial de servicios");
           System.out.println("4. Salir");
           System.out.println("Elige una opción: ");
           opcion = Integer.parseInt(br.readLine());
        
                
        }while (opcion != 4);
    
    public static void agregarCliente(){
        System.out.println("RUT cliente: ");
        String rut = br.readLine();
        System.out.println("Nombre cliente: ");
        String nombre = br.readLine();
        System.out.println("Teléfono: ");
        String telefono = br.readLine();
        System.out.println("Correo: ");
        String correo = br.readLine();
        System.out.println("Direccion: ");
        String direccion = br.readLine();
        
        Cliente cliente = new Cliente(rut, nombre, telefono, correo, direccion);
        clientes.put(rut,cliente);
        System.out.println("");
        
    }
    
    public static void agregarMascota() {
        System.out.println("Ingrese RUT del dueño de la mascota");
        String rut = br.readLine();
        
        Cliente cliente = clientes.get(id);
        if (cliente == null) {
            System.out.println("Dueño no encontrado :(");
            return;
        }
        System.out.println("Nombre de la mascota: ");
        String nombreM = br.readLine();
        System.out.println("Especie: ");
        String especie = br.readLine();
        System.out.println("Raza: ");
        String raza = br.readLine();
        System.out.println("Edad: ");
        int edad = Integer.parseInt(br.readLine());
        System.out.print("Peso: ");
        double peso = Double.parseDouble(br.readLine());
        
        Mascota mascota = new Mascota(mascotaId++, nombreM, especie, raza, edad, peso);
        cliente.agregarMascota(mascota);
        
        
    }
    
}
