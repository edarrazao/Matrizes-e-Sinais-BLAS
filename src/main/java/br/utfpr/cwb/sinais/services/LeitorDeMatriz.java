/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.services;

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

/**
 *
 * @author edupo
 */
class LeitorDeMatriz {

    public CSVParser parser;

    public LeitorDeMatriz() {
    }

    //Construtor
    public LeitorDeMatriz(char separador) {
        parser = new CSVParserBuilder().withSeparator(separador)
                .build();

    }

    //Printa uma matriz de floats
    public void printMatrizFloats(float[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    //Le um arquivo CSV e coloca numa matriz de floats
    public float[][] readMatriz(String file) {
        float[][] matriz = null;
        try {
            //responsável por ler o arquivo /FileNotFoundException
            FileReader filereader = new FileReader(file);

            //ler o CSV com o leitor de arquivo utilizando um parser diferente
            CSVReader reader = new CSVReaderBuilder(filereader)
                    .withCSVParser(this.parser).build();

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

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return matriz;
    }

}
