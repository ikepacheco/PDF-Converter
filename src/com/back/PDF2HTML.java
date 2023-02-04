package com.back;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

public class PDF2HTML extends PDF2File{
	
	boolean converting = false;
	
	public PDF2HTML(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar, JLabel _progressLog, JButton _btnConvert) {
		super(_filePathInput,_filePathOutput,_extensionOutput, _progressBar, _progressLog, _btnConvert);
	}
	
	@Override
	public void run(){
		GenerateHTMLFromPDF();
	}
	
	
	private void GenerateHTMLFromPDF() {
		try {
			if(!filePathInput.isBlank() && !filePathOutput.isBlank()) {

				btnConvert.setEnabled(false);
				progressBar.setIndeterminate(true);

			    progressLog.setText("Reading File...");
				File _filePathInput = new File(filePathInput);
				String filePathInputNameExt = _filePathInput.getName();
				int posDot = filePathInputNameExt.lastIndexOf(".");
				String filePathInputNameNoExt = posDot > 0 ? filePathInputNameExt.substring(0, posDot) : filePathInputNameExt;
				PDDocument pdf = PDDocument.load(_filePathInput);
				String filePathOutputFormated = filePathOutput + "\\" + filePathInputNameNoExt + extensionOutput;

			    
			    
			    
			    converting = true;
			    
			    new Thread(new Runnable(){
					public void run() {
						try {
							int i = 0;
							while(converting == true) {
								if(i == 0)
								progressLog.setText("Converting to HTML...");
								if(i == 1)
								progressLog.setText("Converting to HTML");
								if(i == 2)
								progressLog.setText("Converting to HTML.");
								if(i == 3)
								{
									progressLog.setText("Converting to HTML..");
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
			    
			    
			    
			    
			    
			    
			    Writer output = new PrintWriter(filePathOutputFormated, "utf-8");
			    PDFDomTree pdfDomTree = new PDFDomTree();
			    pdfDomTree.writeText(pdf, output);
			    
			    converting = false;
			    progressLog.setText("Convert Done.");
			    
			    pdf.close();
			    output.close();
			    
			}
		}
		catch(Exception e) 
		{
			e.getStackTrace();
		}
		progressBar.setIndeterminate(false);
		progressBar.setValue(100);
		btnConvert.setEnabled(true);
	}
}
