/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.cwb.desenvolvimentointegradodesistemas.matrizes_e_sinais_blas;

import org.jblas.FloatMatrix;

/**
 *
 * @author Eduardo Darrazão
 * @author Daniel Eduardo Vieira
 *
 */
public class Main {

    public static void main(String args[]) {

        System.out.println("Hello World!");

        float[][] matriz = null;
        FloatMatrix matrizM = null, matrizN = null, matrizA = null, matrizMN
                = null, matrizAM = null, matrizMA = null;
        
        
        Csv csv = new Csv(';');
        
        matriz = csv.readMatriz("Dados/M.csv");
        csv.printMatrizFloats(matriz);
        
        //Passando pra um objeto JBLAS
        matrizM = new FloatMatrix(matriz);

        //As matrizes passadas para teste (M e N) são iguais
        matrizN = new FloatMatrix(matriz);
        
        System.out.println("Fazendo 'matriz' a");
        matriz = csv.readMatriz("Dados/a.csv");
        matrizA = new FloatMatrix(matriz);

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

        matrizM.print();
        matrizN.print();

        //Para que o produto exista, o número de colunas da primeira matriz 
        //tem que ser igual ao número de linhas da segunda matriz. 
        //Além disso, o resultado da multiplicação é uma matriz que possui o
        //mesmo número de linhas da primeira matriz e o mesmo número de colunas
        //da segunda matriz.
        matrizMN = new FloatMatrix(matrizM.rows, matrizN.columns);

        //calcular M*N e salvar em MN
        matrizM.mmuli(matrizN, matrizMN);
        System.out.println("M*N:");
        matrizMN.print();

        System.out.println("Matriz A:");
        matrizA.print();

        //calcular a*M e salvar em AM
        matrizAM = new FloatMatrix(matrizA.rows, matrizM.columns);
        matrizA.mmuli(matrizM, matrizAM);
        System.out.println("a*M:");
        matrizAM.print();
        //Resultado com 6 casas decimais, exemplo usa apenas 2. Manter?

        //e por último, a multiplicação que não tem resposta no exemplo
        //calcular M*a e salvar em MA
        matrizMA = new FloatMatrix(matrizM.rows, matrizA.columns);

        //matrizM.mmuli(matrizA, matrizMA); 
        //Porém é impossível! Como já explicado a condição de existência da
        //multiplicação de matrizes.
        //Exception in thread "main" org.jblas.exceptions.SizeException:
        //Number of columns of left matrix must be equal to number of rows of
        //right matrix.
        //System.out.println("M*a:");
        //matrizMA.print();
        //Para a primeira semana é isso, provavelmente ainda é necessário
        //codificar a gravação de matrizes em arquivo CSV.
    }

}
