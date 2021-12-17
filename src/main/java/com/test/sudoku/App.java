package com.test.sudoku;

import java.io.File;

import com.test.sudoku.model.Result;
import com.test.sudoku.model.SudokuImmutableData;
import com.test.sudoku.model.exception.SudokuFileNotFoundException;
import com.test.sudoku.model.exception.SudokuNotExpectedColumnCountException;
import com.test.sudoku.model.exception.SudokuNotExpectedRowCountException;
import com.test.sudoku.repository.SudokuFileRepository;
import com.test.sudoku.service.SudokuValidationService;

public class App 
{
	
	private static final int RETURN_MISSING_PARAMETER = 1;
	private static final int RETURN_INVALID = 2;
	private static final int RETURN_FILE_NOT_FOUND = 3;
	private static final int RETURN_INVALID_ROW_COUNT = 4;
	private static final int RETURN_INVALID_COLUMN_COUNT = 5;
    private static final int RETURN_UNKNOWN_ERROR = 6;

	public static void main( String[] args ) throws Exception
    {
		if (args.length == 0) {
			System.out.println("P01 - Missing parameter - file.");
			System.exit(RETURN_MISSING_PARAMETER);
		}
    	SudokuFileRepository sudokuFileRepository = new SudokuFileRepository();
    	SudokuValidationService sudokuValidationService = new SudokuValidationService();
    	try {
			SudokuImmutableData sudokuImmutableData = sudokuFileRepository.loadFromFile(new File(args[0]));
			Result result = sudokuValidationService.validate(sudokuImmutableData);
			
			if (!result.isValid()) {
				System.out.print(result.getMessages());
				System.exit(RETURN_INVALID);
			}
		} catch (SudokuFileNotFoundException e) {
			System.out.print("P02 - File not found.");
			System.exit(RETURN_FILE_NOT_FOUND);
		} catch (SudokuNotExpectedRowCountException e) {
			System.out.print("P03 - Doesn't contain the expected rows count. "+e.getCount()+" rows found.");
			System.exit(RETURN_INVALID_ROW_COUNT);
		} catch (SudokuNotExpectedColumnCountException e) {
			System.out.print("P04 - Row doesn't contain the expected columns count. "+e.getCount()+" columns found on the row "+e.getIndex()+".");
			System.exit(RETURN_INVALID_COLUMN_COUNT);
		} catch (Exception e) {
			System.out.print("P05 - Unknown error. "+e.getMessage());
			System.exit(RETURN_UNKNOWN_ERROR);
		}
    }
}
