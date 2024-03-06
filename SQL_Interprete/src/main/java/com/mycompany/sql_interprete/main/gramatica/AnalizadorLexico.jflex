package com.mycompany.sql_interprete.main.gramatica;

import java_cup.runtime.*;

%% // Separador de Area

%class Lexico
%public
%cup
%line
%column
%eofval{
    return symbol(sym.EOF);
%eofval}

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

Saltos = \r|\n|\r\n
Espacios = {Saltos} | [ \t\f]

Palabra = [a-zA-Z][a-zA-Z]*[0-9]*
Digito = [0-9][0-9]*

%% // Separador de Area

<YYINITIAL> {

    SELECCIONAR { return symbol(sym.SELECCIONAR); }

    INSERTAR { return symbol(sym.INSERTAR); }

    ACTUALIZAR { return symbol(sym.ACTUALIZAR); }

    ELIMINAR { return symbol(sym.ELIMINAR); }
    
    EN { return symbol(sym.EN); }

    FILTRAR { return symbol(sym.FILTRAR); }

    VALORES { return symbol(sym.VALORES); }

    ASIGNAR { return symbol(sym.ASIGNAR); }

    "\""        { return symbol(sym.COMILLA); }

    "("        { return symbol(sym.PARANTESIS_I); }

    ")"        { return symbol(sym.PARANTESIS_D); }
    
    "."		{ return symbol(sym.PUNTO); }

    ","		{ return symbol(sym.COMA); }

    ";"		{ return symbol(sym.PUNTO_Y_COMA); }

    "*"		{ return symbol(sym.POR); }

    "="		{ return symbol(sym.IGUAL); }

    "<"		{ return symbol(sym.MENOR); }

    ">"		{ return symbol(sym.MAYOR); }

    "<="	{ return symbol(sym.MENOR_IGUAL); }

    ">="	{ return symbol(sym.MAYOR_IGUAL); }

    {Palabra}	{ return symbol(sym.PALABRA, yytext()); }

    {Digito}	{ return symbol(sym.ENTERO, yytext()); }

    {Espacios} 	{/* ignoramos */}

}

[^]             { throw new Error("Error Léxico caracter Invalido en la linea " + yyline + ", columna " + yycolumn + ": " + yytext()); } 
