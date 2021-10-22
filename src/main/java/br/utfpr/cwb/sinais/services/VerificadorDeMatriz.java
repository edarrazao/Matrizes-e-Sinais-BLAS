/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.services;

import org.jblas.FloatMatrix;

/**
 *
 * @author dvieira
 */
public class VerificadorDeMatriz {
       
    public VerificadorDeMatriz() {
        
    }
    
    public FloatMatrix TestarELerMatriz(String matrixPath, char separador) {
        LeitorDeMatriz csv = new LeitorDeMatriz(separador);
        
        var matriz = csv.readMatriz(matrixPath);
        
        //Passando pra um objeto JBLAS
        var matrizM = new FloatMatrix(matriz);
        
        //Matriz carregada, está em float
        //Próximo passo: usar JBLAS
        //Quatro classes:  FloatMatrix, DoubleMatrix, ComplexFloatMatrix and
        //ComplexDoubleMatrix in the package org.jblas 
        //representam real and complex matrices in single and double precision.
        //matrizM = new FloatMatrix(matriz);
        System.out.println("rows: " + matrizM.rows);

        System.out.println("columns: " + matrizM.columns);

        System.out.println("maior: " + matrizM.max() + " na pos: "
                + matrizM.argmax());
        
        return matrizM;
    }
    
    public FloatMatrix LerMatriz(String matrixPath) throws Exception {
        FloatMatrix matrizM = null;
        
        //Passando pra um objeto JBLAS
        try {
            matrizM = new FloatMatrix(matrixPath);            
        } catch (Exception e) {
            System.out.println(
                    "[VerificadorDeMatriz] ERROR: Invalid path or matrix "+
                            "format.");
            throw(e);
        }
        
        //Matriz carregada, está em float
        //Próximo passo: usar JBLAS
        //Quatro classes:  FloatMatrix, DoubleMatrix, ComplexFloatMatrix and
        //ComplexDoubleMatrix in the package org.jblas 
        //representam real and complex matrices in single and double precision.
        //matrizM = new FloatMatrix(matriz);
        System.out.println("rows: " + matrizM.rows);

        System.out.println("columns: " + matrizM.columns);

        System.out.println("maior: " + matrizM.max() + " na pos: "
                + matrizM.argmax());
        
        return matrizM;
    }
    
    
    
}
