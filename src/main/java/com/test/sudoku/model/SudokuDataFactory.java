package com.test.sudoku.model;

import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author fred
 * 
 * Just brings together data to build the data object.
 *
 */
public class SudokuDataFactory {
	
	private static final byte [][]emptyTemplateData = new byte[SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT][SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT];
	private static final SudokuImmutableData emptyTemplate = new SudokuImmutableData(emptyTemplateData);

	public SudokuImmutableData fromString(String input) {
		String []rows = input.split("\n");
		byte [][]data = new byte[rows.length][];
	
		IntStream.range(0, rows.length).forEach(i -> {
			String []numbers = rows[i].split(",");
			data[i] = new byte[numbers.length];
			IntStream.range(0, numbers.length).forEach(j -> {
				String number = numbers[j].trim();
				if (StringUtils.isEmpty(number)) {
					data[i][j] = 0;
				} else {
					data[i][j] = Byte.valueOf(number);
				}
			});
		});
		
		return new SudokuImmutableData(data);
	}

	public SudokuImmutableData empty() {
		return emptyTemplate;
	}

}
