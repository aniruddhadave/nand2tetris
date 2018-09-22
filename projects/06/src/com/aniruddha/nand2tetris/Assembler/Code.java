package com.aniruddha.nand2tetris.Assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code {

	public static List<String> destinationCodes = new ArrayList<String>();
	public static Map<String, String> compCodes = new HashMap<String, String>();
	public static List<String> jumpCodes = new ArrayList<String>();
	
	// Pre-defined Symbols
	static {
		destinationCodes.add("");
		destinationCodes.add("M");
		destinationCodes.add("D");
		destinationCodes.add("MD");
		destinationCodes.add("A");
		destinationCodes.add("AM");
		destinationCodes.add("AD");
		destinationCodes.add("AMD");
		
		jumpCodes.add("");
		jumpCodes.add("JGT");
		jumpCodes.add("JEQ");
		jumpCodes.add("JGE");
		jumpCodes.add("JLT");
		jumpCodes.add("JNE");
		jumpCodes.add("JLE");
		jumpCodes.add("JMP");
		
		compCodes.put("0", "0101010");
		compCodes.put("1", "0111111");
		compCodes.put("-1", "0111010");
		compCodes.put("D", "0001100");
		compCodes.put("A", "0110000");
		compCodes.put("!D", "0001101");
		compCodes.put("!A", "0110001");
		compCodes.put("-D", "0001111");
		compCodes.put("-A", "0110011");
		compCodes.put("D+1", "0011111");
		compCodes.put("A+1", "0110111");
		compCodes.put("D-1", "0001110");
		compCodes.put("A-1", "0110010");
		compCodes.put("D+A", "0000010");
		compCodes.put("D-A", "0010011");
		compCodes.put("A-D", "0000111");
		compCodes.put("D&A", "0000000");
		compCodes.put("D|A", "0010101");
		
		compCodes.put("", "xxxxxxx");
		
		compCodes.put("M", "1110000");
		compCodes.put("!M", "1110001");
		compCodes.put("-M", "1110011");
		compCodes.put("M+1", "1110111");
		compCodes.put("M-1", "1110010");
		compCodes.put("D+M", "1000010");
		compCodes.put("D-M", "1010011");
		compCodes.put("D&M", "1000000");
		compCodes.put("D|M", "1010101");
		compCodes.put("M-D", "1000111");
		
	}
	
	public Code() {
		// TODO Auto-generated constructor stub
	}
	
	public static String dest(String mnemoic) {
		return Integer.toBinaryString(destinationCodes.indexOf(mnemoic));
	}
	
	public static String comp(String mnemonic) {
		return compCodes.get(mnemonic);
	}
	
	public static String jump(String mnemonic) {
		return Integer.toBinaryString(jumpCodes.indexOf(mnemonic));
	}
	
	public static String generateACode(Integer address) {
		return "0" + String.format("%15s", Integer.toBinaryString(address)).replace(' ', '0');
	}
	
	public static String generateCCode(String comp, String dest, String jump) {
		return "111" + comp(comp) + String.format("%3s", dest(dest)).replace(' ', '0') + String.format("%3s", jump(jump)).replace(' ', '0');
	}
	
}
