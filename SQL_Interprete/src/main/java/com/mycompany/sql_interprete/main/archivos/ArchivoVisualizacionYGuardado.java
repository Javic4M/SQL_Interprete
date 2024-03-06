
package com.mycompany.sql_interprete.main.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoVisualizacionYGuardado {
    
    public List<String> leerArchivo(String path) {
        File file = new File(path);
        List<String> lista = new ArrayList<String>();
        BufferedReader entrada = null;

        try { 
            entrada = new BufferedReader(new FileReader(file));
            String cadena;

            while ((cadena = entrada.readLine()) != null) {
                lista.add(cadena);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public void guardarArchivo(String path, String cadena){
        File file = new File(path);
        List<String> lista = obtenerLista(cadena);
        
        try {
            FileWriter writer = new FileWriter(file, false);

            try (PrintWriter printer = new PrintWriter(writer)) {
                
                for (String fila : lista) {
                    System.out.println("Fila: " + fila);
                    printer.println(fila);
                }
                printer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private List<String> obtenerLista(String cadena) {
        List<String> lista = new ArrayList<String>();
        String[] caracteres = cadena.split("");
        String cadenaRescrita = "";
        
        for (int i = 0; i < caracteres.length; i++) {
            if (!"\t".equals(caracteres[i])) {
                cadenaRescrita += caracteres[i];
            }
        }
        
        caracteres = cadenaRescrita.split("\r");
        for (int i = 0; i < caracteres.length; i++) {
            lista.add(caracteres[i]);
        }
        return lista;
    }
}
