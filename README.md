# Overview

Simple sudoku file validation application.

# Requirements

Maven
Java

# Instruction

To run, execute validate.bat providing file address as parameter. Example:

validate.bat src\test\resources\valid.txt

File should be in the following format:

9, ,4, ,6, ,7, ,1
 ,2, ,4, ,3, ,8, 
8, , , , , , , ,4
 , ,1,8,4,9,6, , 
 , , , , , , , , 
 , ,3,2,5,7,9, , 
4, , , , , , , ,7
 ,8, ,6, ,4, ,5, 
5, ,6, ,8, ,2, ,3

# Errors

## File structure errors.

P01 - Missing parameter - file.
P02 - File not found.
P03 - Doesn't contain the expected rows count. X rows found.
P04 - Row doesn't contain the expected columns count. X columns found on the row Y.
P05 - Unwnown error.

## Logical structure errors.

L01 - Invalid row, having repeated number. Row X, repeated number Y.
L02 - Invalid column, having repeated number. Column X, repeated number Y.
L03 - Invalid box, having repeated number. Box X,Y, repeated number X.
