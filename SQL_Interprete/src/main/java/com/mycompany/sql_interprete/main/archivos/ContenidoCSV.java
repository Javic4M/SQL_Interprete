
package com.mycompany.sql_interprete.main.archivos;

public class ContenidoCSV {
    
    private String contenido;
    private String columna;
    private int numero;
    private boolean mostrar = false;

    public ContenidoCSV(String contenido, String columna, int numero) {
        this.contenido = contenido;
        this.columna = columna;
        this.numero = numero;
    }

    public void siMostrar() {
        this.mostrar = true;
    }
    
    public boolean isMostrar() {
        return mostrar;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public String getContenido() {
        return contenido;
    }

    public String getColumna() {
        return columna;
    }
    
    public int getNumero() {
        return numero;
    }
}
