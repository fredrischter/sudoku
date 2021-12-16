package com.test.sudoku.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.test.sudoku.model.Result;
import com.test.sudoku.model.SudokuData;
import com.test.sudoku.model.SudokuDataFactory;

public class SudokuValidationServiceTest 
{
	SudokuValidationService sudokuValidationService = new SudokuValidationService(); 

    @Test
    public void validCase()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
    			"9, ,4, ,6, ,7, ,1\r\n"
    			+ " ,2, ,4, ,3, ,8, \r\n"
    			+ "8, , , , , , , ,4\r\n"
    			+ " , ,1,8,4,9,6, , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , ,3,2,5,7,9, , \r\n"
    			+ "4, , , , , , , ,7\r\n"
    			+ " ,8, ,6, ,4, ,5, \r\n"
    			+ "5, ,6, ,8, ,2, ,3");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertTrue(result.isValid());
    }
    
    @Test
    public void invalidRow()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, , , ,9, , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number."));
    }

    @Test
    public void invalidColumn()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ "9, , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L02 - Invalid column, having repeated number."));
    }

    @Test
    public void invalidBox()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, , , , , , , , \r\n"
    			+ " ,9, , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L03 - Invalid box, having repeated number."));
    }

    @Test
    public void twoErrors()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, ,9, , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number."));
    }
    
    @Test
    public void threeErrors()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, ,9, , , , , , \r\n"
    			+ "9, , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(3, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number."));
    	assertTrue(result.contains("L02 - Invalid column, having repeated number."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number."));
    }
    
    @Test
    public void twoSimilarErrors()
    {
    	// Given
    	SudokuData sudokuData = new SudokuDataFactory().fromString(
                "9, , , ,9, , , , \r\n"
    			+ "1, , , ,1, , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 1, repeated number 9."));
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 2, repeated number 1."));
    }
}
