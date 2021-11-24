/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.anjos.services;

/**
 *
 * @author dvieira
 * @author edupo
 */

public class Services {
    private CGNR CGNR;
    private FileParser fileParser;

    public Services() {
        this.CGNR = new CGNR();
        this.fileParser = new FileParser();
    }

    //Getters and Setters
    public CGNR getCGNR() {
        return CGNR;
    }

    public void setCGNR(CGNR CGNR) {
        this.CGNR = CGNR;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    
    
}
