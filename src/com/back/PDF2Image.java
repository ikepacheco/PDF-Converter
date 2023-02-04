package com.back;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JProgressBar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fit.pdfdom.PDFDomTree;

public class PDF2Image implements Runnable{

	String filePathInput;
	String filePathOutput;
	String extensionOutput;
	JProgressBar progressBar;
	long fileSizePDF;
	long fileSizeImage;
	
	public PDF2Image(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar) {
		this.filePathInput = _filePathInput;
		this.filePathOutput = _filePathOutput;
		this.extensionOutput = _extensionOutput;
		this.progressBar = _progressBar;
	}
	

	@Override
	public void run(){
		GenerateImageFromPDF();
	}
	
	
	private void GenerateImageFromPDF() {
		try {
			double percentConverted;
			if(!filePathInput.isBlank() && !filePathOutput.isBlank()) {
			
				File _filePathInput = new File(filePathInput);	
				PDDocument pdf = PDDocument.load(_filePathInput);
				PDFRenderer pdfRenderer = new PDFRenderer(pdf);
				for (int page = 0; page < pdf.getNumberOfPages(); ++page) {
					BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			        ImageIOUtil.writeImage(bim, String.format("%s\\%d%s",filePathOutput, page + 1, extensionOutput), 300);
			        
			        percentConverted = ((page+1)*100)/pdf.getNumberOfPages();
					String percent = String.format("%.0f", percentConverted);
					
					progressBar.setValue(Integer.parseInt(percent));
					
					System.out.println("Converted " + percent + "% of " + pdf.getNumberOfPages() + "pages.");
			    }
				pdf.close();
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
