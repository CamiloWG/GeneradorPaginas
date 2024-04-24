/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cca.generadorpaginas;

import Generator.Generador;
import Reader.Tokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;

/**
 *
 * @author Camilo & Paula
 */
public class GeneradorPaginas {

    public static void main(String[] args) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
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
            Tokenizer tokens = new Tokenizer(content.toString().toLowerCase());
            tokens.makeElements();
            Generador gen = new Generador(tokens.elementos);
            System.out.println(gen.generatePage());
            
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        
    }
}
