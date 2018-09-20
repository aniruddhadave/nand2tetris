package com.aniruddha.nand2tetris.Assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	
	public static final int ACOMMAND = 0;
	public static final int CCOMMAND = 1;
	public static final int LCOMMAND = 2;

	BufferedReader br;
	String currentLine;
	Integer currentLineNo;
	String command;
	Long lineCount;
	
	public Parser(String fileName) {
		Path path = Paths.get(fileName);
		try {
			this.lineCount = Files.lines(path).count();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.currentLine = "";
		this.currentLineNo= 0;
		this.command = "";
		
		File inputFile = new File(fileName);
		try {
			this.br = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFile);
			e.printStackTrace();
		}
	}
	
	public Boolean hasMoreCommands() {
		if ( this.currentLineNo + 1 < this.lineCount) {
			return true;
		} else {
			return false;
		}
	}
	
	public void advance() {
		this.currentLineNo += 1;
		try {
			this.currentLine = this.br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.currentLine.equals("\n")) {
			this.advance();
		} else {
			this.command = this.currentLine.trim();
		}
	}
	
	public int commandType() {
		if (this.command.matches("^@.*")) {
			return ACOMMAND;
		} else if (this.command.matches("^(.*")) {
			return LCOMMAND;
		} else return CCOMMAND;
	}
	
	public String symbol() {
		Pattern pattern = Pattern.compile("^[@(](.*?))?$");
		Matcher matcher = pattern.matcher(this.command);
		try {
			matcher.find();
		} catch (Exception e) {
			System.out.println("Command cannot be parsed. Neither A-Command nor L-Command: " + this.command);
		}
		return matcher.group(1);
	}
	
	public String dest() {
		Pattern pattern = Pattern.compile("^(.*?)=.*$");
		Matcher matcher = pattern.matcher(this.command);
		String dest = "";
		if (matcher.find()) {
			dest = matcher.group(1);
		}
		return dest;
	}
	
	public String comp() {
		Pattern pattern = Pattern.compile("^.*?=(.*?);.*$");
		Matcher matcher = pattern.matcher(this.command);
		String comp = "";
		if (matcher.find()) {
			comp = matcher.group(1);
		}
		return comp;
	}
	
	public String jump() {
		Pattern pattern = Pattern.compile("^.*?;(\\w+)$");
		Matcher matcher = pattern.matcher(this.command);
		String jump = "";
		if (matcher.find()) {
			jump = matcher.group(1);
		}
		return jump;
	}
}
