package com.back;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JProgressBar;	

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

public class PDF2HTML extends PDF2File{
	
	public PDF2HTML(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar) {
		super(_filePathInput,_filePathOutput,_extensionOutput, _progressBar);
	}
	
	@Override
	public void run(){
		GenerateHTMLFromPDF();
	}
	
	
	private void GenerateHTMLFromPDF() {
		try {
			if(!filePathInput.isBlank() && !filePathOutput.isBlank()) {
			
				File _filePathInput = new File(filePathInput);
				String filePathInputNameExt = _filePathInput.getName();
				int posDot = filePathInputNameExt.lastIndexOf(".");
				String filePathInputNameNoExt = posDot > 0 ? filePathInputNameExt.substring(0, posDot) : filePathInputNameExt;
				System.out.println(filePathOutput + "\\" + filePathInputNameNoExt + extensionOutput);
				progressBar.setIndeterminate(true);
				PDDocument pdf = PDDocument.load(_filePathInput);
				String filePathOutputFormated = filePathOutput + "\\" + filePathInputNameNoExt + extensionOutput;
			    Writer output = new PrintWriter(filePathOutputFormated, "utf-8");
			    PDFDomTree pdfDomTree = new PDFDomTree();
			    pdfDomTree.writeText(pdf, output);
			    output.close();
			}
		}
		catch(Exception e) 
		{
			e.getStackTrace();
		}
		progressBar.setIndeterminate(false);
		progressBar.setValue(100);
	}
}
