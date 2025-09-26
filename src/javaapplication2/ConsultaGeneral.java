/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author usuario
 */
public class ConsultaGeneral extends Servicio {
    public ConsultaGeneral(String fecha, String profesional, String observacion){
        super(fecha, profesional, observacion);
    }
    
    //sobreescritura de metodos
    @Override
    public String getTipoServicio() { 
        return "Consulta General";
    }
    @Override
    public double calcularCosto() {
        return 15000;
    }
    @Override
    public String getResumen(){
        return "[Consulta] " + getTipoServicio() + " @ " + getFecha() + " (" + getProfesional() + ")";
        }
    
}
