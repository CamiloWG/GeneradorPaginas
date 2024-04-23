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
    private String contentGenerated;
    
    public Generador(Map<String, Map<String, String>> terminales) {
        this.terminales = terminales;
    }
    
    
    private void generateBase() {
        String T = terminales.get("pagina") != null ? terminales.get("pagina").get("contenido") : "Sin Titulo";
        String Q = "<head><title>"+ T +"</title></head><body>"+ B +"</body>";
        String H = "<html>"+ Q +"</html>";
        String P = "<!DOCTYPE html>" + H;
    }
}
