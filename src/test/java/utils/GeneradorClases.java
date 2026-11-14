/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import org.junit.jupiter.api.Test;

/**
 *
 * @author juanp
 */
public class GeneradorClases {

    @Test
    public void makeNewInstance() {
        try {
            // 1. Nombre completo incluyendo el paquete (sin .java)
            String className = "jsoftware.com.jblue.views.mod.pro.OwnerRegisterProcess.java";

            // 2. Cargar la clase dinámicamente
            Class<?> cls = Class.forName(className);
            System.out.println("Clase encontrada: " + cls.toGenericString());

            // 3. Crear una instancia de la clase (equivale a hacer un 'new AdministracionHistoryService()')
            Object instance = cls.getDeclaredConstructor().newInstance();

            System.out.println("Instancia creada exitosamente: " + instance);

        } catch (ClassNotFoundException ex) {
            System.err.println("No se encontró la clase especificada: " + ex.getMessage());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException ex) {
            System.err.println("Error al intentar instanciar la clase: " + ex.getMessage());
        }

    }

}
