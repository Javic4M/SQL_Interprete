
package com.mycompany.sql_interprete.main.nodoArbol;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodoArbol extends DefaultMutableTreeNode {
    
    private String nombreDelNode;
    private String path;
    private boolean abierto = false, esUnArchivo = true;
    
    public NodoArbol() {}
    
    public NodoArbol(String nombreDelNode, String path) {
        this.nombreDelNode = nombreDelNode;
        this.path = path;
        this.setUserObject(nombreDelNode);
    }
    
    public String obtenerNombreDelNode() {
        return nombreDelNode;
    }
    
    public String obtenerPath() {
        return path;
    }
    
    public void cambiarDeEstado() {
        if (abierto) {
            abierto = false;
        } else {
            abierto = true;
        }
    }
    
    public boolean estaAbierto() {
        return abierto;
    }
    
    public void noEsUnArchivo() {
        esUnArchivo = false;
    }
    
    public boolean esUnArchivo() {
        return esUnArchivo;
    }
    
}
