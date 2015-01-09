/**********************************************************************************
 * Author            : Helic Leung
 * Version           : 1.0
 * Create Date       : Aug 26, 2013
 * Last Updated Date : Aug 26, 2013
 *********************************************************************************/
package com.csg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hpsf.CustomProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

public class MSWordHelper {

	private Map<String, String> replacements = null;
	private String inputText = null;
	private String outputFilePath = null;
	private boolean isNewWordCreated = false;
	
    /**
     * Constructor for MSWordHelper
     */
	public MSWordHelper(String inputText, String outputFileName) {
		this.inputText = inputText;
		this.outputFilePath = outputFileName;
	}
	
	/**
	 * Method to process Word Document Generation
	 * @return success or fail
	 */
	public static boolean process(List<String> lines, String outPath, boolean portrait) throws Exception {
        // POI apparently can't create a document from scratch,
        // so we need an existing empty dummy document
		String sample = (portrait)?"empty2.docx":"empty.docx";
        XWPFDocument doc = new XWPFDocument(new FileInputStream(sample));
        for (String str : lines){
        		 XWPFParagraph p1 = doc.createParagraph();
        		 XWPFRun r1 = p1.createRun();

        		 if ((str.startsWith("\f"))){
        			 String nextText = str.substring(1);
        			 r1.setText(" ");
            		 p1.setPageBreak(true);
            		 XWPFParagraph p2 = doc.createParagraph();
            		 XWPFRun r2 = p2.createRun();
            		 r2.setText(nextText);
            	 }else{
            		 r1.setText(str);
            	 }
        }
        
        doc.write(new FileOutputStream(outPath));
        
        return true;
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
	}

}
