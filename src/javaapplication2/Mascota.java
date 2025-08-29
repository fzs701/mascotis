package javaapplication2;
import java.util.ArrayList;
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
    private ArrayList<Servicio> historialServicios = new ArrayList<>();
    
    //sobrecarga constructores para crear mascotas con distintos detalles
    public Mascota(int id, String nombre, String especie, String raza, int edad, double peso){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
    }
    public Mascota(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.especie = "desconocido";
        this.raza = "desconocido";
        this.edad = 0;
        this.peso = 0.0;
    }
    public Mascota(int id, String nombre, String especie, String raza){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = 0;
        this.peso = 0.0;
    }
    
    //get y set.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie;}
    
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza;}
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad;}
    
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso;}
    
    //sobrecarga de metodos para agregar servicio con distintos parametros
    
    public void agregarServicio(Servicio servicio){
        historialServicios.add(servicio);
    }
    public void agregarServicio(String tipo, String fecha){
        Servicio s = new Servicio(tipo, fecha);
        historialServicios.add(s);
    }
    public void agregarServicio(String tipo, String fecha, String profesional, String observaciones){
        Servicio s = new Servicio(tipo, fecha, profesional, observaciones);
        historialServicios.add(s);
    }
    
    
    public ArrayList<Servicio> getHistorialServicios() {
        return historialServicios;
    }
    
   
}

