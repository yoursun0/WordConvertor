/**
 * 
 */
package com.csg;

import java.nio.charset.Charset;
/**
 * @author Helic
 *
 */
public class Encoding {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (String encoding1 : Charset.availableCharsets().keySet()) {
		for (String encoding2 : Charset.availableCharsets().keySet()) {
            try {
                byte[] binary = "of the agency    榷xperience昅迋迋迋迋迋迋迋迋迋迋迋迋迋迋迋芧    suppliers (Q8)".getBytes(encoding2);
                String str = new String(binary, encoding1);
                if (str.contains("══════════════"))
                System.out.printf("%s %s %s%n", 
                		encoding1, encoding2, new String(binary, encoding1));
            } catch (Exception e) {
            }
		}
		}
	}

}
