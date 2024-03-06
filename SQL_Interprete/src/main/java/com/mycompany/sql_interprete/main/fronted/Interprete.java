
package com.mycompany.sql_interprete.main.fronted;

import com.mycompany.sql_interprete.main.archivos.ArchivoCreacionYEliminacion;
import com.mycompany.sql_interprete.main.archivos.ArchivoIDE;
import com.mycompany.sql_interprete.main.archivos.ArchivoVisualizacionYGuardado;
import com.mycompany.sql_interprete.main.archivos.CargarArchivo;
import com.mycompany.sql_interprete.main.archivos.ConsultasCSV;
import com.mycompany.sql_interprete.main.gramatica.Lexico;
import com.mycompany.sql_interprete.main.gramatica.Sintactico;
import com.mycompany.sql_interprete.main.nodoArbol.NodoArbol;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;

public class Interprete extends javax.swing.JFrame {

    private DefaultTreeModel modelo;
    private NodoArbol nodoRaiz ,nodoSeleccionado;
    private List<String> lista = new ArrayList<String>();
    private boolean eliminar = false, crear = false, proyectoActivo = false;
    
    public Interprete() {
        initComponents();
        this.setTitle("Interprete");
        this.setLocationRelativeTo(null);
        crerNodoRaiz();
        play.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/play.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
        guardarYCerrar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/save.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
        crearFolderYArchivos.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/create.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
        eliminarNodo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/delete.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        arbolArchivos = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaDeConsola = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        pestañasDeArchivos = new javax.swing.JTabbedPane();
        play = new javax.swing.JButton();
        guardarYCerrar = new javax.swing.JButton();
        crearFolderYArchivos = new javax.swing.JButton();
        eliminarNodo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        cargarProyecto = new javax.swing.JMenuItem();
        nuevoProyecto = new javax.swing.JMenuItem();
        cargarProyectoPorIde = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        arbolArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                arbolArchivosMousePressed(evt);
            }
        });
        arbolArchivos.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arbolArchivosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(arbolArchivos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Area de Consola"));

        jScrollPane2.setViewportView(areaDeConsola);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel"));

        pestañasDeArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pestañasDeArchivosMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pestañasDeArchivos)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pestañasDeArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        guardarYCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarYCerrarActionPerformed(evt);
            }
        });

        crearFolderYArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearFolderYArchivosActionPerformed(evt);
            }
        });

        eliminarNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarNodoActionPerformed(evt);
            }
        });

        archivo.setText("Archivo");

        cargarProyecto.setText("Cargar Proyecto");
        cargarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarProyectoActionPerformed(evt);
            }
        });
        archivo.add(cargarProyecto);

        nuevoProyecto.setText("Nuevo Proyecto");
        nuevoProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProyectoActionPerformed(evt);
            }
        });
        archivo.add(nuevoProyecto);

        cargarProyectoPorIde.setText("Cargar Proyecto por IDE");
        cargarProyectoPorIde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarProyectoPorIdeActionPerformed(evt);
            }
        });
        archivo.add(cargarProyectoPorIde);

        jMenuBar1.add(archivo);

        ayuda.setText("Ayuda");
        jMenuBar1.add(ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(crearFolderYArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                        .addComponent(eliminarNodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarYCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(play, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(guardarYCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(crearFolderYArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(eliminarNodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arbolArchivosValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arbolArchivosValueChanged
        if (proyectoActivo) {
            nodoSeleccionado = (NodoArbol) arbolArchivos.getLastSelectedPathComponent();
        }
    }//GEN-LAST:event_arbolArchivosValueChanged

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        if (!"".equals(obtenerConsulta())) {
            if (proyectoActivo) {
                if (pestañasDeArchivos.getSelectedIndex() == -1) {
                    Sintactico parser = new Sintactico(new Lexico(new StringReader(obtenerConsulta())));

                    try {
                        parser.parse();
                        List<String> intruccionesSeparadas = new ArrayList<>();
                        String[] intrucciones = obtenerConsulta().split("");
                        String union = "";
                        
                        for (int i = 0; i < intrucciones.length; i++) {
                            if (!";".equals(intrucciones[i]) && !"\r".equals(intrucciones[i]) && !"\n".equals(intrucciones[i])) {
                                union += intrucciones[i];
                            } else {
                                if (!"\r".equals(intrucciones[i]) && !"\n".equals(intrucciones[i])) {
                                    union += intrucciones[i];
                                    intruccionesSeparadas.add(union);
                                    union = "";
                                }
                            }
                        }
                        
                        for (String intruccionSeparada : intruccionesSeparadas) {
                            ConsultasCSV consulta = new ConsultasCSV(this);
                            consulta.consultar(nodoRaiz, intruccionSeparada);
                        }
                    } catch (Exception ex) {
                        Symbol sym = parser.getSymbol();

                        if (sym != null) {
                            switch (sym.sym) {
                                case 22, 23 -> JOptionPane.showMessageDialog(this,"Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": " + parser.getNombre(),"Error",JOptionPane.ERROR_MESSAGE);
                                case 0 -> JOptionPane.showMessageDialog(this,"Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": Se esperaba el Resto de la Instrucción","Error",JOptionPane.ERROR_MESSAGE);
                                default -> JOptionPane.showMessageDialog(this,"Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": " + parser.symbl_name_from_id(sym.sym),"Error",JOptionPane.ERROR_MESSAGE);
                            } 
                        }
                    } catch (Error ex){ 
                        JOptionPane.showMessageDialog(this,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Se Recomienda Cerrar todas las Pestañas para un Procesamiento Correcto","Recomendación",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Debes Cargar un Proyecto o Crear uno Antes de realizar una Consulta","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Debes escribir una Instrucción para Ejecutar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_playActionPerformed

    private void pestañasDeArchivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestañasDeArchivosMousePressed
        int indice = pestañasDeArchivos.getSelectedIndex();
        
        if (eliminar) {
            if (indice >= 0) {
                int opcion = JOptionPane.showConfirmDialog(this, "Guardar y Cerrar", "Archivo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (opcion == 0) {
                    // Falta Enlazar el archivo para que cuando lo presione se mande a guardar la info
                    cambiarEstadoDelNodo(nodoRaiz, pestañasDeArchivos.getTitleAt(indice));
                    
                    JTextPane areaDeTexto = (JTextPane) pestañasDeArchivos.getSelectedComponent();
                    ArchivoVisualizacionYGuardado archivo = new ArchivoVisualizacionYGuardado();           
                    archivo.guardarArchivo(buscarPath(nodoRaiz, pestañasDeArchivos.getTitleAt(indice)), areaDeTexto.getText());
                    pestañasDeArchivos.remove(indice);
                }
            }
        }
    }//GEN-LAST:event_pestañasDeArchivosMousePressed

    private void arbolArchivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolArchivosMousePressed
        boolean nombreRepetido = false;
        
        if (proyectoActivo) {
            if (nodoSeleccionado != null) {
                if (crear) {
                    String[] options = {"Crear Folder", "Crear Archivo"};           
                    int seleccion = JOptionPane.showOptionDialog(this, "Seleccione una Opción:","Archivo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if (seleccion == 0) {
                        CrearNuevoFolder nuevo = new CrearNuevoFolder(this, nodoSeleccionado);
                        nuevo.setVisible(true);

                        if (nuevo.creamosFolder()) {
                            NodoArbol nodo = new NodoArbol(nuevo.obtenerNombreDelFolder(), nodoSeleccionado.obtenerPath() + "\\" +  nuevo.obtenerNombreDelFolder());
                            nodo.noEsUnArchivo();

                            for (int i = 0; i < nodoSeleccionado.getChildCount(); i++) {
                                NodoArbol nodoAComparar = (NodoArbol) nodoSeleccionado.getChildAt(i);
                                String nombreAComparar = (String) nodoAComparar.getUserObject();

                                if (nodo.obtenerNombreDelNode().equals(nombreAComparar)) {
                                    JOptionPane.showMessageDialog(this,"Se encontraron dos Archivos con el Mismo Nombre","Error",JOptionPane.ERROR_MESSAGE);
                                    nombreRepetido = true;
                                    break;
                                }
                            }
                            if (!nombreRepetido) {
                                ArchivoCreacionYEliminacion archivo = new ArchivoCreacionYEliminacion();
                                archivo.crearFolder(nodo.obtenerPath());
                                modelo.insertNodeInto(nodo, nodoSeleccionado, nodoSeleccionado.getChildCount());
                                ArchivoIDE ide = new ArchivoIDE();
                                ide.escribirArchivoIDE(nodoRaiz.obtenerPath());
                            }
                        }
                    } else if (seleccion == 1) {
                        CrearNuevoArchivo nuevo = new CrearNuevoArchivo(this);
                        nuevo.setVisible(true);    

                        if (nuevo.creamosArchivo()) {
                            NodoArbol nodo = new NodoArbol(nuevo.obtenerNombreYExtension(), nodoSeleccionado.obtenerPath() + "\\" + nuevo.obtenerNombreYExtension());

                            for (int i = 0; i < nodoSeleccionado.getChildCount(); i++) {
                                NodoArbol nodoAComparar = (NodoArbol) nodoSeleccionado.getChildAt(i);
                                String nombreAComparar = (String) nodoAComparar.getUserObject();

                                if (nodo.obtenerNombreDelNode().equals(nombreAComparar)) {
                                    JOptionPane.showMessageDialog(this,"Se encontraron dos Archivos con el Mismo Nombre","Error",JOptionPane.ERROR_MESSAGE);
                                    nombreRepetido = true;
                                    break;
                                }
                            }
                            if (!nombreRepetido) {
                                ArchivoCreacionYEliminacion archivo = new ArchivoCreacionYEliminacion();
                                archivo.crearArchivo(nodo.obtenerPath());
                                modelo.insertNodeInto(nodo, nodoSeleccionado, nodoSeleccionado.getChildCount());
                                ArchivoIDE ide = new ArchivoIDE();
                                ide.escribirArchivoIDE(nodoRaiz.obtenerPath());
                            }
                        }
                    }
                } else {
                    // Sirve para Presentar el Archivo Seleccionado en Pantalla
                    if (nodoSeleccionado.esUnArchivo()) {
                        if (nodoSeleccionado.isLeaf()) {           
                            if (!nodoSeleccionado.estaAbierto()) {

                                ArchivoVisualizacionYGuardado archivo = new ArchivoVisualizacionYGuardado();
                                lista = archivo.leerArchivo(nodoSeleccionado.obtenerPath());
                                JTextPane areaDeTexto = new JTextPane();

                                for (int i = 0; i < lista.size(); i++) {
                                    if (i == 0) {
                                        areaDeTexto.setText(lista.get(i));
                                    } else {
                                        String textoEnPantalla = areaDeTexto.getText() + "\r";
                                        areaDeTexto.setText((textoEnPantalla + lista.get(i)));
                                    }
                                }
                                pestañasDeArchivos.addTab(nodoSeleccionado.obtenerNombreDelNode(), areaDeTexto);
                                nodoSeleccionado.cambiarDeEstado();
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_arbolArchivosMousePressed

    private void guardarYCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarYCerrarActionPerformed
        if (eliminar) {
            guardarYCerrar.setIcon(new  ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/save.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
            eliminar = false;
        } else {
            guardarYCerrar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/select.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
            eliminar = true;
        }
    }//GEN-LAST:event_guardarYCerrarActionPerformed

    private void crearFolderYArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearFolderYArchivosActionPerformed
        if (crear) {
            crearFolderYArchivos.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/create.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
            crear = false;
        } else {
            crearFolderYArchivos.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/mycompany/sql_interprete/fronted/select.png")).getImage().getScaledInstance(-1, -1, java.awt.Image.SCALE_SMOOTH)));
            crear = true;
        }
    }//GEN-LAST:event_crearFolderYArchivosActionPerformed

    private void cargarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarProyectoActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int respuesta = jFileChooser.showOpenDialog(this);
                
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            nodoRaiz = new NodoArbol(file.getName(), file.getAbsolutePath());
            nodoRaiz.noEsUnArchivo();
            modelo = new DefaultTreeModel(nodoRaiz);
            arbolArchivos.setModel(modelo);
            ArchivoIDE ide = new ArchivoIDE();
            ide.escribirArchivoIDE(nodoRaiz.obtenerPath());
            CargarArchivo cargar = new CargarArchivo();
            cargar.crearArbol(modelo, nodoRaiz, file);
            proyectoActivo = true;
        }
    }//GEN-LAST:event_cargarProyectoActionPerformed

    private void nuevoProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProyectoActionPerformed
        NuevoProyecto nuevoProyecto = new NuevoProyecto(this);
        nuevoProyecto.setVisible(true);
        
        if (!"".equals(nuevoProyecto.obtenerPathUbicacionDelProyecto())) {
            ArchivoCreacionYEliminacion archivo = new ArchivoCreacionYEliminacion();
            archivo.crearFolder(nuevoProyecto.obtenerPathUbicacionDelProyecto() + "\\" + nuevoProyecto.obtenerNombreDelProyecto());
            nodoRaiz = new NodoArbol(nuevoProyecto.obtenerNombreDelProyecto(), nuevoProyecto.obtenerPathUbicacionDelProyecto() + "\\" + nuevoProyecto.obtenerNombreDelProyecto());
            nodoRaiz.noEsUnArchivo();
            modelo = new DefaultTreeModel(nodoRaiz);
            arbolArchivos.setModel(modelo);          
            ArchivoIDE ide = new ArchivoIDE();
            ide.escribirArchivoIDE(nodoRaiz.obtenerPath());
            CargarArchivo cargar = new CargarArchivo();
            cargar.crearArbol(modelo, nodoRaiz, new File(nodoRaiz.obtenerPath()));
            proyectoActivo = true;
        }
    }//GEN-LAST:event_nuevoProyectoActionPerformed

    private void cargarProyectoPorIdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarProyectoPorIdeActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("ide", "ide");
        jFileChooser.setFileFilter(filtrado);
        
        int respuesta = jFileChooser.showOpenDialog(this);
                
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            ArchivoIDE ar = new ArchivoIDE();
            ar.leerArchivoIDE(jFileChooser.getSelectedFile().getAbsolutePath(), nodoRaiz, modelo, arbolArchivos);
        
            //---------------------------------------
            //ArchivoIDE ide = new ArchivoIDE();
            //ide.escribirArchivoIDE(nodoRaiz.obtenerPath());
            //CargarArchivo cargar = new CargarArchivo();
            //cargar.crearArbol(modelo, nodoRaiz, new File(nodoRaiz.obtenerPath()));
            proyectoActivo = true;
        }
    }//GEN-LAST:event_cargarProyectoPorIdeActionPerformed

    private void eliminarNodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarNodoActionPerformed
        if (nodoSeleccionado != null) {
            if (nodoSeleccionado != nodoRaiz) {
                modelo.removeNodeFromParent(nodoSeleccionado);
            } else {
                JOptionPane.showMessageDialog(this,"No puedes eliminar el Nodo Raiz","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (proyectoActivo) {
                JOptionPane.showMessageDialog(this,"Debes seleccionar un Nodo para Eliminar","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_eliminarNodoActionPerformed

    
    // Métodos Propios
    private void crerNodoRaiz() {
        NodoArbol nodoInicial = new NodoArbol("", "");
        nodoInicial.noEsUnArchivo();
        arbolArchivos.setModel(new DefaultTreeModel(nodoInicial));
    }
        
    private void cambiarEstadoDelNodo(NodoArbol nodoPadre, String nombreDelNodoAEliminar) {
        
        for (int i = 0; i < nodoPadre.getChildCount(); i++) {
            NodoArbol nodoACambiar = (NodoArbol) nodoPadre.getChildAt(i);
            String nombreAComparar = (String) nodoACambiar.getUserObject();
            
            if (nodoACambiar.isLeaf()) {
                if (nombreDelNodoAEliminar.equals(nombreAComparar)) {
                    nodoACambiar.cambiarDeEstado();
                }
            } else {
                cambiarEstadoDelNodo(nodoACambiar, nombreDelNodoAEliminar);
            }
        }
    }
    
    private String buscarPath(NodoArbol nodoPadre, String nombreDelNodo) {
        String path = "";
        
        for (int i = 0; i < nodoPadre.getChildCount(); i++) {
            NodoArbol nodoACambiar = (NodoArbol) nodoPadre.getChildAt(i);
            String nombreAComparar = (String) nodoACambiar.getUserObject();

            if (nodoACambiar.isLeaf()) {
                if (nombreDelNodo.equals(nombreAComparar)) {
                    path = nodoACambiar.obtenerPath();
                    break;
                }
            } else {
                path = buscarPath(nodoACambiar, nombreDelNodo);
            }
        }
        return path;
    }
    
    public String obtenerConsulta() {
        return areaDeConsola.getText();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolArchivos;
    private javax.swing.JMenu archivo;
    private javax.swing.JTextPane areaDeConsola;
    private javax.swing.JMenu ayuda;
    private javax.swing.JMenuItem cargarProyecto;
    private javax.swing.JMenuItem cargarProyectoPorIde;
    private javax.swing.JButton crearFolderYArchivos;
    private javax.swing.JButton eliminarNodo;
    private javax.swing.JButton guardarYCerrar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem nuevoProyecto;
    private javax.swing.JTabbedPane pestañasDeArchivos;
    private javax.swing.JButton play;
    // End of variables declaration//GEN-END:variables
}
