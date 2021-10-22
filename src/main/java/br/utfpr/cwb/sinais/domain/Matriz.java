/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.domain;

import org.jblas.FloatMatrix;

/**
 *
 * @author dvieira
 */
public class Matriz {
    public FloatMatrix matriz;
    public char separator;
    public char decimalSeparator;

    public Matriz() {
        
    }

    public Matriz(FloatMatrix matriz, char separator) {
        this.matriz = matriz;
        this.separator = separator;
    }
    
    

    public Matriz(FloatMatrix matriz, char separator, char decimalSeparator) {
        this.matriz = matriz;
        this.separator = separator;
        this.decimalSeparator = decimalSeparator;
    }   
}
