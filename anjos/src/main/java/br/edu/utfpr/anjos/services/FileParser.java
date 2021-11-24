/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.anjos.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dvieira
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class FileParser {

    //Construtor
    public FileParser() {

    }

    public void replace(String file, String toBeReplaced, String toReplace, String new_file) {
        try {
            Path path = Paths.get(file);
            Path path2 = Paths.get(new_file);
            Charset charset = StandardCharsets.UTF_8;
            
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(toBeReplaced, toReplace);
            Files.write(path2, content.getBytes(charset));
        } catch (IOException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
