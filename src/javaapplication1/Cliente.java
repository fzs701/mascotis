package javaapplication1;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
*/
import java.util.ArrayList;

public class Cliente {
    private String rut;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private ArrayList<Mascota> mascotas;
    
    public Cliente(String rut, String nombre, String telefono, String correo, String direccion){
        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();  //comprobar si es corrercto
    }
    
    public void agregarMascota(Mascota m){ mascotas.add(m);}
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
    //get y set
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {this.telefono = telefono;}
    
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) {this.correo = correo;}
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {this.direccion = direccion;}

}
