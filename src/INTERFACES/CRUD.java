/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INTERFACES;

import java.util.List;

public interface CRUD<T> {
    
    //OBTENER ID
    int OBTENER_ID();
    
    // Método para AGREGAR un objeto
    Boolean AGREGAR(T objeto);

    // Método para BUSCAR_POR_ID un objeto por su identificador
    T BUSCAR_POR_ID(int id);

    // Método para EDITAR un objeto
    Boolean EDITAR(T objeto, int ID);

    // Método para DESACTIVAR un objeto por su identificador
    Boolean DESACTIVAR(int id);

    // Método para LISTAR todos los objetos
    List<T> LISTAR();
}

