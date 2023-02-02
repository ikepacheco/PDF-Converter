package com.front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.back.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Label;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;


import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;

public class MainApp extends JFrame {

	
	int xMouse, yMouse;
	
	JFileChooser chooseFileInput; 
	
	private JPanel contentPane;
	private JTextField txtFileInputLocation;
	private JTextField txtFileOutputLocation;
	

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
					PDF2HTML pdf2html = new PDF2HTML("D:\\Jason\\Docs\\asd.pdf","D:\\Jason\\Lenguajes\\Java\\PDF-Converter\\src\\output\\pdf.html");
					
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
	public MainApp() {
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
		
		JButton btnSelectFileInput = new JButton("Select file");
		btnSelectFileInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFileInput = new JFileChooser(); 
				chooseFileInput.setCurrentDirectory(new java.io.File("."));
				chooseFileInput.setDialogTitle("Select Folder");
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
		btnSelectFileInput.setBounds(351, 92, 104, 28);
		contentPane.add(btnSelectFileInput);
		
		JButton btnSelectFolderOutput = new JButton("Select folder");
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
		btnSelectFolderOutput.setBounds(351, 132, 104, 28);
		contentPane.add(btnSelectFolderOutput);
		
		JButton btnConvert = new JButton("CONVERT");
		btnConvert.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		btnConvert.setBounds(153, 186, 160, 46);
		contentPane.add(btnConvert);
		
		JComboBox boxTypeToConvert = new JComboBox();
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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setRequestFocusEnabled(false);
		progressBar.setValue(100);
		progressBar.setBounds(147, 165, 161, 12);
		bg.add(progressBar);
	}
}
