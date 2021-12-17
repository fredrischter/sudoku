package com.test.sudoku.repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

import org.apache.commons.io.FileUtils;

import com.test.sudoku.model.SudokuDataFactory;
import com.test.sudoku.model.SudokuImmutableData;
import com.test.sudoku.model.exception.SudokuException;
import com.test.sudoku.model.exception.SudokuFileNotFoundException;
import com.test.sudoku.model.exception.SudokuNotExpectedColumnCountException;
import com.test.sudoku.model.exception.SudokuNotExpectedRowCountException;

/**
 * 
 * @author fred
 *
 * Responsible for retrieving healthy data set.
 *
 */
public class SudokuFileRepository {
	
	SudokuDataFactory sudokuDataFactory = new SudokuDataFactory(); 

	public SudokuImmutableData loadFromResourceFile(String fileName) throws SudokuException {
		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new SudokuFileNotFoundException();
		}
        File file = FileUtils.getFile(resource.getPath());
        return loadFromFile(file);
	}

	public SudokuImmutableData loadFromFile(File file) throws SudokuException {
        try {
			String content = FileUtils.readFileToString(file, Charset.defaultCharset());
			SudokuImmutableData sudokuImmutableData = sudokuDataFactory.fromString(content);
			
			if (!sudokuImmutableData.hasExpectedRowCount()) {
				throw new SudokuNotExpectedRowCountException(sudokuImmutableData.getRowCount());
			};
			
			IntStream.range(0, SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT).forEach(i -> {
				if (!sudokuImmutableData.hasExpectedColumnCount(i)) {
					throw new SudokuNotExpectedColumnCountException(i++, sudokuImmutableData.getColumnCount(i));
				}
			});
			
			return sudokuImmutableData;
		} catch (IOException e) {
			throw new SudokuFileNotFoundException();
		}
	}
	
}
