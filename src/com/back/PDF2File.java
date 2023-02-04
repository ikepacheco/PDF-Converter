package com.back;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PDF2File implements Runnable {
	String filePathInput;
	String filePathOutput;
	String extensionOutput;
	JProgressBar progressBar;
	JLabel progressLog;
	JButton btnConvert;
	long fileSizePDF;
	long fileSizeOutput;
	
	public PDF2File(String _filePathInput, String _filePathOutput, String _extensionOutput, JProgressBar _progressBar, JLabel _progressLog, JButton _btnConvert) {
		this.filePathInput = _filePathInput;
		this.filePathOutput = _filePathOutput;
		this.extensionOutput = _extensionOutput;
		this.progressBar = _progressBar;
		this.progressLog = _progressLog;
		this.btnConvert = _btnConvert;
	}
	
	@Override
	public void run(){
		ConvertPDF2File();
	}	
	
	public void ConvertPDF2File() {
		System.out.println("from PDF2File");
	}
}











