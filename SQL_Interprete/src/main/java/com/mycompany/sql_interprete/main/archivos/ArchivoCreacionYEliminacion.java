
package com.mycompany.sql_interprete.main.archivos;

import java.io.File;
import java.io.IOException;

public class ArchivoCreacionYEliminacion {
    
    public String crearFolder(String pathDelFolder) {
        File file = new File(pathDelFolder);
        
        if (!file.exists()) {
            file.mkdirs();
        }  
        return file.getAbsolutePath();
    }
    
    public String crearArchivo(String pathDelArchivo) {
        File file = new File(pathDelArchivo);
        
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            return file.getAbsolutePath();
        } catch (IOException ex) {
            System.out.println("Error al Crear Archivos");
        }
        return "Error";
    }
}
