package com.back;

import javax.swing.JProgressBar;

public class PDF2File implements Runnable {
	String filePathInput;
	String filePathOutput;
	String extensionOutput;
	JProgressBar progressBar;
	long fileSizePDF;
	long fileSizeOutput;
	
	public PDF2File(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar) {
		this.filePathInput = _filePathInput;
		this.filePathOutput = _filePathOutput;
		this.extensionOutput = _extensionOutput;
		this.progressBar = _progressBar;
	}
	
	@Override
	public void run(){
		ConvertPDF2File();
	}	
	
	public void ConvertPDF2File() {
		System.out.println("from PDF2File");
	}
}











