echo "STARTING JFLEX COMPILING"
java -jar "C:\jflex-1.9.1\lib\jflex-full-1.9.1.jar" -d "C:\Users\DELL\OneDrive\Documentos\NetBeansProjects\SQL_Interprete\src\main\java\com\mycompany\sql_interprete\main\gramatica" AnalizadorLexico.jflex

echo "STARTING CUP COMPILING"
java -jar "C:\java-cup-11b.jar" -parser Sintactico AnalizadorSintactico.cup
