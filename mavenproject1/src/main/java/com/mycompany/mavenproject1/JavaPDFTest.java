package com.mycompany.mavenproject1;

import java.io.IOException;

public class JavaPDFTest {

    public static void main(String[] args) throws IOException {

       PDFManager pdfManager = new PDFManager();       
       pdfManager.setFilePath("./src/main/resources/13.pdf");
       System.out.println(pdfManager.ToText());       
       pdfManager.net();
       pdfManager.retornaReferencias();
    }    
}