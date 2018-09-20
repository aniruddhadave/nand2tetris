package com.aniruddha.nand2tetris.Assembler;

import java.util.HashMap;
import java.util.Map;

public class SymbolsTable {
	
	public static Map<String, Integer> symbolsMap = new HashMap<String, Integer>();
	
	public SymbolsTable() {
		symbolsMap.put("SP", 0);
		symbolsMap.put("LCL", 1);
		symbolsMap.put("ARG", 2);
		symbolsMap.put("THIS", 3);
		symbolsMap.put("THAT", 4);
		symbolsMap.put("R0", 0);
		symbolsMap.put("R1", 1);
		symbolsMap.put("R2", 2);
		symbolsMap.put("R3", 3);
		symbolsMap.put("R4", 4);
		symbolsMap.put("R5", 5);
		symbolsMap.put("R6", 6);
		symbolsMap.put("R7", 7);
		symbolsMap.put("R8", 8);
		symbolsMap.put("R9", 9);
		symbolsMap.put("R10", 10);
		symbolsMap.put("R11", 11);
		symbolsMap.put("R12", 12);
		symbolsMap.put("R13", 13);
		symbolsMap.put("R14", 14);
		symbolsMap.put("R15", 15);
		symbolsMap.put("SCREEN", 16384);
		symbolsMap.put("KBD", 24576);
	}
	
	public void addSymbol(String symbol, Integer address){
		symbolsMap.put(symbol, address);
	}
	
	public boolean contains(String symbol) {
		return symbolsMap.containsKey(symbol);
	}
	
	public int getAddress(String symbol) {
		return symbolsMap.get(symbol);
	}
}
