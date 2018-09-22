package com.aniruddha.nand2tetris.Assembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An Assembler for the Hack computer
 * 
 * @author Aniruddha Dave
 */
public class Assembler {

	String inputFileName;
	String outputFileName;
	// Symbols Table with pre-defined symbols
	SymbolsTable symTable; 
	// Start new symbols from address 16
	Integer symbolAddress = 16;
	
	/**
	 * @param inputFileName
	 */
	public Assembler(String inputFileName) {
		this.inputFileName = inputFileName;
		this .outputFileName = getOutputFilename(inputFileName);
		this.symTable = new SymbolsTable();
	}
	
	/**
	 * @param inputFileName
	 * @param outputFileName
	 */
	public Assembler(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.symTable = new SymbolsTable();
	}
	
	/**
	 * @param inputFilename
	 * @return outputFileName with ".hack" ext
	 */
	public static String getOutputFilename(String inputFilename) {
		if (inputFilename.endsWith(".asm")) {
			return inputFilename.replace(".asm", ".hack");
		} else {
			return inputFilename + ".hack";
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Args: <file.asm>");
		} else {
			String inputFileName = args[0];
			Assembler asm = new Assembler(inputFileName);
			asm.assemble();
		}
	}
	
	public void assemble() throws IOException {
		this.firstPass();
		this.secondPass();
	} 
	/**
	 * First pass through the ".asm" file
	 * Identifies the memory location for label definitions
	 * (LABEL)
	 */
	public void firstPass() {
		Parser parser = new Parser(this.inputFileName);
		int currentAddress = 0;
		while(parser.hasMoreCommands()) {
			parser.advance();
			if ((parser.commandType() == parser.ACOMMAND) || (parser.commandType() == parser.CCOMMAND)) {
				currentAddress += 1;
			} else if (parser.commandType() == parser.LCOMMAND) {
				this.symTable.addSymbol(parser.symbol(), currentAddress);
			}
		}
	}
	
	/**
	 * Performs Assembly 
	 * Generated byte code and outputs to file
	 * @throws IOException
	 */
	public void secondPass() throws IOException {
		Parser parser = new Parser(this.inputFileName);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(new File(this.outputFileName)));
		
		while(parser.hasMoreCommands()) {
			parser.advance();
			if (parser.commandType() == parser.ACOMMAND) {
				bw.write(Code.generateACode(this.getAddress(parser.symbol())));
				bw.write("\n");
			} else if (parser.commandType() == parser.CCOMMAND) {
				bw.write(Code.generateCCode(parser.comp(), parser.dest(), parser.jump()));
				bw.write("\n");
			} else if (parser.commandType() == parser.LCOMMAND) {
				continue;
			}
		}
		bw.close();
		
	}
	
	/**
	 * @param symbol
	 * @return int address
	 */
	public int getAddress(String symbol) {
		if (Utils.isNumeric(symbol)) {
			return Integer.parseInt(symbol);
		} else {
			if (!this.symTable.contains(symbol)) {
				this.symTable.addSymbol(symbol, this.symbolAddress);
				this.symbolAddress += 1;
			}
			return this.symTable.getAddress(symbol);
		}
			
	}
	
}