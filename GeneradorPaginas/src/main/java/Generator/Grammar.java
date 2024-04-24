/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Camilo & Paula
 */
public class Grammar {
    
    private Map<String, List<String>> rules;
    
   public Grammar() {
       this.rules = new HashMap<>(); 
   }
   
   
   public void addRule(String key, String[] rules) {
       this.rules.put(key, Arrays.asList(rules));
   }
}
