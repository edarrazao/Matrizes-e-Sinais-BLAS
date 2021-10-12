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

/**
 *
 * @author edupo
 */
public class Main {

    public static void main(String args[]) {

        System.out.println("Hello World!");

        //Trazer matriz em CSV pro projeto
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); //pra separar com ; ao invés do padrão ,
            FileReader filereader = new FileReader("Dados/M.csv"); //responsável por ler o arquivo //FileNotFoundException
            CSVReader reader = new CSVReaderBuilder(filereader).withCSVParser(parser).build(); //ler o CSV com o leitor de arquivo utilizando um parser diferente

            List<String[]> matrizStrings = reader.readAll(); //Adiciona o CSV para uma Lista de um Array de Strings //IOException

            //Ok, está lendo, porém está tudo em string.
            float[][] matriz = new float[matrizStrings.size()][];

            for (int i = 0; i < matrizStrings.size(); i++) {
                float[] linha = new float[matrizStrings.get(i).length]; //cria array de float com o tamanho de strings naquela linha
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
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
