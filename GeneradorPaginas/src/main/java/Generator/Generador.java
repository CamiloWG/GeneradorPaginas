/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import java.util.Map;

/**
 *
 * @author Camilo & Paula
 */
public class Generador {
    
    private Map<String, Map<String, String>> terminales;
    private Grammar gramatica;
    private String contentGenerated;
    
    public Generador(Map<String, Map<String, String>> terminales) {
        this.terminales = terminales;
        this.gramatica = new Grammar();
    }
    
    
    private void inicializarGramatica() {
        this.gramatica.addRule("CONTENT", new String[]{"TITULO", "TEXTO", "IMAGEN", "LINK", "LISTA", "VIDEO", "TABLA", ""});
        this.gramatica.addRule("TITULO", new String[]{"<h1 class=\" CLASS \"> TEXTOCONT </h1>"});
        this.gramatica.addRule("TEXTO", new String[]{"<p class=\" CLASS \"> TEXTOCONT </p>"});
        this.gramatica.addRule("IMAGEN", new String[]{ "<img src=\" TEXTOCONT \" alt=\"Imagen\" class=\" CLASS \">"});
        this.gramatica.addRule("LINK", new String[]{ "<a href=\" TEXTOLINK \" class=\" CLASS \"> TEXTOCONT </a>" });
        this.gramatica.addRule("LISTA", new String[]{"ULIST", "OLIST"});
        this.gramatica.addRule("ULIST", new String[]{"<ul class=\" CLASS \"> LISTITEM </ul>"});
        this.gramatica.addRule("OLIST", new String[]{"<ol class=\" CLASS \"> LISTITEM </ol>"});
        this.gramatica.addRule("LISTITEM", new String[]{"<li class=\" CLASS \"> CONTENT </li>", "<li class=\" CLASS \"> CONTENT </li> LISTITEM" });
        this.gramatica.addRule("VIDEO", new String[]{ "<iframe src=\" TEXTOCONT \" frameborder=\"0\" allowfullscreen class=\" CLASS \"></iframe>" });
        this.gramatica.addRule("TABLA", new String[]{ "<table class=\" CLASS \"> TABLECONT </table>" });
        this.gramatica.addRule("TABLECONT", new String[]{ "ROW", "ROW TABLECONT" });
        this.gramatica.addRule("ROW", new String[]{ "<tr> CELL </tr>" });
        this.gramatica.addRule("CELL", new String[]{ "<td class=\" CLASS \"> CONTENT </td>", "<td class=\" CLASS \"> CONTENT </td> CELL" });
        this.gramatica.addRule("CLASS", new String[]{ "CLASE", "CLASE CLASS", "" });
        this.gramatica.addRule("CLASE", new String[]{ "grande", "mediano", "pequeño", "azul", "amarillo", "rojo", "negro", "negrilla", "cursiva", "normal", "subrayado", "centrado", "izquierda", "derecha", "justificado", "TimesNewRoman", "Arial", "Verdana", "border", "sin-borde", "borde-redondeado", "borde-rojo", "borde-azul", "borde-verde" });
    }
    
    
    private void generateBase() {
        String T = terminales.get("pagina") != null ? terminales.remove("pagina").get("contenido") : "Sin Titulo";
        String Q = "<head><title>"+ T +"</title></head><body>"+ generateComponents() +"</body>";
        String H = "<html>"+ Q +"</html>";
        String P = "<!DOCTYPE html>" + H;
    }
    
    private String generateComponents() {
        String  htmlElements = "";
        
        
        
        return htmlElements;
    }
}
