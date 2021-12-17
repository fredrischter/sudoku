package com.test.sudoku.model;

import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @author fred
 * 
 * Holds immutable sudoku data and rule about it.
 * 
 * Preferred to write the structural verification here to keep it near to the data.
 * 
 */
public final class SudokuImmutableData {

	public static final int ROWS_AND_COLUMNS_EXPECTED_COUNT = 9;
	public static final int HIGHEST_NUMBER = 9;
	public static final int HORIZONTAL_BOX_COUNT = 3;
	public static final int VERTICAL_BOX_COUNT = 3;
	public static final int ROWS_PER_BOX = 3;
	public static final int COLUMNS_PER_BOX = 3;
	
	private byte [][]data;
	
	public SudokuImmutableData(byte[][] data) {
		this.data = ArrayUtils.clone(data);
	}
	
	public byte getValue(int row, int column) {
		return data[row][column];
	}

	public boolean isEmpty(int row, int column) {
		return getValue(row, column) == 0;
	}

	public int getRowCount() {
		return data.length;
	}

	public boolean hasExpectedRowCount() {
		return getRowCount() == ROWS_AND_COLUMNS_EXPECTED_COUNT;
	}

	public int getColumnCount(int i) {
		return data[i].length;
	}

	public boolean hasExpectedColumnCount(int i) {
		return getColumnCount(i) == ROWS_AND_COLUMNS_EXPECTED_COUNT;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof SudokuImmutableData)) {
			return false;
		}
		SudokuImmutableData sudokuImmutableData = (SudokuImmutableData) o;
		
		if (sudokuImmutableData.getRowCount() != getRowCount()) {
			return false;
		}
		
		return !IntStream.range(0, getRowCount()).anyMatch(i -> {
			if (sudokuImmutableData.getColumnCount(i) != getColumnCount(i)) {
				return true;
			}
			return IntStream.range(0, getColumnCount(i))
				.anyMatch(j -> sudokuImmutableData.getValue(i, j)!=getValue(i, j));
		});
	}

}
