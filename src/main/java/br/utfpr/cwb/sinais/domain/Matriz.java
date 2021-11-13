/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.domain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jblas.FloatMatrix;

/**
 *
 * @author dvieira
 */
public final class Matriz {
    private FloatMatrix matriz;
    private char separator;
    private char decimalSeparator;
    private String path;

    public Matriz() { }
    
    public Matriz(FloatMatrix matriz, char separator) {
        this.setMatriz(matriz);
        this.setSeparator(separator);
    }

    public Matriz(FloatMatrix matriz, char separator, char decimalSeparator) {
        this.setMatriz(matriz);
        this.setSeparator(separator);
        this.setDecimalSeparator(decimalSeparator);
    }
    
    public Matriz(String path) {
        this.setPath(path);
        
        try {
            this.setMatriz(FloatMatrix.loadCSVFile(path));
        } catch (IOException ex) {
            Logger.getLogger(Matriz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters and Setters
    public FloatMatrix getMatriz() {
        return matriz;
    }

    public void setMatriz(FloatMatrix matriz) {
        this.matriz = matriz;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public char getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(char decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
}
