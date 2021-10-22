/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.utfpr.cwb.sinais.services;

/**
 *
 * @author dvieira
 */
public class Services {
    public LeitorDeMatriz LeitorDeMatriz;
    public VerificadorDeMatriz VerificadorDeMatriz;
    public CGNR CGNR;

    public Services() {
        this.LeitorDeMatriz = new LeitorDeMatriz();
        this.VerificadorDeMatriz = new VerificadorDeMatriz();
        this.CGNR = new CGNR();
    }
}
