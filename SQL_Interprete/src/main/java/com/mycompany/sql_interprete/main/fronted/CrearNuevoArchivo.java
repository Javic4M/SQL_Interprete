
package com.mycompany.sql_interprete.main.fronted;

import javax.swing.JOptionPane;

public class CrearNuevoArchivo extends javax.swing.JDialog {

    private boolean crearArchivo = false;
    
    public CrearNuevoArchivo(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.setTitle("Archivo");
        this.setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreDelFolder = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        extensionDelArchivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Escriba el Nombre del Archivo:");

        jLabel3.setText("Escriba su Extensión (Ej: .txt):");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombreDelFolder, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(extensionDelArchivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreDelFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extensionDelArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Nuevo Archivo");

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 234, Short.MAX_VALUE)
                        .addComponent(aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(aceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        boolean nombreBien = false;
        
        if (!obtenerNombreDelArchivo().isEmpty()) {
            String cadena[] = obtenerNombreDelArchivo().split("");
            boolean salir = false;
            
            for (int i = 0; i < cadena.length; i++) {
                if (" ".equals(cadena[i])) {
                    JOptionPane.showMessageDialog(this,"No puedes dejar Espacios en Blanco","Error",JOptionPane.ERROR_MESSAGE);
                    salir = true;
                    break;
                }
            }
            if (!salir) {
                if (obtenerNombreDelArchivo().matches("[a-zA-Z]*")) {
                    nombreBien = true;
                } else {
                    JOptionPane.showMessageDialog(this,"Recuerda solo utilizar Letras para el Nombre","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Debes Ingresar un Nombre","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if (nombreBien) {
            if (!obtenerExtensionDelArchivo().isEmpty()) {
                String cadena[] = obtenerExtensionDelArchivo().split("");
                String nombreExt = "";
                boolean salir = false;

                if (".".equals(cadena[0])) {
                    for (int i = 1; i < cadena.length; i++) {
                        if (" ".equals(cadena[i])) {
                            JOptionPane.showMessageDialog(this,"No puedes dejar Espacios en Blanco","Error",JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        } else {
                            nombreExt += cadena[i];
                        }
                    }
                    if (!salir) {
                        if (nombreExt.matches("[a-zA-Z]*")) {
                            crearArchivo = true;
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this,"Recuerda solo utilizar Letras para el Nombre","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"La Extensión comienza con una Punto","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Debe Escribir la Extensión del Archivo","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private String obtenerNombreDelArchivo() {
        return String.valueOf(nombreDelFolder.getText());
    }
    
    private String obtenerExtensionDelArchivo() {
        return String.valueOf(extensionDelArchivo.getText());
    }
    
    public String obtenerNombreYExtension() {
        return obtenerNombreDelArchivo() + obtenerExtensionDelArchivo();
    }
    
    public boolean creamosArchivo() {
        return crearArchivo;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField extensionDelArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreDelFolder;
    // End of variables declaration//GEN-END:variables
}
