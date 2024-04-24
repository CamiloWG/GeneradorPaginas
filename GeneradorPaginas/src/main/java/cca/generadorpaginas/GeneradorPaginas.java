/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cca.generadorpaginas;

import Generator.Generador;
import Generator.Grammar;
import Reader.Tokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Camilo & Paula
 */
public class GeneradorPaginas {
    private static JFileChooser fileChooser;
    
    public static void main(String[] args) throws Exception {
        
        fileChooser = new JFileChooser();
        
        String grammarFile = readFile("Seleccione su gramatica:");
        String content = readFile("Seleccione su archivo de texto de entrada:");
        
        if(content.length() > 0 && grammarFile.length() > 0) {
            Tokenizer tokens = new Tokenizer(content);
            tokens.makeElements();
            Grammar gram = new Grammar(grammarFile);
            Generador gen = new Generador(tokens.elementos, gram);
            Path ruta = Paths.get(fileChooser.getSelectedFile().getPath()).resolveSibling("index.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta.toString()));
            writer.write(gen.generatePage());
            writer.close();
            System.out.println("PÁGINA GENERADA CORRECTAMENTE!");
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        
    }
    
    
    public static String readFile(String message) {
        JOptionPane.showMessageDialog(null, message, "Alerta", 1);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return content.toString();
        }
        return "";
    }
}
