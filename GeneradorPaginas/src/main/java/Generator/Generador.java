/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import Resources.PreSets;
import java.util.HashMap;
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
        String Q = "<head><title>"+ T +"</title><style type=\"text/css\">"+ PreSets.CSS_PREDEFINED +"</style></head><body><div class=\"content-container\">"+ generateComponents() +"</div></body>";
        String H = "<html>"+ Q +"</html>";
        String P = "<!DOCTYPE html>" + H;
        return P;
    }
    
    private String generateComponents() {
        String  htmlElements = "";
        for(Map.Entry entry : terminales.entrySet()) {
            String currLabel = entry.getKey().toString().toUpperCase();
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
        for(String vals : object.values()) if(gramatica.hasRule("CLASE", vals)) clases += vals + " "; 
        
        if(rule.contains("LISTITEM")) {
            String[] items = contenido.split(",(?![^{]*\\})");
            String itemsStr = "";
            for(String it : items) {
                if(it.contains("{") && it.contains("}")) {
                    String otherElement = recursiveElement(it.replace("{", "").replace("}", ""));
                    itemsStr += this.gramatica.getRule("LISTITEM").get(0).replace("CLASS", clases).replace("CONTENT", "► "+otherElement);
                }
                else itemsStr += this.gramatica.getRule("LISTITEM").get(0).replace("CLASS", clases).replace("CONTENT", "► "+it);
            }
            rule = rule.replace("LISTITEM", itemsStr);
        } else if(rule.contains("TABLECONT")) {
            String[] filas = contenido.split("(?<!\\{[^\\}]*)(\\|)(?![^\\{]*\\})");
            String formatedTable = "";
            for(String row : filas) {
                String cells = "";
                String[] items = row.split(",(?![^{]*\\})");
                for(String it : items) {
                    if(it.contains("{") && it.contains("}")) {
                        String otherElement = recursiveElement(it.replace("{", "").replace("}", ""));
                        cells += this.gramatica.getRule("CELL").get(0).replace("CONTENT", otherElement);
                    } else cells += this.gramatica.getRule("CELL").get(0).replace("CONTENT", it);
                }
                formatedTable += this.gramatica.getRule("ROW").get(0).replace("CELL", cells);
            }
            rule = rule.replace("TABLECONT", formatedTable);
        }
        
        String url = object.get("url") != null ? object.remove("url") : "";
        rule = rule.replace("TEXTOLINK", url);
        rule = rule.replace("TEXTOCONT", contenido);
        rule = rule.replace("CLASS", clases);
        return rule;
    }
    
    
    private String recursiveElement(String element) {
        String stringValue = "";
        Map<String, String> object = new HashMap<>();

        String[] parts = element.split("\\|");
        
        

        stringValue = parts[0].trim();

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            String[] keyValue = part.split("\\s", 2); 
            if (keyValue.length != 2) {
                System.out.println(part);
                throw new IllegalArgumentException("Formato de elemento de mapa no válido: se espera 'clave valor'");
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            object.put(key, value);
        } 
        
        return generateElement(stringValue.toUpperCase(), object);
    }

}
