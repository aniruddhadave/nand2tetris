// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

  @offset
  M=0

(LOOP)
  @KBD
  D=M

  @COLOR_BLACK
  D;JGT

  @COLOR_WHITE
  0;JMP

(COLOR_BLACK)
  @key
  M=-1

  @COLOR
  0;JMP

(COLOR_WHITE)
  @key
  M=0

  @COLOR
  0;JMP

(COLOR)
  @SCREEN
  D=A
  @offset
  D=D+M
  @index
  M=D

  // Color at the index
  @key
  D=M
  @index
  A=M
  M=D

  @offset
  M=M+1
  D=M
  @8192
  D=A-D
  @CONTINUE
  D;JGT
  @offset
  M=0

(CONTINUE)
  @LOOP
  0;JMP
