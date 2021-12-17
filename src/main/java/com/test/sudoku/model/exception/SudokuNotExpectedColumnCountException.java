package com.test.sudoku.model.exception;

public class SudokuNotExpectedColumnCountException extends SudokuException {

	private static final long serialVersionUID = 3980369559810115978L;
	private int index;
	private int count;
	
	public SudokuNotExpectedColumnCountException(int index, int count) {
		this.index = index;
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public int getCount() {
		return count;
	}
}
