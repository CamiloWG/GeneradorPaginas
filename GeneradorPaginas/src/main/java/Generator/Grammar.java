/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Camilo & Paula
 */
public class Grammar {
    private final List<Character> variables;
    private final List<Character> terminales;
    private char variableInicial;
    private final Map<Character, Reglas> reglas;

    
    public Grammar() {
        this.variables = new ArrayList<>();
        this.terminales = new ArrayList<>();
        this.reglas = new HashMap<>();
    }
    
    public List<Character> getVariables() {
        return variables;
    }

    public boolean addVariable(char var) {
        if(!variables.contains(var)) {
            this.variables.add(var);
            Reglas setReglas = new Reglas(var);
            this.reglas.put(var, setReglas);
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean deleteVariable(char var) {
        if(variables.contains(var)) {
            this.variables.remove(var);
            this.reglas.remove(var);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Character> getTerminales() {
        return terminales;
    }

    public boolean addTerminales(char terminal) {
        if(!this.terminales.contains(terminal)) {
            this.terminales.add(terminal);
            return true;
        } else return false;
    }
    
    public boolean deleteTerminales(char terminal) {
        if(this.terminales.contains(terminal)) {
            this.terminales.remove(terminal);
            return true;
        } else return false;
    }

    public char getVariableInicial() {
        return variableInicial;
    }

    public boolean setVariableInicial(char variableInicial) {
        if(this.variables.contains(variableInicial)) {
            this.variableInicial = variableInicial;
            return true;
        } else return false;
    }

    public Map<Character, Reglas> getReglas() {
        return reglas;
    }

    public boolean addReglaToVar(char var, String regla) {
        if(this.variables.contains(var) || regla.equals("^") && !this.reglas.get(var).getReglas().contains("^")) {
            this.reglas.get(var).addRegla(regla);
            return true;
        } else return false;
    }
    
    public boolean deleteReglaFromVar(char var, String regla) {
        if(this.variables.contains(var) || regla.equals("^") && this.reglas.get(var).getReglas().contains("^")) {
            return this.reglas.get(var).deleteRegla(regla);            
        } else return false;
    }
    
    
    public String generarCadenaAleatoria() {
        String cadena = "";
        if(this.EmptyRules()) return "ERROR: Por favor configure todas las variables con reglas";
        if(this.variables.contains(this.variableInicial)) {
            Reglas var = this.reglas.get(this.variableInicial);
            if(var.getReglas().isEmpty()) return "ERROR: Reglas iniciales vacias";
            cadena = var.getReglaAleatoria();
        } else return "ERROR: No se ha establecido la variable inicial!";
        
        while(containsUppers(cadena)) { 
            for(char simbolo : cadena.toCharArray()) { 
                if(Character.isUpperCase(simbolo)) { 
                    String simboloStr = Character.toString(simbolo);
                    cadena = cadena.replaceFirst(simboloStr, this.reglas.get(simbolo).getReglaAleatoria());    
                    break;
                } 
            }
        }
        return cadena;
    }
    
    
    private boolean containsUppers(String texto) {
        for (char c : texto.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean EmptyRules() {
        return this.reglas.values().stream().anyMatch(regla -> regla.getReglas().size() == 0);
    }
    
    
    public boolean validateRule(String rule) {
        if(rule.contains(" ")) return false;
        for(char caracter : rule.toCharArray()) {
            if(!this.variables.contains(caracter) && !this.terminales.contains(caracter)) return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String varsTxt = "No Terminales = {";
        for(char var : this.variables) {
            varsTxt += " "+var+",";
        }
        if(varsTxt.contains(",")) varsTxt = varsTxt.substring(0, varsTxt.length()-1)+" }\n";
        else varsTxt += " }\n";
        
        String term = "Terminales = {";
        for(char t : this.terminales) {
            term += " "+t+",";
        }
        if(term.contains(",")) term = term.substring(0, term.length()-1)+" }\n";
        else term += " }\n";
        
        String rulesTxt = "Reglas = {\n";
        for (Map.Entry<Character, Reglas> entry : this.reglas.entrySet()) {
            String rule ="\t"+ entry.getKey() + " ->";
            for(String regla : entry.getValue().getReglas()) {
                rule += " "+regla+" |";
            }
            if(rule.contains("|")) rule = rule.substring(0, rule.length()-1);
            else rule += " REGLAS VACÍAS";
            rulesTxt += rule+"\n";
        }
        rulesTxt += "}";
        String inicial = "V Inicial = "+ (Character.isUpperCase(variableInicial) ? this.variableInicial+"" : "Sin variable inicial") + "\n";
        return varsTxt + term + inicial + rulesTxt + "\n\n";
        
    }
}
