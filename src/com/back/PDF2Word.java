package com.back;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PDF2Word extends PDF2File{
	
	boolean converting;
	
	public PDF2Word(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar, JLabel _progressLog, JButton _btnConvert) {
		super(_filePathInput,_filePathOutput,_extensionOutput, _progressBar, _progressLog, _btnConvert);
	}
	
	@Override
	public void run(){
		GenerateDOCXFromPDF();
	}
	
	
	private void GenerateDOCXFromPDF() {
		try 
		{

			btnConvert.setEnabled(false);
			double percentConverted;
			
			File _filePathInput = new File(filePathInput);
			XWPFDocument doc = new XWPFDocument();
			PdfReader pdf = new PdfReader(filePathInput);
			PdfReaderContentParser parser = new PdfReaderContentParser(pdf);
			
			
			
			
			
			String filePathInputNameExt = _filePathInput.getName();
			int posDot = filePathInputNameExt.lastIndexOf(".");
			String filePathInputNameNoExt = posDot > 0 ? filePathInputNameExt.substring(0, posDot) : filePathInputNameExt;
			String filePathOutputFormated = filePathOutput + "\\" + filePathInputNameNoExt + extensionOutput;
		
			
			for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
			    TextExtractionStrategy strategy =
			      parser.processContent(i, new SimpleTextExtractionStrategy());
			    String text = strategy.getResultantText();
			    XWPFParagraph p = doc.createParagraph();
			    XWPFRun run = p.createRun();
			    run.setText(text);
			    run.addBreak(BreakType.PAGE);
			    
			    
			    percentConverted = ((i)*100)/pdf.getNumberOfPages();
				String percent = String.format("%.0f", percentConverted);
				
				progressBar.setValue(Integer.parseInt(percent));
			    progressLog.setText(String.format("Converting %d page(s) of %d", i, pdf.getNumberOfPages()));
			    
			}
			FileOutputStream out = new FileOutputStream(filePathOutputFormated);
			doc.write(out);
			

			converting = false;
		    progressLog.setText("Convert Done.");
		    
			
		}
		catch(Exception e) 
		{
			
		}
		
		btnConvert.setEnabled(true);
	}
}
