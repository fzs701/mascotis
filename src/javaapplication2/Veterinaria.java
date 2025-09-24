/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import java.util.HashMap;
import javaapplication2.exceptions.ClienteNoEncontrado;
import javaapplication2.exceptions.MascotaNoEncontrada;

/**
 *
 * @author usuario
 */
public class Veterinaria {
    
    private final HashMap<String, Cliente> clientes;

    public Veterinaria(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteOrThrow(String rut) throws ClienteNoEncontrado {
        Cliente c = clientes.get(rut);
        if (c == null) throw new ClienteNoEncontrado(rut);
        return c;
    }

    public Mascota getMascotaOrThrow(Cliente cliente, String idStr) throws MascotaNoEncontrada {
        for (Mascota m : cliente.getMascotas()) {
            if (String.valueOf(m.getId()).equals(idStr)) return m;
        }
        int id;
        try { id = Integer.parseInt(idStr); } catch (NumberFormatException e) { id = -1; }
        throw new MascotaNoEncontrada(id);
    }
}
    
