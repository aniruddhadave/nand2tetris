// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

  // 1. Initialize the result as 0
  @R2
  M=0         // R2= 0

  // 2. Initialize the iterator
  @i
  M=0         // i= 0

  // 3. If R0 is 0 terminate the program
  @R0
  D=M
  @END
  D;JEQ

(LOOP)
  // 4. Check whether R1 was added R0 times
  @i
  D=M         // D= i
  @R0
  D= D-M      // D= i - R0
  @END
  D;JGE       // If i - R0 == 0 goto END

  // 5. Add R1 to R2
  @R1
  D=M         // D= R1
  @R2
  M=D+M       // R2= R1 + R2
  @i
  M=M+1
  @LOOP
  0;JMP

(END)
  @END
  0;JMP
