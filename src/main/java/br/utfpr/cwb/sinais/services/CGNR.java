/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.services;

import java.util.ArrayList;
import org.jblas.FloatMatrix;

/**
 *
 * @author edupo
 */
public class CGNR {

    //Construtor
    public CGNR() {

    }

    //Codificar um protótipo com o Algoritmo CGNR. Validar os resultados com os dados experimentais.
    //Medir o tempo total de execução e o consumo de recursos como memória e ocupação de CPU.
    public FloatMatrix executar(FloatMatrix matriz_modelo_h, FloatMatrix vetor_sinal_g) {
        /**
        Entrada: H e g
        Saída: f
        g - Vetor de sinal
        H - Matriz de modelo
        f - Imagem
        S - Número de amostras do sinal
        N - Número de elementos sensores
        Cálculo do fator de redução (c)
        Cálculo do coeficiente de regularização (λ)
        Cálculo do erro (ϵ)
        Cálculo do ganho de sinal (γ)
        * Calculo de vetores norma 2 euclidiana
        * Matriz transposta (elevado a T)
        **/
        
        //ENTRA: Matriz H-1 e G-1 (ou G-2 ou a-1)
        //No comparativo com o pseudo-código, entende-se por matrizes 0 sendo a 0 mesmo e a i
        //e matrizes b pelas i+1
        
        FloatMatrix f0 = FloatMatrix.zeros(3600,1); //imagem 60x60, como definido no trabalho
        //na verdade é um vetor 3600, ai pra construir a imagem 60z60 separa uma coluna a cada 60 elementos!!
        FloatMatrix r0, z0, p0;
        System.out.println("dados de h: " + matriz_modelo_h.rows + ", " + matriz_modelo_h.columns + ", " + matriz_modelo_h.length);
        System.out.println("dados de G: " + vetor_sinal_g.rows + ", " + vetor_sinal_g.columns + ", " + vetor_sinal_g.length);
     
        FloatMatrix hf0 = matriz_modelo_h.mmul(f0);
//        System.out.println("dados de HF0: " + hf0.rows + ", " + hf0.columns + ", " + hf0.length);
        r0 = vetor_sinal_g.sub(hf0);
        System.out.println("dados de R: " + r0.rows + ", " + r0.columns + ", " + r0.length);
        
        FloatMatrix ht = matriz_modelo_h.transpose();
        z0 = ht.mmul(r0);
        System.out.println("dados de Z: " + z0.rows + ", " + z0.columns + ", " + z0.length);
        
        p0 = z0.dup();
        
        FloatMatrix w, fb, rb, zb, pb;
        ArrayList<Float> alpha = new ArrayList();
        ArrayList<Float> beta = new ArrayList();
        float erro = (float) 1.0;
        
        for (int i = 0; erro >= 0.0001; i++) {
            //wi
            w = matriz_modelo_h.mmul(p0);
//            System.out.println("dados de W: " + w.rows + ", " + w.columns + ", " + w.length);
            
            //ai = norma2 ao quadrado de zi / norma2 ao quadrado de wi
            float normaz = z0.norm2() * z0.norm2();
            float normaw = w.norm2() * w.norm2();
            alpha.add(i, normaz/normaw);
//            System.out.println("valor de alpha é: " + alpha.get(i));
            
            //fi+1 -> fb
            FloatMatrix aipi = p0.mmul(alpha.get(i));
            fb = f0.add(aipi);
            
            //ri+1 -> rb
            FloatMatrix aiwi = w.mmul(alpha.get(i));
            rb = r0.sub(aiwi);
            
            //zi+1 -> zb
            zb = ht.mmul(rb);
            
            //B1 = norma2 ao quadrado de zb / norma2 ao quadrado de z0
            float normazb = zb.norm2() * zb.norm2();
            float normaz0 = z0.norm2() * z0.norm2();
            beta.add(i, normazb/normaz0);
//            System.out.println("valor de beta é: " + beta.get(i));
            
            //pi+1 -> pb
            FloatMatrix bipi = p0.mmul(beta.get(i));
            pb = zb.add(bipi);
            
            //cálculo do erro
            erro = rb.norm2() - r0.norm2();
            System.out.println("Erro está em: " + erro);
            
            //coloca os auxiliares B como 0 (o i+1 vira i)
            f0 = fb;
            r0 = rb;
            z0 = zb;
            p0 = pb;
//            System.out.println("//");
            
            
        }
        
        return f0;
        
    }

}
