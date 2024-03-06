
package com.mycompany.sql_interprete.main.archivos;

import com.mycompany.sql_interprete.main.fronted.TablaDeResultados;
import com.mycompany.sql_interprete.main.nodoArbol.NodoArbol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConsultasCSV {
    
    private ArchivoCSV leer;
    private JFrame jframe;
    private boolean tieneElementos = false, esUnNumero = false;
    private String columnaElegida = "", signo = "", valor = "";
    
    public ConsultasCSV(JFrame jframe) {
        this.jframe = jframe;
        leer = new ArchivoCSV();
    }
    
    public void consultar(NodoArbol raiz, String consulta) {
        String[] instruccion = consulta.split(" ");
        String columnas = "", pathUbicacion = "", filtroOValor = "";
        
        if ("SELECCIONAR".equals(instruccion[0])) {
            int indice = 1;
            
            if ("*".equals(instruccion[indice])) {
                columnas = "*";
                indice++;
            } else {
                columnas = instruccion[indice];
                indice++; 
            }
            indice++;        
            pathUbicacion += instruccion[indice];
            indice += 2;
            filtroOValor += instruccion[indice];
            seleccionar(raiz, columnas, pathUbicacion, filtroOValor);
        } else if ("INSERTAR".equals(instruccion[0])) {
            int indice = 2;
            pathUbicacion = instruccion[indice];
            indice++;
            
            if ("*".equals(instruccion[indice])) {
                columnas = "*";
            } else {
                columnas = instruccion[indice];
            }
            indice = indice + 2;
            filtroOValor = instruccion[indice];
            
            String[] filtroEnPartes = filtroOValor.split("");
            String unionFiltro = "";

            for (int i = 0; i < filtroEnPartes.length - 1; i++) {
                unionFiltro += filtroEnPartes[i];
            }
            filtroOValor = unionFiltro;           
            insertar(raiz, columnas, pathUbicacion, filtroOValor);
        } else if ("ACTUALIZAR".equals(instruccion[0])) {
            int indice = 2;
            pathUbicacion = instruccion[indice];
            indice += 2;
            columnas = instruccion[indice];
            indice++;
            
            if (indice != instruccion.length) {
                indice++;
                filtroOValor = instruccion[indice];
            } else {
                String[] columnasNuevas = columnas.split("");
                String unionColumnas = "";
                
                for (int i = 0; i < columnasNuevas.length - 1; i++) {
                    unionColumnas += columnasNuevas[i];
                }
                columnas = unionColumnas;
            }   
            actualizar(raiz, columnas, pathUbicacion, filtroOValor);
        } else if ("ELIMINAR".equals(instruccion[0])) {
            int indice = 2;
            pathUbicacion = instruccion[indice];
            indice++;
            
            if (indice != instruccion.length) {
                indice++;
                filtroOValor = instruccion[indice];
            } else {
                String[] pathRecortado = pathUbicacion.split("");
                String unionPath = "";
                
                for (int i = 0; i < pathRecortado.length - 1; i++) {
                    unionPath += pathRecortado[i];
                }
                pathUbicacion = unionPath;
            }
            eliminar(raiz, pathUbicacion, filtroOValor);
        }
    }
    
    private void seleccionar(NodoArbol raiz, String columnas, String pathUbicacion, String filtro) {
        List<String> ubicacion = construirPath(pathUbicacion);        
        List<ContenidoCSV> contenido = leer.leerCSV(buscarPath(raiz, ubicacion));      
        List<String> todasLasColumnas = obtenerTodasLasColumnas(contenido);
        boolean entrar = true;
        
        if (!"*".equals(columnas)) {
            String[] columnasSeleccionadas = columnas.split(",");
            construirFiltro(filtro);         
            
            for (int i = 0; i < todasLasColumnas.size(); i++) {  
                for (int j = 0; j < columnasSeleccionadas.length; j++) {
                    if (contenido.get(i).getColumna().equals(columnasSeleccionadas[j])) {
                        contenido.get(i).siMostrar();
                    }
                }
            }
            
            for (int i = todasLasColumnas.size(); i < contenido.size(); i++) {              
                for (int j = 0; j < columnasSeleccionadas.length; j++) {
                    
                    if (contenido.get(i).getColumna().equals(columnasSeleccionadas[j])) {
                        // Falta Evaluar si la columna Encontrada tiene restriccion, PUEDE SER QUE YA ESTE RESUELTO
                        
                        if (contenido.get(i).getColumna().equals(columnaElegida)) {
                            if (esUnNumero) {
                                try {
                                    int valorNumero = Integer.parseInt(valor);
                                    int valorLista = 0;
                                    if (!"Null".equals(contenido.get(i).getContenido())) {
                                        valorLista = Integer.parseInt(contenido.get(i).getContenido());
                                    }

                                    if ("=".equals(signo)) {
                                        if (valorLista == valorNumero) {
                                            contenido.get(i).siMostrar();
                                        }
                                    } else if ("<".equals(signo)) {
                                        if (valorLista < valorNumero) {
                                            contenido.get(i).siMostrar();
                                        }
                                    } else if (">".equals(signo)) {
                                        if (valorLista > valorNumero) {
                                            contenido.get(i).siMostrar();
                                        }
                                    } else if ("<=".equals(signo)) {
                                        if (valorLista <= valorNumero) {
                                            contenido.get(i).siMostrar();
                                        }
                                    } else if (">=".equals(signo)) {
                                        if (valorLista >= valorNumero) {
                                            contenido.get(i).siMostrar();
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null,"Se esta comparando un texto con un Número","Error",JOptionPane.ERROR_MESSAGE);
                                    entrar = false;
                                }
                            } else {
                                if (contenido.get(i).getContenido().equals(valor)) {     
                                    contenido.get(i).siMostrar();
                                }
                            }
                        } else {
                            contenido.get(i).siMostrar();
                        }
                        break;
                    }
                }
            }
        } else {
            for (ContenidoCSV contenidoCSV : contenido) {
                if (contenidoCSV.getColumna().equals(columnaElegida)) {
                    if (esUnNumero) {
                        try {
                            int valorNumero = Integer.parseInt(valor);
                            int valorLista = 0;
                            if (!"Null".equals(contenidoCSV.getContenido())) {
                                valorLista = Integer.parseInt(contenidoCSV.getContenido());
                            }
                            if ("=".equals(signo)) {
                                if (valorLista == valorNumero) {
                                    contenidoCSV.siMostrar();
                                }
                            } else if ("<".equals(signo)) {
                                if (valorLista < valorNumero) {
                                    contenidoCSV.siMostrar();
                                }
                            } else if (">".equals(signo)) {
                                if (valorLista > valorNumero) {
                                    contenidoCSV.siMostrar();
                                }
                            } else if ("<=".equals(signo)) {
                                if (valorLista <= valorNumero) {
                                    contenidoCSV.siMostrar();
                                }
                            } else if (">=".equals(signo)) {
                                if (valorLista >= valorNumero) {
                                    contenidoCSV.siMostrar();
                                }
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,"Se esta comparando un texto con un Número","Error",JOptionPane.ERROR_MESSAGE);
                            entrar = false;
                        }
                    } else {
                        if (contenidoCSV.getContenido().equals(valor)) {     
                            contenidoCSV.siMostrar();
                        }
                    }
                } else {
                    contenidoCSV.siMostrar();
                }
            }
        }
        if (entrar) {
            TablaDeResultados tabla = new TablaDeResultados(jframe, contenido);
            tabla.setVisible(true);    
        }
    }
    
    private void insertar(NodoArbol raiz, String columnas, String pathUbicacion, String valoresEnviados) {
        List<String> ubicacion = construirPath(pathUbicacion);
        List<String> todasLasColumnas = new ArrayList<>();
        String path = buscarPath(raiz, ubicacion);
        List<ContenidoCSV> contenido = leer.leerCSV(path);    
        
        // ------------------------------------------------------------------------------
        String[] valoresAIngresar = valoresEnviados.split(",");

        String[] valorInicial = valoresAIngresar[0].split("");
        String unionDelValorSinParentesis = "";         
        for (int i = 1; i < valorInicial.length; i++) {
            if (!"\"".equals(valorInicial[i])) {
                unionDelValorSinParentesis += valorInicial[i];
            }
        }
        valoresAIngresar[0] = unionDelValorSinParentesis;

        String[] valorFinal = valoresAIngresar[valoresAIngresar.length - 1].split("");
        unionDelValorSinParentesis = "";           
        for (int i = 0; i < (valorFinal.length - 1); i++) {
            if (!"\"".equals(valorFinal[i])) {
                unionDelValorSinParentesis += valorFinal[i];
            }
        }
        valoresAIngresar[valoresAIngresar.length - 1] = unionDelValorSinParentesis;
        // -------------------------------------------------------------------------------
        
        todasLasColumnas = obtenerTodasLasColumnas(contenido);
        if (!"*".equals(columnas)) {
            String[] columnasSeleccionadas = columnas.split(",");
   
            String[] parteInicial = columnasSeleccionadas[0].split("");
            String unionSinParentisis = "";         
            for (int i = 1; i < parteInicial.length; i++) {
                unionSinParentisis += parteInicial[i];
            }
            columnasSeleccionadas[0] = unionSinParentisis;

            String[] parteFinal = columnasSeleccionadas[columnasSeleccionadas.length - 1].split("");
            unionSinParentisis = "";           
            for (int i = 0; i < (parteFinal.length - 1); i++) {
                unionSinParentisis += parteFinal[i];
            }
            columnasSeleccionadas[columnasSeleccionadas.length - 1] = unionSinParentisis;
            
            for (int i = 0; i < todasLasColumnas.size(); i++) {
                boolean fueSeleccionada = false;
                
                for (int j = 0; j < columnasSeleccionadas.length; j++) {
                    if (columnasSeleccionadas[j].equals(todasLasColumnas.get(i))) {
                        contenido.add(new ContenidoCSV(valoresAIngresar[j],"",i+1));
                        fueSeleccionada = true;
                        break;
                    }
                }
                if (!fueSeleccionada) {
                    contenido.add(new ContenidoCSV("Null","",i+1));
                }
            }
        } else {
            for (int i = 0; i < todasLasColumnas.size(); i++) {
                contenido.add(new ContenidoCSV(valoresAIngresar[i],"",i+1));
            }
        }
        leer.escribirCSVPorLista(path, todasLasColumnas.size(), contenido);
    }
    
    private void actualizar(NodoArbol raiz, String columnas, String pathUbicacion, String filtro) {
        List<String> ubicacion = construirPath(pathUbicacion); 
        String path = buscarPath(raiz, ubicacion);
        List<ContenidoCSV> contenido = leer.leerCSV(path);  
        List<String> todasLasColumnas = obtenerTodasLasColumnas(contenido);
        boolean hayFiltro = false;
        
        String[] columnasYValores = columnas.split(",");
        if (!"".equals(filtro)) {
            System.out.println("Hay Filtro");
            hayFiltro = true;
            construirFiltro(filtro);
        }
        
        for (String columna : columnasYValores) {
            String[] columnasACambiar = columna.split("");
            String columnaSeleccionada = "", valorActual = "";   
            int indice = 0;
            
            while (!"=".equals(columnasACambiar[indice])) {
                columnaSeleccionada += columnasACambiar[indice];
                indice++;
            }
            indice++;

            if ("\"".equals(columnasACambiar[indice])) {
                indice++;
                        
                for (int i = indice; i < columnasACambiar.length - 1; i++) {
                    valorActual +=  columnasACambiar[i];
                }
            } else {
                for (int i = indice; i < columnasACambiar.length; i++) {
                    valorActual +=  columnasACambiar[i];
                }
            }
            
            for (int i = 0; i < contenido.size(); i++) { 
                if (contenido.get(i).getColumna().equals(columnaSeleccionada) && !contenido.get(i).getContenido().equals(columnaSeleccionada)) {
                    if (hayFiltro) {
                        System.out.println("Filtro");
                        if (esUnNumero) {
                            if (contenido.get(i).getColumna().equals(columnaElegida)) {
                                int valorNumero = Integer.parseInt(valor);
                                int valorLista = Integer.parseInt(contenido.get(i).getContenido());

                                if ("=".equals(signo)) {
                                    if (valorLista == valorNumero) {
                                        contenido.get(i).setContenido(valorActual);
                                    }
                                } else if ("<".equals(signo)) {
                                    if (valorLista < valorNumero) {
                                        contenido.get(i).setContenido(valorActual);
                                    }
                                } else if (">".equals(signo)) {
                                    if (valorLista > valorNumero) {
                                        contenido.get(i).setContenido(valorActual);
                                    }
                                } else if ("<=".equals(signo)) {
                                    if (valorLista <= valorNumero) {
                                        contenido.get(i).setContenido(valorActual);
                                    }
                                } else if (">=".equals(signo)) {
                                    if (valorLista >= valorNumero) {
                                        contenido.get(i).setContenido(valorActual);
                                    }
                                }
                            } else {
                                contenido.get(i).setContenido(valorActual);
                            }
                        } else {
                            if (contenido.get(i).getColumna().equals(columnaElegida)) {
                                if (contenido.get(i).getContenido().equals(valor)) {     
                                    contenido.get(i).setContenido(valorActual);
                                }
                            } else {
                                contenido.get(i).setContenido(valorActual);
                            }
                        }
                    } else {
                        System.out.println("Entro al LLenado Completo");
                        contenido.get(i).setContenido(valorActual);
                    }
                }
            }
            columnaSeleccionada = "";
            valorActual = "";
        }
        leer.escribirCSVPorLista(path, todasLasColumnas.size(), contenido);
    }
    
    private void eliminar(NodoArbol raiz, String pathUbicacion, String filtro) {
        List<String> ubicacion = construirPath(pathUbicacion); 
        String path = buscarPath(raiz, ubicacion);
        List<ContenidoCSV> contenido = leer.leerCSV(path);
        List<String> todasLasColumnas = obtenerTodasLasColumnas(contenido);
        
        if (tieneElementos) {
            if ("".equals(filtro)) {
                int limite = contenido.size(), contador = todasLasColumnas.size();

                for (int i = todasLasColumnas.size(); i < limite; i++) {
                    contenido.remove(contador);
                }
            } else {
                String[] columnasYValores = filtro.split(",");

                for (String columnaYValor : columnasYValores) {
                    construirFiltro(columnaYValor);

                    for (int i = todasLasColumnas.size(); i < contenido.size(); i++) { 
                        if (esUnNumero) {
                            if (contenido.get(i).getColumna().equals(columnaElegida)) {
                                int valorNumero = Integer.parseInt(valor);
                                int valorLista = Integer.parseInt(contenido.get(i).getContenido());

                                if ("=".equals(signo)) {
                                    if (valorLista == valorNumero) {
                                        contenido.get(i).setContenido("null");
                                    }
                                } else if ("<".equals(signo)) {
                                    if (valorLista < valorNumero) {
                                        contenido.get(i).setContenido("null");
                                    }
                                } else if (">".equals(signo)) {
                                    if (valorLista > valorNumero) {
                                        contenido.get(i).setContenido("null");
                                    }
                                } else if ("<=".equals(signo)) {
                                    if (valorLista <= valorNumero) {
                                        contenido.get(i).setContenido("null");
                                    }
                                } else if (">=".equals(signo)) {
                                    if (valorLista >= valorNumero) {
                                        contenido.get(i).setContenido("null");
                                    }
                                }
                            }
                        } else {
                            if (contenido.get(i).getColumna().equals(columnaElegida)) {
                                if (contenido.get(i).getContenido().equals(valor)) {     
                                    contenido.get(i).setContenido("null");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("No tiene Elementos");
        }
        leer.escribirCSVPorLista(path, todasLasColumnas.size(), contenido);
    }
    
    private List<String> construirPath(String pathUbicacion) {
        String[] ubicacionPorPartes = pathUbicacion.split("");
        List<String> ubicacion = new ArrayList<>();
        String unionPartes = "";
        
        for (String parte : ubicacionPorPartes) {
            if (!".".equals(parte) ) {
                unionPartes += parte;
            } else {
                ubicacion.add(unionPartes);
                unionPartes = "";
            }
        }
        ubicacion.add(unionPartes);
        ubicacion.remove(0);
        return ubicacion;
    }
    
    private String buscarPath(NodoArbol raiz, List<String> ubicacion) {
        String parteDeLaUbicacion, path = "";
        
        if (ubicacion.size() == 1) {
            parteDeLaUbicacion = ubicacion.get(0) + ".csv";
        } else {
            parteDeLaUbicacion = ubicacion.get(0);
        }
        ubicacion.remove(0);
        
        for (int i = 0; i < raiz.getChildCount(); i++) {
            NodoArbol nodoAComparar = (NodoArbol) raiz.getChildAt(i);
            String nombreAComparar = (String) nodoAComparar.getUserObject();

            if (nombreAComparar.equals(parteDeLaUbicacion)) {
                
                if (ubicacion.isEmpty()) {
                    path = nodoAComparar.obtenerPath();
                } else {
                    path = buscarPath(nodoAComparar, ubicacion);
                }
                break;
            }
        }
        return path;
    }
    
    private List<String> obtenerTodasLasColumnas(List<ContenidoCSV> contenido) {
        List<String> todasLasColumnas = new ArrayList<>();
        int contadorUno = 1;
        tieneElementos = false;
        
        for (ContenidoCSV valor : contenido) {
            if (valor.getNumero() != 1) {
                todasLasColumnas.add(valor.getColumna());
            } else {
                if (contadorUno == 1) {
                    todasLasColumnas.add(valor.getColumna());
                    contadorUno++;
                } else {
                    tieneElementos = true;
                    break;
                }
            }
        }
        return todasLasColumnas;
    }
    
    private void construirFiltro(String filtro) {
        String[] filtroEnPartes = filtro.split("");
        String unionSinPuntoYComa = "";
        columnaElegida = "";
        valor =  "";
        
        for (int i = 0; i < filtroEnPartes.length - 1; i++) {
            unionSinPuntoYComa += filtroEnPartes[i];
        }
        filtroEnPartes = unionSinPuntoYComa.split("");
        int indice = 0;
        
        while (!"=".equals(filtroEnPartes[indice]) && !"<".equals(filtroEnPartes[indice]) && !">".equals(filtroEnPartes[indice]) && !"<=".equals(filtroEnPartes[indice]) && !">=".equals(filtroEnPartes[indice])) {
            columnaElegida += filtroEnPartes[indice];
            indice++;
        }
        signo = filtroEnPartes[indice];
        indice++;
        
        if ("=".equals(filtroEnPartes[indice])) {
            signo += filtroEnPartes[indice];
            indice++;
        }
        
        if ("\"".equals(filtroEnPartes[indice])) {
            indice++;
            esUnNumero = false;
            
            while (!"\"".equals(filtroEnPartes[indice])) {
                valor += filtroEnPartes[indice];
                indice++;
            }
        } else {
            esUnNumero = true;
            
            while (indice != filtroEnPartes.length) {
                valor += filtroEnPartes[indice];
                indice++;
            }
        }
    }
}
