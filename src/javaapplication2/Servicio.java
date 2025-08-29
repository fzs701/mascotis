/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package javaapplication2;
/**
 *
 * @author usuario
 */
public class Servicio {
    private String tipoServicio;
    private String fecha;
    private String profesional;
    private String observaciones;
    
    public Servicio(String tipoServicio, String fecha, String profesional, String observaciones){
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.profesional = profesional;
        this.observaciones = observaciones;
    }
    public Servicio(String tipoServicio, String fecha){
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.profesional = "Sin información.";
        this.observaciones = "No hay observaciones.";
    }
   
   
    
    
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public String getTipoServicio() { return tipoServicio; }
    
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getFecha() { return fecha; }
    
    public void setProfesional(String profesional) { this.profesional = profesional; }
    public String getProfesional() { return profesional; }
    
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getObservaciones() { return observaciones; }
    
    
    
    
    
    
    
}
