/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author usuario
 */
public class Peluqueria extends Servicio {
    private final double pesoMascota;
    
    public Peluqueria(String fecha, String profesional, String observacion, double pesoMascota){
        super(fecha, profesional, observacion);
        this.pesoMascota = pesoMascota;
    }
    @Override
    public String getTipoServicio() { 
        return "Peluquería";
    }
    @Override
    public double calcularCosto() {
        if (pesoMascota < 5) return 12000;
        if(pesoMascota <= 15) return 17000;
        return 22000;
    }
    @Override
    public String getResumen(){
        return "[Peluquería] " + getTipoServicio()  + getFecha() + " (" + getProfesional() + ")";
    }
}
