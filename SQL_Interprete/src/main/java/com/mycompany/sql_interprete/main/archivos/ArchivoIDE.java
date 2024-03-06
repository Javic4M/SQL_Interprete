
package com.mycompany.sql_interprete.main.archivos;

import com.mycompany.sql_interprete.main.nodoArbol.NodoArbol;
import java.io.File;
import java.io.IOException;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ArchivoIDE {
    
    private Document documento;
    
    public void escribirArchivoIDE(String path) {
        File file = new File(path);
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();
            
            Document documento = implementacion.createDocument(null, "REPORTE", null);
            
            Element proyecto = documento.createElement("PROYECTO");
            proyecto.setAttribute("nombre", file.getName());
            escribirEtiquetas(documento, proyecto, file);
            
            documento.getDocumentElement().appendChild(proyecto);        
            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File(path + "\\Reporte.ide"));
            
            Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            System.out.println("Error: En el Parser");
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error: En el TransCong");
        } catch (TransformerException ex) {
            System.out.println("Error: En el Transformer");
        }
    }
    
    private void escribirEtiquetas(Document documento, Element padre, File file) {
        File[] archivos = file.listFiles();
        Element carpeta = null;
        
        if (archivos != null) {           
            for (File archivo : archivos) {
                
                if (archivo.isDirectory()) {
                    carpeta = documento.createElement("CARPETA");
                    carpeta.setAttribute("nombre", archivo.getName());
                    padre.appendChild(carpeta);
                    escribirEtiquetas(documento, carpeta, archivo);
                } else {
                    Element archiv = documento.createElement("ARCHIVO");
                    archiv.setAttribute("nombre", archivo.getName());
                    archiv.setAttribute("ubicacion", archivo.getPath());
                    padre.appendChild(archiv);
                }
            }
        } else {
            System.out.println("No tiene Hijos");
        }
    }
    
    public void leerArchivoIDE(String path, NodoArbol nodoPadre, DefaultTreeModel modelo, JTree arbol) {
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            documento = builder.parse(new File(path));
        
            NodeList listaDeNodos = documento.getElementsByTagName("PROYECTO");
            recursion(listaDeNodos, nodoPadre, modelo, arbol, true);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error: En el Parser");
        }
    }
    
    private void recursion(NodeList listaDeNodos, NodoArbol nodoPadre, DefaultTreeModel modelo, JTree arbol, boolean crear) {
            
        for (int i = 0; i < listaDeNodos.getLength(); i++) {
            Node node = listaDeNodos.item(i);
            System.out.println("Nombre: " + node.getNodeName());
            
            NodoArbol nuevoNodo = null;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;
                
                if (crear) {
                    nodoPadre = new NodoArbol(elemento.getAttribute("nombre"), elemento.getAttribute("ubicacion"));
                    modelo = new DefaultTreeModel(nodoPadre);
                    arbol.setModel(modelo);
                } else {
                    nuevoNodo = new NodoArbol(elemento.getAttribute("nombre"), elemento.getAttribute("ubicacion"));
                    modelo.insertNodeInto(nuevoNodo, nodoPadre, i);
                }
                
                System.out.println("N: " + elemento.getAttribute("nombre") + ", Ubicacion: " + elemento.getAttribute("ubicacion"));
                System.out.println();              
            }

            if (node.hasChildNodes()) {
                if (crear) {
                    nodoPadre.noEsUnArchivo();
                    recursion(node.getChildNodes(), nodoPadre, modelo, arbol, false);
                } else {
                    nuevoNodo.noEsUnArchivo();
                    recursion(node.getChildNodes(), nuevoNodo, modelo, arbol, false);
                }
            }
        }
    }
    
    
    
    /*
    
    try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            documento = builder.parse(new File(path));

            NodeList listaDeNodos = documento.getElementsByTagName("PROYECTO");
            
            for (int i = 0; i < listaDeNodos.getLength(); i++) {
                Node node = listaDeNodos.item(i);
                System.out.println("Nombre: " + node.getNodeName());
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    System.out.println("Elemetnos: " + elemento.getAttribute("nombre"));
                }
                
                if (node.hasChildNodes()) {
                    NodeList listaSecuandaria = node.getChildNodes();
                    
                    for (int j = 0; j < listaSecuandaria.getLength(); j++) {
                        Node node2 = listaSecuandaria.item(j);
                        System.out.println("Nombre: " + node2.getNodeName());
                        
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            Element elemento = (Element) node2;
                            System.out.println("Elemetnos: " + elemento.getAttribute("nombre"));
                        }
                        
                        if (node.hasChildNodes()) {
                            
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error: En el Parser");
        }
    */
}
 