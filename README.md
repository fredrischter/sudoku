# Overview

Simple sudoku file validation application.

Data structure problem was treated as exception, as it is not expected to happen. Logical "error" like having repeated number was treated as information added to a ValidationResult, as is expected to happen during the game.

As I like data centered approach, I've left some validation withing the data class. Repository responsible for healthy retrieval. Service responsible for game logic / validation as a top level layer.

Packaging includes dependencies. Used some apache commons libs to simplify code.

# Requirements

Maven 3
Java 14

# Instruction

To run, execute validate.bat providing file address as parameter. Examples:

validate.bat src\test\resources\valid.txt
validate.bat src\test\resources\validEmpty.txt
validate.bat src\test\resources\sevenRows.txt

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

# To do

- To reject unsolvable sudokus.
- Replace tabs with spaces / format everything with agreed format.
- To test complexity.
