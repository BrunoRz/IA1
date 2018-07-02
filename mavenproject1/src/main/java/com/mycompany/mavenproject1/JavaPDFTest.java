package com.mycompany.mavenproject1;

import java.io.IOException;

public class JavaPDFTest {

    public static void main(String[] args) throws IOException {

        PDFManager pdfManager = new PDFManager();       
        pdfManager.setFilePath("./src/main/resources/120.pdf");
        pdfManager.ToText();
        pdfManager.findAuthors();
        pdfManager.preProcessing();
        //pdfManager.frequenciaPalavras();
    }    
}