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
    public FloatMatrix Matriz;
    public char Separator;
    public char DecimalSeparator;

    public Matriz() { }
    
    public Matriz(FloatMatrix matriz, char separator) {
        this.Matriz = matriz;
        this.Separator = separator;
    }

    public Matriz(FloatMatrix matriz, char separator, char decimalSeparator) {
        this.Matriz = matriz;
        this.Separator = separator;
        this.DecimalSeparator = decimalSeparator;
    }   
}
