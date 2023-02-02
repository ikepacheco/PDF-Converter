package com.back;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

public class PDF2HTML implements Runnable {
	
	String filePathInput;
	String filePathOutput;
	long fileSizePDF;
	long fileSizeHTML;
	
	public PDF2HTML(String _filePathInput, String _filePathOutput) {
		this.filePathInput = _filePathInput;
		this.filePathOutput = _filePathOutput;
	}
	
	@Override
	public void run(){
		GenerateHTMLFromPDF();
	}
	
	
	private void GenerateHTMLFromPDF() {
		try {
			File _filePathInput = new File(filePathInput);
			PDDocument pdf = PDDocument.load(_filePathInput);
		    Writer output = new PrintWriter(filePathOutput, "utf-8");
		    new PDFDomTree().writeText(pdf, output);
		    output.close();
		}
		catch(Exception e) 
		{
			e.getStackTrace();
		}
	}
}
