/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.cwb.desenvolvimentointegradodesistemas.matrizes_e_sinais_blas;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        //Trazer matriz em CSV pro projeto
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';')
                    .build(); //pra separar com ; ao invés do padrão ,

            //responsável por ler o arquivo //FileNotFoundException
            FileReader filereader = new FileReader("Dados/M.csv");

            //ler o CSV com o leitor de arquivo utilizando um parser diferente
            CSVReader reader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser).build();

            //Adiciona o CSV para uma Lista de um Array de Strings /IOException
            List<String[]> matrizStrings = reader.readAll();

            //Ok, está lendo, porém está tudo em string.
            matriz = new float[matrizStrings.size()][];

            for (int i = 0; i < matrizStrings.size(); i++) {
                //cria array de float com o tamanho de strings naquela linha
                float[] linha = new float[matrizStrings.get(i).length];

                for (int j = 0; j < matrizStrings.get(i).length; j++) {
                    linha[j] = Float.parseFloat(matrizStrings.get(i)[j]);
                }

                matriz[i] = linha;
            }

            //Agora que transformou num Array de Array de floats, basta printar
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print(matriz[i][j] + "  ");
                }
                System.out.println("");
            }

            //Passando pra um objeto JBLAS
            matrizM = new FloatMatrix(matriz);

            //As matrizes passadas para teste (M e N) são iguais
            matrizN = new FloatMatrix(matriz);

            // IMPORTANTE!
            // Daqui até o final do try/catch será código repetido,
            // botando só pra testar, depois criar classes/métodos certinho
            System.out.println("Fazendo 'matriz' a");
            filereader = new FileReader("Dados/a.csv");
            reader = new CSVReaderBuilder(filereader).withCSVParser(parser)
                    .build();
            matrizStrings = reader.readAll();

            matriz = new float[matrizStrings.size()][];
            for (int i = 0; i < matrizStrings.size(); i++) {
                //cria array de float com o tamanho de strings naquela linha
                float[] linha = new float[matrizStrings.get(i).length];

                for (int j = 0; j < matrizStrings.get(i).length; j++) {
                    linha[j] = Float.parseFloat(matrizStrings.get(i)[j]);
                }

                matriz[i] = linha;
            }

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print(matriz[i][j] + "  ");
                }
                System.out.println("");
            }

            matrizA = new FloatMatrix(matriz);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Matriz carregada, está em float
        //Próximo passo: usar JBLAS
        //Quatro classes:  FloatMatrix, DoubleMatrix, ComplexFloatMatrix and
        //ComplexDoubleMatrix in the package org.jblas 
        //representam real and complex matrices in single and double precision.
        //Terá que suportar Sparse Matrix(Matrizes Esparsas-grande quantidade
        //        de zeros)? O jblas não suporta.
        //Trabalharemos com valores Float ou terá que expandir até Double?
        //        Custo de tempo de processamento.
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
