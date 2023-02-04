package com.back;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF2TXT extends PDF2File{
	
	boolean converting = false;
	
	
	public PDF2TXT(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar, JLabel _progressLog, JButton _btnConvert) {
		super(_filePathInput,_filePathOutput,_extensionOutput, _progressBar, _progressLog, _btnConvert);
	}
	
	@Override
	public void run(){
		GenerateTXTFromPDF();
	}
	
	
	private void GenerateTXTFromPDF() {
		try 
		{

			btnConvert.setEnabled(false);

		    progressLog.setText("Reading File...");
			progressBar.setIndeterminate(true);
			
			
			
			File _filePathInput = new File(filePathInput);
			String parsedText;
			PDFParser parser = new PDFParser(new RandomAccessFile(_filePathInput, "r"));
			parser.parse();
			

			converting = true;
		    
		    new Thread(new Runnable(){
				public void run() {
					try {
						int i = 0;
						while(converting == true) {
							if(i == 0)
							progressLog.setText("Parsing text...");
							if(i == 1)
							progressLog.setText("Parsing text");
							if(i == 2)
							progressLog.setText("Parsing text.");
							if(i == 3)
							{
								progressLog.setText("Parsing text..");
								i = -1;
							}
							i++;
							try {
					            Thread.sleep(500);
					          } catch (InterruptedException e) {
					            
					          }
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,e.getMessage(),e.getCause().toString(),JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}).start();	
		    
		    
			COSDocument cosDoc = parser.getDocument();
			PDFTextStripper pdfTXTStripper = new PDFTextStripper();
			PDDocument pdf = new PDDocument(cosDoc);
			parsedText = pdfTXTStripper.getText(pdf);
			
			String filePathInputNameExt = _filePathInput.getName();
			int posDot = filePathInputNameExt.lastIndexOf(".");
			String filePathInputNameNoExt = posDot > 0 ? filePathInputNameExt.substring(0, posDot) : filePathInputNameExt;
			String filePathOutputFormated = filePathOutput + "\\" + filePathInputNameNoExt + extensionOutput;

			converting = false;
			
			PrintWriter pw = new PrintWriter(filePathOutputFormated);
			pw.print(parsedText);

		    progressLog.setText("Convert Done.");
		    
			cosDoc.close();
			pw.close();
		}
		catch(Exception e) 
		{
			
		}
		

		progressBar.setIndeterminate(false);
		progressBar.setValue(100);
		btnConvert.setEnabled(true);
	}
}
