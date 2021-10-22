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
    public LeitorDeMatriz leitorDeMatriz;
    public VerificadorDeMatriz VerificadorDeMatriz;
    public CGNR cgnr;

    public Services() {
        this.leitorDeMatriz = new LeitorDeMatriz();
        this.VerificadorDeMatriz = new VerificadorDeMatriz();
        this.cgnr = new CGNR();
    }
}
