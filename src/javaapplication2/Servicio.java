/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package javaapplication2;

/**
 *
 * @author usuario
 */
public abstract class Servicio {
    private String fecha;
    private String profesional;
    private String observaciones;
    
    public Servicio(String fecha, String profesional, String observaciones){
        this.fecha = fecha;
        if(profesional == null || profesional.isBlank()){
            this.profesional = "Sin información.";
        }else {
            this.profesional = profesional;
        }
        
        if(observaciones == null || observaciones.isBlank()){
            this.profesional = "No hay observaciones.";
        } else {
            this.observaciones = observaciones;
        }
    }
   
    
    
    public abstract String getTipoServicio();
    public abstract double calcularCosto();
    
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getFecha() { return fecha; }
    
    public void setProfesional(String profesional) { this.profesional = profesional; }
    public String getProfesional() { return profesional; }
    
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getObservaciones() { return observaciones; }
    
    
    //sobreescritura de metodos
    
    public String getResumen() {
        return getTipoServicio() + "Fecha " + fecha + " (" + profesional + ")";
    }
    
    
    
    
    
    
    
}
