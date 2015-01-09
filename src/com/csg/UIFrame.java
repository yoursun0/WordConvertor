/**
 * 
 */
package com.csg;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
class PRNFileFilter extends FileFilter{
    public boolean accept(File file){
        if(file.isDirectory()) return true;
        String fname = file.getName();
        return fname.toUpperCase().endsWith("PRN");
    }
    public String getDescription(){
    	return "PRN file";
    }
}

public class UIFrame extends JFrame {
	
	private static String url1 = "W:\\temp\\*.PRN";
	private static final String url2 = "W:\\temp\\output.docx";
	 
    public UIFrame(final String[] args) throws IOException {
    	
    	byte[] buffer = new byte[1024]; 
    	FileInputStream fileInputStream = 
                new FileInputStream(new File("path.txt"));
    	int length = -1;
    	
        while((length = fileInputStream.read(buffer)) != -1) { 
            url1 = new String(buffer);
        }
        
        
    	
    	final TextField Input1 = new TextField (url1, 45);
    	final TextField Input2 = new TextField (url2, 45);
    	Label label1;
    	Label label2;
    	TextField Echo;
    	JButton openButton = new JButton("Open");
    	LayoutManager Layout;

    	JFileChooser chooser = new JFileChooser();

    	// Create a file chooser that opens up as an Open dialog
        openButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
        	String filePath = url1.
     	    	     substring(0,url1.lastIndexOf(File.separator));
            JFileChooser chooser = new JFileChooser(filePath+"\\");
            FileFilter filter1 = new PRNFileFilter();
            chooser.setFileFilter(filter1);
            chooser.setMultiSelectionEnabled(true);
            int option = chooser.showOpenDialog(UIFrame.this);
            if (option == JFileChooser.APPROVE_OPTION) {
              File[] sf = chooser.getSelectedFiles();
              String inputPath = sf[0].getPath();
              String outputPath = inputPath.replace(".PRN", ".docx");
              outputPath = outputPath.replace(".prn", ".docx");
              if (sf.length > 0) {
            	  Input1.setText(inputPath);
            	  Input1.setBackground (Color.yellow);
            	  Input2.setText(outputPath);
            	  Input2.setBackground (Color.yellow);
              }
            }
          }
        });
        
        Echo = new TextField ("Input the source and destination file path:", 45);
        label1 = new Label("Input File Path:");
    	label2 = new Label("Output File Path:");
    	Checkbox portrait = new Checkbox ("Portrait");
        JButton btn = new JButton("Convert"); 
        Layout = new FlowLayout ();

        /* Decoration */
        setBackground (Color.yellow);
        Input1.setBackground (Color.white);
        Input2.setBackground (Color.white);
        Echo.setForeground (Color.blue);
        
        /* Location */
        setLayout (Layout);
        add (Echo);
        add (Input1);
        add (Input2);
        add (portrait);
        add (openButton);
        add (btn);
        
        /* Configuration */
        Echo.setEditable (false);
        
        btn.addActionListener(new WordConvertor(Input1,Input2,portrait)); 
        
        getContentPane().add(btn, BorderLayout.NORTH); 

        setDefaultCloseOperation(
                   WindowConstants.EXIT_ON_CLOSE); 
        setSize(420, 200);
        
        setVisible(true);
    }

}