/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private double peso;
    
    public Mascota(int id, String nombre, String especie, String raza, int edad, double peso){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
    }
    //get y set.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String geNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie;}
    
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza;}
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad;}
    
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso;}
    
   
}
