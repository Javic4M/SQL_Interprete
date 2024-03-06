
package com.mycompany.sql_interprete.main.fronted;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class NuevoProyecto extends javax.swing.JDialog {

    private String pathUbicacionDelProyecto = "";
    
    public NuevoProyecto(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.setTitle("Nuevo Proyecto");
        this.setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombreDelProyecto = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        buscarUbicacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Nuevo Proyecto");

        jLabel2.setText("Selecciona una Ubicación para el Proyecto");

        jLabel3.setText("Escriba el Nombre del Proyecto:");

        buscarUbicacion.setText("Buscar Ubicación");
        buscarUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarUbicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buscarUbicacion)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(96, 96, 96))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreDelProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscarUbicacion)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarUbicacionActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int respuesta = jFileChooser.showOpenDialog(this);
                
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            if (verificarNombreDelProyecto()) {
                File file = jFileChooser.getSelectedFile();
                pathUbicacionDelProyecto = file.getAbsolutePath();
                this.dispose();
            }
        }
    }//GEN-LAST:event_buscarUbicacionActionPerformed

    public boolean verificarNombreDelProyecto() {        
        if (!obtenerNombreDelProyecto().isEmpty()) {
            String cadena[] = obtenerNombreDelProyecto().split("");
            boolean salir = false;
            
            for (int i = 0; i < cadena.length; i++) {
                if (" ".equals(cadena[i])) {
                    JOptionPane.showMessageDialog(this,"No puedes dejar Espacios en Blanco","Error",JOptionPane.ERROR_MESSAGE);
                    salir = true;
                    break;
                }
            }
            if (!salir) {
                if (obtenerNombreDelProyecto().matches("[a-zA-Z]*")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this,"Recuerda solo utilizar Letras para el Nombre","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Debes Ingresar un Nombre","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public String obtenerPathUbicacionDelProyecto() {
        return pathUbicacionDelProyecto;
    }
    
    public String obtenerNombreDelProyecto() {
        return String.valueOf(nombreDelProyecto.getText());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarUbicacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombreDelProyecto;
    // End of variables declaration//GEN-END:variables
}
