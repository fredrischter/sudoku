package com.test.sudoku.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.test.sudoku.model.Result;
import com.test.sudoku.model.SudokuImmutableData;
import com.test.sudoku.model.SudokuDataFactory;

public class SudokuValidationServiceTest 
{
	SudokuValidationService sudokuValidationService = new SudokuValidationService(); 

    @Test
    public void validCase()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
    			"9,3,4,5,6,8,7,2,1\r\n"
    			+ "1,2,7,4,9,3,5,8,6\r\n"
    			+ "8,6,5,7,1,2,3,9,4\r\n"
    			+ "7,5,1,8,4,9,6,3,2\r\n"
    			+ "2,9,8,1,3,6,4,7,5\r\n"
    			+ "6,4,3,2,5,7,9,1,8\r\n"
    			+ "4,1,9,3,2,5,8,6,7\r\n"
    			+ "3,8,2,6,7,4,1,5,9\r\n"
    			+ "5,7,6,9,8,1,2,4,3");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertTrue(result.isValid());
    }
    
    @Test
    public void invalidJustNotFinished()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
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
    	assertFalse(result.isValid());
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L04 - Not finished."));
    }
    
    @Test
    public void invalidRow()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
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
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 1, repeated number 9."));
    	assertTrue(result.contains("L04 - Not finished."));
    }

    @Test
    public void invalidColumn()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
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
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L02 - Invalid column, having repeated number. Column 1, repeated number 9."));
    	assertTrue(result.contains("L04 - Not finished."));
    }

    @Test
    public void invalidBox()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
                " , , ,9, , , , , \r\n"
    			+ " , , , ,9, , , , \r\n"
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
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 2,1, repeated number 9."));
    	assertTrue(result.contains("L04 - Not finished."));
    }

    @Test
    public void threeErrors()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
                " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , ,7, ,7\r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(3, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 5, repeated number 7."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 3,2, repeated number 7."));
    	assertTrue(result.contains("L04 - Not finished."));
    }
    
    @Test
    public void fourErrors()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
                " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , ,5, ,5\r\n"
    			+ " , , , , , ,5, , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(4, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 8, repeated number 5."));
    	assertTrue(result.contains("L02 - Invalid column, having repeated number. Column 7, repeated number 5."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 3,3, repeated number 5."));
    	assertTrue(result.contains("L04 - Not finished."));
    }
    
    @Test
    public void twoSimilarErrors()
    {
    	// Given
    	SudokuImmutableData sudokuData = new SudokuDataFactory().fromString(
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
    	assertEquals(3, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 1, repeated number 9."));
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 2, repeated number 1."));
    	assertTrue(result.contains("L04 - Not finished."));
    }
}
