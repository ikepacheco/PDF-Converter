package com.front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.back.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;


import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import java.awt.Insets;

public class MainApp extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int xMouse, yMouse;
	
	JFileChooser chooseFileInput; 
	
	private JPanel contentPane;
	private JTextField txtFileInputLocation;
	private JTextField txtFileOutputLocation;
	@SuppressWarnings("rawtypes")
	JComboBox boxTypeToConvert;
	JProgressBar progressBar;
	JLabel lblConvertingLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
		    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            javax.swing.UIManager.setLookAndFeel(new FlatMacDarkLaf());
		            break;
		        }
		    }
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex) 
		{
		    java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainApp() {
		setTitle("PDF2FILE");
		setLocationByPlatform(true);
		setFocusTraversalKeysEnabled(false);
		setFocusCycleRoot(false);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 257);
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xWithScreen = (screenSize.width - getWidth()) / 2;
		int yWithScreen = (screenSize.height - getHeight()) / 2;
		
		setLocation(xWithScreen,yWithScreen);
		
		
		
		
		
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xMouseScreen = e.getXOnScreen();
				int yMouseScreen = e.getYOnScreen();
				setLocation(xMouseScreen - xMouse, yMouseScreen - yMouse);
			}
		});
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelectFileInput = new JButton("SELECT FILE");
		btnSelectFileInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFileInput = new JFileChooser(); 
				chooseFileInput.setCurrentDirectory(new java.io.File("."));
				chooseFileInput.setDialogTitle("Select Folder");
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("PDF", "pdf");
				
				chooseFileInput.setFileFilter(imageFilter);
			    //
			    // disable the "All files" option.
			    //
				chooseFileInput.setAcceptAllFileFilterUsed(false);
			    //    
				int result = chooseFileInput.showOpenDialog(MainApp.this);
			    if (result == JFileChooser.APPROVE_OPTION ||
			    	result == JFileChooser.CANCEL_OPTION) 
			    { 
			    	if (result == JFileChooser.APPROVE_OPTION) {
				    	System.out.println("getSelectedFile() : " +  chooseFileInput.getSelectedFile());
			      		txtFileInputLocation.setText(chooseFileInput.getSelectedFile().toString());
			    	}
			    }
			    else 
			    {
			    	JOptionPane.showMessageDialog(MainApp.this, "Select folder please","No folder selected",1);
			    }
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(223, 68, 90, 14);
		contentPane.add(separator);
		btnSelectFileInput.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		btnSelectFileInput.setBounds(351, 92, 112, 28);
		contentPane.add(btnSelectFileInput);
		
		JButton btnSelectFolderOutput = new JButton("SELECT FOLDER");
		btnSelectFolderOutput.setMargin(new Insets(0, 0, 0, 0));
		btnSelectFolderOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFileInput = new JFileChooser(); 
				chooseFileInput.setCurrentDirectory(new java.io.File("."));
				chooseFileInput.setDialogTitle("Select Folder");
				chooseFileInput.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
				chooseFileInput.setAcceptAllFileFilterUsed(false);
			    //    
				int result = chooseFileInput.showOpenDialog(MainApp.this);
			    if (result == JFileChooser.APPROVE_OPTION ||
			    	result == JFileChooser.CANCEL_OPTION) 
			    { 
			    	if (result == JFileChooser.APPROVE_OPTION) {
				    	System.out.println("getSelectedFile() : " +  chooseFileInput.getSelectedFile());
			      		txtFileOutputLocation.setText(chooseFileInput.getSelectedFile().toString());
			    	}
			    }
			    else 
			    {
			    	JOptionPane.showMessageDialog(MainApp.this, "Select folder please","No folder selected",1);
			    }
			}
		});
		btnSelectFolderOutput.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		btnSelectFolderOutput.setBounds(351, 132, 112, 28);
		contentPane.add(btnSelectFolderOutput);
		
		
		JButton btnConvert = new JButton("CONVERT");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try 
						{
							if(txtFileInputLocation.getText().isBlank())
								JOptionPane.showMessageDialog(null,"Please select file to convert.","Error File not Selected!",JOptionPane.INFORMATION_MESSAGE);
							if(txtFileOutputLocation.getText().isBlank())
								JOptionPane.showMessageDialog(null,"Please select folder to output file converted.","Error Folder not Selected!",JOptionPane.INFORMATION_MESSAGE);
								
								
							if(!txtFileInputLocation.getText().isBlank() && !txtFileOutputLocation.getText().isBlank()) {
								
								PDF2Word pdf2word;
								PDF2HTML pdf2html;
								PDF2Image pdf2image;
								PDF2TXT pdf2txt;
								
								String extensionFile = "." + boxTypeToConvert.getSelectedItem().toString().toLowerCase();
								
								Object typeConvertionFile = new Object();
	
								
								
								switch(extensionFile) {
									case ".docx":
										pdf2word = new PDF2Word(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2word;
										break;
									case ".jpeg":
										pdf2image = new PDF2Image(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2image;
										break;
									case ".jpg":
										pdf2image = new PDF2Image(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2image;
										break;
									case ".gif":
										pdf2image = new PDF2Image(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2image;
										break;
									case ".html":
										pdf2html = new PDF2HTML(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2html;
										break;
									case ".png":
										pdf2image = new PDF2Image(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2image;
										break;
									case ".txt":
										pdf2txt = new PDF2TXT(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2txt;
										break;
									case ".tiff":
										pdf2image = new PDF2Image(txtFileInputLocation.getText(),txtFileOutputLocation.getText(),extensionFile, progressBar, lblConvertingLog, btnConvert);
										typeConvertionFile = pdf2image;
										break;
									default:
										pdf2html = null;
										typeConvertionFile = pdf2html;
										break;
								}
								
								
								
								Thread t1 = new Thread((Runnable)typeConvertionFile);
								t1.start();
							} 
						}
						catch (Exception e) 
						{
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
					
				});
				
				
			}
		});
		btnConvert.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		btnConvert.setBounds(153, 186, 160, 46);
		contentPane.add(btnConvert);
		
		
		
		progressBar = new JProgressBar();
		progressBar.setRequestFocusEnabled(false);
		progressBar.setBorder(new CompoundBorder());
		progressBar.setBounds(155, 182, 156, 16);
		contentPane.add(progressBar);
		
		
		
		boxTypeToConvert = new JComboBox();
		boxTypeToConvert.setBackground(new Color(35, 35, 35));
		boxTypeToConvert.setBorder(new CompoundBorder());
		boxTypeToConvert.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		boxTypeToConvert.setModel(new DefaultComboBoxModel(new String[] {"DOCX", "JPEG", "JPG", "GIF", "HTML", "PNG", "TXT", "TIFF"}));
		boxTypeToConvert.setBounds(219, 33, 124, 38);
		contentPane.add(boxTypeToConvert);
		
		txtFileInputLocation = new JTextField();
		txtFileInputLocation.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		txtFileInputLocation.setBounds(110, 92, 233, 28);
		contentPane.add(txtFileInputLocation);
		txtFileInputLocation.setColumns(10);
		
		txtFileOutputLocation = new JTextField();
		txtFileOutputLocation.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		txtFileOutputLocation.setColumns(10);
		txtFileOutputLocation.setBounds(110, 132, 233, 28);
		contentPane.add(txtFileOutputLocation);
		
		JPanel bg = new JPanel();
		bg.setBackground(new Color(35, 35, 35));
		bg.setBounds(6, 6, 469, 245);
		contentPane.add(bg);
		bg.setLayout(null);
		
		JPanel btnExit = new JPanel();
		btnExit.setBackground(new Color(35, 35, 35));
		btnExit.setBounds(0, 0, 26, 26);
		bg.add(btnExit);
		btnExit.setLayout(null);
		
		JLabel X = DefaultComponentFactory.getInstance().createLabel("X");
		X.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(new Color(224,62,82));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(new Color(35,35,35));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		X.setHorizontalTextPosition(SwingConstants.CENTER);
		X.setHorizontalAlignment(SwingConstants.CENTER);
		X.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		X.setBounds(0, 0, 26, 26);
		btnExit.add(X);
		
		JLabel File = DefaultComponentFactory.getInstance().createLabel("FILE");
		File.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		File.setHorizontalTextPosition(SwingConstants.RIGHT);
		File.setHorizontalAlignment(SwingConstants.RIGHT);
		File.setBounds(6, 87, 96, 26);
		bg.add(File);
		
		JLabel Output = DefaultComponentFactory.getInstance().createLabel("OUTPUT");
		Output.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		Output.setHorizontalTextPosition(SwingConstants.RIGHT);
		Output.setHorizontalAlignment(SwingConstants.RIGHT);
		Output.setBounds(6, 127, 96, 26);
		bg.add(Output);
		
		JLabel PDFTO = DefaultComponentFactory.getInstance().createLabel("PDF 2");
		PDFTO.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 45));
		PDFTO.setHorizontalAlignment(SwingConstants.RIGHT);
		PDFTO.setHorizontalTextPosition(SwingConstants.RIGHT);
		PDFTO.setBounds(77, 26, 135, 48);
		bg.add(PDFTO);
		
		lblConvertingLog = DefaultComponentFactory.getInstance().createLabel("--");
		lblConvertingLog.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		lblConvertingLog.setBounds(149, 157, 199, 16);
		bg.add(lblConvertingLog);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("@IkePacheco");
		lblNewJgoodiesLabel.setForeground(new Color(98, 98, 98));
		lblNewJgoodiesLabel.setBounds(6, 223, 102, 16);
		bg.add(lblNewJgoodiesLabel);
	}
}
