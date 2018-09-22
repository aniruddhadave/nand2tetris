package com.aniruddha.nand2tetris.Assembler;

import java.io.File;

public class Utils {

	public static final String COMMENTSTART = "//";
	
	public static String removeComments(String str) {
		int index = str.indexOf(COMMENTSTART);
		if (index != -1) {
			str = str.substring(0, index);
		}
		return str;
	}
	
	public static String deleteWhitespaces(String str) {
		str.replaceAll("\\s", "");
		return str;
	}
	
	public static boolean isAsm(File file) {
		String filename = file.getName();
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			if (filename.substring(index).toLowerCase().equals(".asm")) {
				return true;
			}
		}
		return false;
	}
	
	public static String padLeftZero(String str, int len) {
		for (int i= str.length(); i< len; i++) {
			str = "0" + str;
		}
		return str;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}