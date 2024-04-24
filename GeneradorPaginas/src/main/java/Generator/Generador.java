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
    
    public Generador(Map<String, Map<String, String>> terminales, Grammar gramatica) {
        this.terminales = terminales;
        this.gramatica = gramatica; 
    }
    
   
    public String generatePage() {
        String T = terminales.get("pagina") != null ? terminales.remove("pagina").get("contenido") : "Sin Titulo";
        String Q = "<head><title>"+ T +"</title></head><body>"+ generateComponents() +"</body>";
        String H = "<html>"+ Q +"</html>";
        String P = "<!DOCTYPE html>" + H;
        return P;
    }
    
    private String generateComponents() {
        String  htmlElements = "";
        
        
        for(Map.Entry entry : terminales.entrySet()) {
            String currLabel = entry.getKey().toString().toUpperCase();
            System.out.println(currLabel);
            if(this.gramatica.hasRule("CONTENT", currLabel)) {
                htmlElements += generateElement(currLabel, (Map<String, String>)entry.getValue());
            }
        }
        
        return htmlElements;
    }
    
    private String generateElement(String element, Map<String, String> object) {
        String rule = this.gramatica.getRule(element).get(0);
        String contenido = object.get("contenido") != null ? object.remove("contenido") : "";
        String clases = "";
        for(String vals : object.values()) clases += vals + " "; 
        
        String url = object.get("url") != null ? object.remove("url") : "";
        rule = rule.replace("TEXTOLINK", url);
        rule = rule.replace("TEXTOCONT", contenido);
        rule = rule.replace("CLASS", clases);
        return rule;
    }
}
