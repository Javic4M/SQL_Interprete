
package com.mycompany.sql_interprete.main.archivos;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ArchivoCSV {
    
    public void escribirCSV(String path, String textoDelPanel) {
        String[] caracteres = textoDelPanel.split("");
        String reescritura = "";
        
        for (int i = 0; i < caracteres.length; i++) {
            if (!"\r".equals(caracteres[i])) {
                reescritura += caracteres[i];
            }
        }
        
        String[] filas = reescritura.split("\n");
        
        try {
            CsvWriter salida = new CsvWriter(new FileWriter(path, false), ',');
            
            for (String fila : filas) {
                salida.write(fila);
                salida.endRecord();
            }
            salida.close();
        } catch (IOException e) {
            System.out.println("Error en la Escritura del Archivo CSV");
        }
    }
    
    public void escribirCSVPorLista(String path, int contador, List<ContenidoCSV> contenido) {
        int indice = 0;
        
        try {
            CsvWriter salida = new CsvWriter(new FileWriter(path, false), ',');
            
            while (indice != contenido.size()) {             
                for (int i = 0; i < contador; i++) {
                    salida.write(contenido.get(indice).getContenido());
                    indice++;
                }
                salida.endRecord();
            }
            salida.close();
        } catch (IOException e) {
            System.out.println("Error en la Escritura del Archivo CSV");
        }
    }
    
    public List<ContenidoCSV> leerCSV(String path) {
        List<ContenidoCSV> contenido = new ArrayList<>();
        List<String> columnasDelArchivo = new ArrayList<>();
        boolean primerRecorrido = true;
        int contador = 0;
        BufferedReader reader;
        
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = null;
            
            while ((line = reader.readLine()) != null) {
                String[] filas = line.split(",");
                
                for (String fila : filas) {
                    if (primerRecorrido) {
                        columnasDelArchivo.add(fila);
                        contenido.add(new ContenidoCSV(fila, fila, contador+1));
                    } else {
                        contenido.add(new ContenidoCSV(fila, columnasDelArchivo.get(contador), contador+1));
                    }
                    contador++;
                }
                primerRecorrido = false;
                contador = 0;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"La Ruta del Archivo no Exite","Error",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error al Leer el Archivo");
        }
        return contenido;
    }

}
