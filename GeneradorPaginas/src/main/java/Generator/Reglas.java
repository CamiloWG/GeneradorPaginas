/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Camilo & Paula
 */
public class Reglas {
    private final List<String> reglas;
    public final char simbolo;
    
    public Reglas(char simbolo) {
        this.simbolo = simbolo;
        this.reglas = new ArrayList<>();
    }
    
    
    public void addRegla(String regla) {
        this.reglas.add(regla);  
    }
    
    public boolean deleteRegla(String regla) {
        return this.reglas.removeIf(reglaStr -> reglaStr.equals(regla));
    }
    
    public List<String> getReglas() {
        return this.reglas;
    }
    
    public String getReglaAleatoria() {
        Random rand = new Random();
        String rule = reglas.get(rand.nextInt(reglas.size()));
        return !rule.equals("^") ? rule : "";
    }
}
