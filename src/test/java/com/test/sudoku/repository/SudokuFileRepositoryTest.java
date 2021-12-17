package com.test.sudoku.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.test.sudoku.model.SudokuImmutableData;
import com.test.sudoku.model.SudokuDataFactory;
import com.test.sudoku.model.exception.SudokuException;
import com.test.sudoku.model.exception.SudokuFileNotFoundException;
import com.test.sudoku.model.exception.SudokuNotExpectedColumnCountException;
import com.test.sudoku.model.exception.SudokuNotExpectedRowCountException;

public class SudokuFileRepositoryTest {
	
	SudokuFileRepository sudokuFileRepository = new SudokuFileRepository();
	
    @Test
    public void loadFromFileTest() throws SudokuException
    {
    	// Given
    	SudokuDataFactory sudokuDataFactory = new SudokuDataFactory();
    	SudokuImmutableData staticData = sudokuDataFactory.fromString(
    			"9, ,4, ,6, ,7, ,1\r\n"
    			+ " ,2, ,4, ,3, ,8, \r\n"
    			+ "8, , , , , , , ,4\r\n"
    			+ " , ,1,8,4,9,6, , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , ,3,2,5,7,9, , \r\n"
    			+ "4, , , , , , , ,7\r\n"
    			+ " ,8, ,6, ,4, ,5, \r\n"
    			+ "5, ,6, ,8, ,2, ,3");
    	
    	SudokuImmutableData staticDataEmpty = sudokuDataFactory.empty();
    	
    	// When
    	SudokuImmutableData fromFile = sudokuFileRepository.loadFromResourceFile("puzzle.txt");

    	// Then
    	assertEquals(staticData, fromFile);
    	assertNotSame(staticDataEmpty, fromFile);
    }

    @Test(expected = SudokuFileNotFoundException.class)
    public void fileNotFound() throws SudokuException
    {
    	sudokuFileRepository.loadFromResourceFile("not existing.txt");
    }

    @Test(expected = SudokuNotExpectedRowCountException.class)
    public void notEnoughRows() throws SudokuException
    {
    	sudokuFileRepository.loadFromResourceFile("sevenRows.txt");
    }

    @Test(expected = SudokuNotExpectedColumnCountException.class)
    public void notEnoughColumns() throws SudokuException
    {
    	sudokuFileRepository.loadFromResourceFile("sevenColumns.txt");
    }

}
