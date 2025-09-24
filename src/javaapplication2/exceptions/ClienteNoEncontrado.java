/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2.exceptions;

/**
 *
 * @author usuario
 */
public class ClienteNoEncontrado extends Exception {
    public ClienteNoEncontrado(String rut) {
        super("Cliente no encontrado (RUT: " + rut + ")");
    }
    
}
