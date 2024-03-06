
package com.mycompany.sql_interprete.main.fronted;

import com.mycompany.sql_interprete.main.archivos.ContenidoCSV;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TablaDeResultados extends javax.swing.JDialog {

    private List<ContenidoCSV> contenido;
    private DefaultTableModel tablaModelo = new DefaultTableModel();
    private String[] titulos = null;
    
    public TablaDeResultados(java.awt.Frame parent, List<ContenidoCSV> contenido) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.contenido = contenido;
        mostrarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tabla De Resultados");

        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaReporte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1)
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarTabla() {
        int numeroDeFilas = obtenerNumeroDeFilas();
        int numeroDeColumnas = obtenerNumeroDeColumnas();
        int indice = numeroDeColumnas;
        
        tablaReporte.removeAll();
        tablaModelo.setColumnIdentifiers(titulos);
        tablaReporte.setModel(tablaModelo);
        tablaModelo.setRowCount(0);
        
        for (int i = 0; i < numeroDeFilas; i++) {
            Object[] objetos = new Object[titulos.length];
            boolean tieneElementos = false;
            
            int k = 0;
            for (int j = 0; j < numeroDeColumnas; j++) {
                if (contenido.get(indice).isMostrar()) {
                    tieneElementos = true;
                    objetos[k] = contenido.get(indice).getContenido();  
                    k++;
                }
                indice++;
            }
            if (tieneElementos && k == titulos.length) {
                tablaModelo.addRow(objetos);
            }
        }
        tablaReporte.setEnabled(false);
    }
    
    private int obtenerNumeroDeColumnas() {
        int contador = 0, contadorTotal = 0, contadorUno = 1;

        for (ContenidoCSV valor : contenido) { // Obtenemos el Número de Columnas
            if (valor.getNumero() != 1) {
                if (valor.isMostrar()) {
                    contador++;
                }
                contadorTotal++;
            } else {
                if (contadorUno == 1) {
                    if (valor.isMostrar()) {
                        contador++;
                    }
                    contadorUno++;
                    contadorTotal++;
                } else {
                    break;
                }
            }
        }
        String[] columnas = new String[contador];
        int posicion = 0;
        
        for (int i = 0; i < contadorTotal; i++) {
            if (contenido.get(i).isMostrar()) {
                columnas[posicion] = contenido.get(i).getContenido();
                posicion++;
            }
        }
        titulos = columnas;
        return contadorTotal;
    }
    
    private int obtenerNumeroDeFilas() {
        int contadorFilas = -1;
        
        for (ContenidoCSV valor : contenido) {
            if (valor.getNumero() == 1) {
                contadorFilas++;
            }
        }
        return contadorFilas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReporte;
    // End of variables declaration//GEN-END:variables
}
