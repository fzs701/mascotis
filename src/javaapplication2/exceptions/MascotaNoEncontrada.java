/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2.exceptions;

/**
 *
 * @author usuario
 */
public class MascotaNoEncontrada extends Exception {
    public MascotaNoEncontrada(int id) {
        super("Mascota no encontrada (ID: " + id + ")");
    }
    
    
}
