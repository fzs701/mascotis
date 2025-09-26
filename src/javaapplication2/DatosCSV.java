/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package javaapplication2;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class DatosCSV {
    static final String ARCHIVO = "clientes.csv";

    
    public static Map<String, Cliente> cargar() {
        Map<String, Cliente> clientes = new HashMap<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            JOptionPane.showMessageDialog(null, "No se encontró " + f.getAbsolutePath() + ". Se inicia vacío.");
            return clientes;
        }

        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea; int n = 0;
            while ((linea = br.readLine()) != null) {
                n++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                if (linea.toLowerCase().startsWith("rut;")) continue; 

                String[] p = linea.split(";", -1);
                if (p.length != 11) {
                    System.err.println("Línea " + n + " inválida (se esperaban 11 columnas): " + linea);
                    continue;
                }

                
                String rut       = p[0].trim();
                String nombre    = p[1].trim();
                String telefono  = p[2].trim();
                String correo    = p[3].trim();
                String direccion = p[4].trim();
                if (rut.isEmpty()) continue;

                Cliente c = clientes.get(rut);
                if (c == null) {
                    c = new Cliente(rut, nombre, telefono, correo, direccion);
                    clientes.put(rut, c);
                }

                
                int    id      = Integer.parseInt(p[5].trim());
                String nomM    = p[6].trim();
                String especie = p[7].trim();
                String raza    = p[8].trim();
                int    edad    = Integer.parseInt(p[9].trim());
                double peso    = Double.parseDouble(p[10].trim().replace(",", "."));

                
                if (nomM.isEmpty() && especie.isEmpty() && raza.isEmpty()
                    && id == 0 && edad == 0 && Math.abs(peso) < 1e-9) {
                    continue;
                }

                c.agregarMascota(new Mascota(id, nomM, especie, raza, edad, peso));
                if (id > maxId) maxId = id;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar CSV: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        
        JavaApplication2.mascotaId = Math.max(JavaApplication2.mascotaId, maxId + 1);
        JOptionPane.showMessageDialog(null, "CSV cargado correctamente.");

        return clientes;
    }

    
    public static void guardar(Map<String, Cliente> clientes) {
        File f = new File(ARCHIVO);
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {

            for (Cliente c : clientes.values()) {
                if (c.getMascotas().isEmpty()) {
                    pw.println(c.getRut() + ";" + c.getNombre() + ";" + c.getTelefono() + ";" +
                               c.getCorreo() + ";" + c.getDireccion() + ";" +
                               "0;;;;0;0.0"); 
                } else {
                    for (Mascota m : c.getMascotas()) {
                        pw.println(
                            c.getRut() + ";" +
                            c.getNombre() + ";" +
                            c.getTelefono() + ";" +
                            c.getCorreo() + ";" +
                            c.getDireccion() + ";" +
                            m.getId() + ";" +
                            m.getNombre() + ";" +
                            m.getEspecie() + ";" +
                            m.getRaza() + ";" +
                            m.getEdad() + ";" +
                            m.getPeso()
                        );
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Datos guardados");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar CSV: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }  

}
