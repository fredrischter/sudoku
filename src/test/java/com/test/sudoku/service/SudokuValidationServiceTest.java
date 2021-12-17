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
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 1, repeated number 9."));
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
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L02 - Invalid column, having repeated number. Column 1, repeated number 9."));
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
    	assertEquals(1, result.errorCount());
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 2,1, repeated number 9."));
    }

    @Test
    public void twoErrors()
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
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 5, repeated number 7."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 3,2, repeated number 7."));
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
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , , , , \r\n"
    			+ " , , , , , ,5, ,5\r\n"
    			+ " , , , , , ,5, , ");
    	
    	// When
    	Result result = sudokuValidationService.validate(sudokuData);
    	
    	// Then
    	assertFalse(result.isValid());
    	assertEquals(3, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 8, repeated number 5."));
    	assertTrue(result.contains("L02 - Invalid column, having repeated number. Column 7, repeated number 5."));
    	assertTrue(result.contains("L03 - Invalid box, having repeated number. Box 3,3, repeated number 5."));
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
    	assertEquals(2, result.errorCount());
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 1, repeated number 9."));
    	assertTrue(result.contains("L01 - Invalid row, having repeated number. Row 2, repeated number 1."));
    }
}
