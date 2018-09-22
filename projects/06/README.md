# Assembler for Hack Computer
The Assembler is written in Java programming language for the Hack computer built for nand2tetris project.

## Requirements
- Java 8
- Maven

## Installation
In the current folder, type the following command and create the executable jar
```sh
$ mvn clean install
```

## Execution
```sh
$ java -jar ./target/<jar file> <Assembly File>
```
Example:
```sh
$ java -jar ./target/Assembler-0.0.1-SNAPSHOT.jar ./pong/Pong.asm
```

## Output
The Assembler converts the given assembly code into bytecode and outputs it into a file in the same location as input with a ".hack" extension.
