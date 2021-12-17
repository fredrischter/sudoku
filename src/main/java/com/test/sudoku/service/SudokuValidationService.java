package com.test.sudoku.service;

import java.util.stream.IntStream;

import com.test.sudoku.model.Result;
import com.test.sudoku.model.SudokuImmutableData;

public class SudokuValidationService {

	public Result validate(SudokuImmutableData sudokuData) {
		Result result = new Result();
		collectRowErrors(sudokuData, result);
		collectColumnErrors(sudokuData, result);
		collectBoxErrors(sudokuData, result);
		return result;
	}

	private void collectRowErrors(SudokuImmutableData sudokuData, Result result) {
		IntStream
			.range(0, SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT)
			.forEach(row -> collectSpecificRowErrors(sudokuData, row, result));
	}

	private void collectSpecificRowErrors(SudokuImmutableData sudokuData, int row, Result result) {
		boolean table[] = newNumberTable();
		IntStream
			.range(0, SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT)
			.forEach(column -> {
				int number = sudokuData.getValue(row, column);
				if (table[number]) {
					result.add("L01 - Invalid row, having repeated number. Row " + (row + 1) + ", repeated number " + number + ".");
				}
				if (number != 0) {
					table[number] = true;
				}
			});
	}

	private void collectColumnErrors(SudokuImmutableData sudokuData, Result result) {
		IntStream
			.range(0, SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT)
			.forEach(column -> collectSpecificColumnErrors(sudokuData, column, result));
	}

	private void collectSpecificColumnErrors(SudokuImmutableData sudokuData, int column, Result result) {
		boolean table[] = newNumberTable();
		IntStream.range(0, SudokuImmutableData.ROWS_AND_COLUMNS_EXPECTED_COUNT)
			.forEach(row -> {
				int number = sudokuData.getValue(row, column);
				if (table[number]) {
					result.add("L02 - Invalid column, having repeated number. Column " + (column + 1) + ", repeated number " + number + ".");
				}
				if (number != 0) {
					table[number] = true;
				}
			});
	}

	private void collectBoxErrors(SudokuImmutableData sudokuData, Result result) {
		IntStream.range(0, SudokuImmutableData.VERTICAL_BOX_COUNT).forEach(y -> {
			IntStream.range(0, SudokuImmutableData.HORIZONTAL_BOX_COUNT).forEach(x -> {
				collectSpecificBoxErrors(sudokuData, x, y, result);
			});
		});
	}

	private void collectSpecificBoxErrors(SudokuImmutableData sudokuData, int x, int y, Result result) {
		boolean table[] = newNumberTable();
		IntStream.range(0, SudokuImmutableData.ROWS_PER_BOX).forEach(row -> {
			IntStream.range(0, SudokuImmutableData.COLUMNS_PER_BOX).forEach(column -> {
				int number = sudokuData.getValue(y * SudokuImmutableData.ROWS_PER_BOX + row,
						x * SudokuImmutableData.COLUMNS_PER_BOX + column);
				if (table[number]) {
					result.add("L03 - Invalid box, having repeated number. Box " + (x + 1) + "," + (y + 1) + ", repeated number " + number + ".");
				}
				if (number != 0) {
					table[number] = true;
				}
			});
		});
	}

	private boolean[] newNumberTable() {
		return new boolean[SudokuImmutableData.HIGHEST_NUMBER + 1];
	}

}
