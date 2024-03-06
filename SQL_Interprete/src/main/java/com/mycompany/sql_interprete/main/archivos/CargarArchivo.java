
package com.mycompany.sql_interprete.main.archivos;

import com.mycompany.sql_interprete.main.nodoArbol.NodoArbol;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;

public class CargarArchivo {
    
    public void crearArbol(DefaultTreeModel modelo, NodoArbol nodo, File file) {
        File[] archivos = file.listFiles();
        
        if (archivos != null) {
            int contador = 0;
            
            for (File archivo : archivos) {
                NodoArbol hijo = new NodoArbol(archivo.getName(), archivo.getAbsolutePath());
                modelo.insertNodeInto(hijo, nodo, contador);
                contador++;
                
                if (archivo.isDirectory()) {
                    hijo.noEsUnArchivo();
                    crearArbol(modelo, hijo, archivo);
                }
            }
        } else {
            System.out.println("No tiene Hijos");
        }
    }
}
