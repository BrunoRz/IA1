package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFManager {

    private PDFParser parser;
    private PDFTextStripper pdfStripper;    
    private PDDocument pdDoc ;
    private COSDocument cosDoc ;

    private String text;
    private String filePath;
    private File file;

    public PDFManager() {

    }

    public String ToText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);

        parser = new PDFParser(new RandomAccessFile(file,"r"));
        parser.parse();

        cosDoc = parser.getDocument();
        pdDoc = new PDDocument(cosDoc);

        pdfStripper = new PDFTextStripper();        
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        text = pdfStripper.getText(pdDoc);
        return text;
    }

    
    
    private final Pattern p = Pattern.compile("[a-zA-Z]+");
    
    public void frequenciapalavras(){
        text.replaceAll("[.,]","");
        
        Map<String, Integer> mapaFreq = new HashMap<>();
        
        for (String palavra : text.split("\\s+")) {
            if (!mapaFreq.containsKey(palavra)) {
                mapaFreq.put(palavra, 1);
            } else {
                mapaFreq.put(palavra, 1 + mapaFreq.get(palavra));
            }
        }
        
        String[] palavrasMaisFrequentes = new String[3];
        int[] freqPalavras = new int[3];
        for (Map.Entry<String, Integer> entrada : mapaFreq.entrySet()) {
            if (entrada.getValue() > freqPalavras[0]) {
                freqPalavras[0] = entrada.getValue();
                palavrasMaisFrequentes[0] = entrada.getKey();
            } else {
                if (entrada.getValue() > freqPalavras[1]) {
                    freqPalavras[1] = entrada.getValue();
                    palavrasMaisFrequentes[1] = entrada.getKey();
                } else if (entrada.getValue() > freqPalavras[2]) {
                    freqPalavras[2] = entrada.getValue();
                    palavrasMaisFrequentes[2] = entrada.getKey();
                }
            }
        }

        for (int i = 0; i < freqPalavras.length; i++) {
            System.out.println(i + 1 + " palavra: " + palavrasMaisFrequentes[i]
                        + " \nFrequência: " + freqPalavras[i]
                        + "\n------------------------\n");
        }
    }
    
    public void retornaReferencias(){
        int lastIndex = text.lastIndexOf("REFERENCES");
        String referencias = text.substring(lastIndex);
        System.out.println(referencias);
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}