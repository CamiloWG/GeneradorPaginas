/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Camilo & Paula
 */
public class Tokenizer {
    private static final String[] validElements = {"pagina", "titulo", "texto", "imagen", "link", "lista", "video", "tabla"};
    public Map<String, Map<String, String>> elementos;
    private String textFile;
    
    public Tokenizer(String textFile) {
        this.elementos = new HashMap<>();
        this.textFile = textFile;
    }
    
    
    private String[] elementsSplitted() {
        return this.textFile.split("-");
    }
    
    
    public void makeElements() throws Exception {
        for(String element : elementsSplitted()) {
            String[] elementParts = element.split("\n");
            
            if(elementParts[0].equals("")) System.arraycopy(elementParts, 1, elementParts, 0, elementParts.length-1);
            
            if(this.checkIsValidElement(elementParts[0])) {
                Map<String, String> clases = new HashMap<>();
                String[] props = new String[elementParts.length - 1];
                System.arraycopy(elementParts, 1, props, 0, props.length);
                
                for(String clase : props) {
                    String[] parts = clase.split("\\s+", 2);
                    if (parts.length == 2) {
                        clases.put(parts[0], parts[1]);
                    }
                }
                
                this.elementos.put(elementParts[0], clases);
                
            } else throw new Exception("ERROR: No se reconoce el elemento "+ elementParts[0]);
        }
        
        System.out.println(this.elementos);
    }
    
    private boolean checkIsValidElement(String tag) {
        for(String str : validElements) {
            if(tag.equals(str)) return true;
        }
        return false;
    }
}
