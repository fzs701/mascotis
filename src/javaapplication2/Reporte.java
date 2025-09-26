/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.swing.*;

public class Reporte {

    public static void generar(Map<String, Cliente> clientes) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("===== REPORTE DE CLIENTES, MASCOTAS Y SERVICIOS =====\n\n");

        for (Cliente c : clientes.values()) {
            sb.append("Cliente:   ").append(c.getNombre())
              .append(" (").append(c.getRut()).append(")\n");
            sb.append("Teléfono:  ").append(c.getTelefono()).append("\n");
            sb.append("Correo:    ").append(c.getCorreo()).append("\n");
            sb.append("Dirección: ").append(c.getDireccion()).append("\n");

            if (c.getMascotas().isEmpty()) {
                sb.append("  >> No tiene mascotas registradas\n\n");
            } else {
                for (Mascota m : c.getMascotas()) {
                    sb.append("  Mascota: ").append(m.getNombre())
                      .append(" [ID: ").append(m.getId()).append("]\n");
                    sb.append("    Especie: ").append(m.getEspecie()).append("\n");
                    sb.append("    Raza:    ").append(m.getRaza()).append("\n");
                    sb.append("    Edad:    ").append(m.getEdad()).append(" años\n");
                    sb.append("    Peso:    ").append(m.getPeso()).append(" kg\n");

                    if (m.getHistorialServicios().isEmpty()) {
                        sb.append("    >> No tiene servicios registrados.\n");
                    } else {
                        sb.append("    Servicios:\n");
                        for (Servicio s : m.getHistorialServicios()) {
                            sb.append("      - ").append(s.getResumen()).append("\n");
                        }
                    }
                    sb.append("\n");
                }
            }
            sb.append("----------------------------------------------------\n\n");
        }

        
        File out = new File("reporte.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
            pw.print(sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al escribir reporte: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        JTextArea textArea = new JTextArea(sb.toString(), 22, 70);
        textArea.setEditable(false);
        textArea.setCaretPosition(0); 
        JScrollPane scroll = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(
                null,
                scroll,
                "Reporte guardado ",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
