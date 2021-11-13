/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.cwb.sinais.core;

import br.utfpr.cwb.sinais.api.Program;
import br.utfpr.cwb.sinais.services.Services;
import br.utfpr.cwb.sinais.domain.Matriz;
import org.jblas.FloatMatrix;
import ij.process.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Eduardo Darrazão
 * @author Daniel Eduardo Vieira
 *
 */
public class Main {

    public static void main(String args[]) {

        Program program = new Program();

        Services services = new Services();

        // Exemplo 
        // var a = services.VerificadorDeMatriz;
        Matriz matriz = new Matriz();

        //
        //START
        //
        System.out.println("Iniciando...");
        FloatMatrix matrizH;
        
        //Replace nos files
        services.getFileParser().replace("Dados/M.csv", ";", ",", "Dados/M2.csv");
        services.getFileParser().replace("Dados/N.csv", ";", ",", "Dados/N2.csv");
        
        //Carregar matriz H
        System.out.println("carregando h");
        Matriz h = new Matriz("Dados/H-1.csv");
        System.out.println("h carregado, tentar duplicar");
        matrizH = h.getMatriz().dup();
        
        //Carregar vetor de sinal G
        Matriz vetor_sinal_g = new Matriz("Dados/G-1 (sinal 1).csv");
        System.out.println("G-1 carregado.");
        
        
        //cálculo do fator de redução (c)
        System.out.println("fator de redução iniciado, aguarde");
        FloatMatrix ht = matrizH.transpose();
        //comentado pq demora e n sei onde utilizar ainda
//        FloatMatrix hth = ht.mmul(matrizH);
//        float c = hth.norm2();
//        System.out.println("fator de redução (c): " + c);
        
        
        //cálculo do coeficiente de regularização (lambda)
        float lambda;
        FloatMatrix htg = ht.mmul(vetor_sinal_g.getMatriz());
        if(htg.max() > Math.abs(htg.min())) {
            lambda = (float) (htg.max() * 0.1);
        }else {
            lambda = (float) (Math.abs(htg.min()) * 0.1);
        }
        System.out.println("Coeficiente de regularização (lambda) é: " + lambda);
        
        
        //Cálculo do erro é usado na função do CGNR (?)
        
        
        //Cálculo do ganho de sinal (gama)
        //Só há um elemento sensor
        System.out.println("começando o ganho de sinal");
        for (int l = 0; l < vetor_sinal_g.getMatriz().length; l++) {
            float gama = (float) (100 + 1/20 * l * Math.sqrt(l));
            vetor_sinal_g.getMatriz().put(l, vetor_sinal_g.getMatriz().get(l)*gama);
        }
        System.out.println("ganho de sinal realizado");
        
        
        //chamar função
        //imagem é uma matriz(vetor) 3600,1
        FloatMatrix imagem = services.getCGNR().executar(matrizH, vetor_sinal_g.getMatriz());
        
        //Transformar em imagem
        ImageProcessor ip = new FloatProcessor(60, 60, imagem.toArray());
        ip.flipVertical();
        ip.rotate(90);
        ip.resetMinAndMax();
//        ip.setMinAndMax(0, 255);
        System.out.println("background: " + ip.getBackgroundValue());
        System.out.println("max: " + ip.getMax());
        System.out.println("min: " + ip.getMin());
        BufferedImage bi = ip.getBufferedImage();
        try {
            File output = new File("saved1c.png");
            ImageIO.write(bi, "png", output);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}