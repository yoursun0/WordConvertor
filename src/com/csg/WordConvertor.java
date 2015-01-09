/**
 * 
 */
package com.csg;

import java.util.List;
import java.util.ArrayList;
import java.awt.Checkbox;
import java.awt.Desktop;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Helic
 *
 */
public class WordConvertor implements ActionListener {

	  private TextField in;
	  private TextField out;
	  private Checkbox portrait; 

	  public WordConvertor(TextField in, TextField out, Checkbox portrait) {
	    this.in = in;
	    this.out = out;
	    this.portrait = portrait;
	  }
	
	public void actionPerformed(ActionEvent ae) {

		BufferedReader br = null;
		try {
			// for (String encoding2 : Charset.availableCharsets().keySet()) {
			String encoding1 = "IBM869";
			String encoding2 = "windows-1258";
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(in.getText()),encoding2));

			List<String> lines = new ArrayList<String>();
			String line = br.readLine();

	        while (line != null) {
	        	StringBuilder sb = new StringBuilder();
	            sb.append(line);
	            line = br.readLine();
	            String oneLine = sb.toString();
	            byte[] binary = oneLine.getBytes(encoding2);
	            String str = new String(binary, encoding1);
	            lines.add(str);
	            System.out.println(str);
	        }
	        
	        FileOutputStream os = new FileOutputStream("path.txt");
	        os.write(in.getText().toString().getBytes(Charset.forName("UTF-8")));
	        
	        MSWordHelper.process(lines,this.out.getText(),this.portrait.getState());
	        Desktop desktop = null;
	        if (Desktop.isDesktopSupported()) 
	        {
	          desktop = Desktop.getDesktop();
	          desktop.open(new File(this.out.getText())); 
	        }
	        System.exit(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	}

    public static void main(String[] args) throws IOException {
        new UIFrame(args);
    }
}
