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
    
    public Grammar(String rules) {
       this.rules = new HashMap<>(); 
       this.readRules(rules);
    }
    
    private void readRules(String rules) {
        String[] lines = rules.split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split("->");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String[] values = parts[1].split("\\|");
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                this.addRule(key, values);
            }
        }
    }


    public void addRule(String key, String[] rules) {
        this.rules.put(key, Arrays.asList(rules));
    }

    public List<String> getRule(String key) {
        return this.rules.get(key);
    }

    public boolean hasRule(String var, String rule) {
        return rules.get(var).contains(rule);
    }
}
