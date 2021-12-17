package com.test.sudoku.model.exception;

public class SudokuNotExpectedRowCountException extends SudokuException {

	private static final long serialVersionUID = 4456520305773374749L;
	private int count;
	
	public SudokuNotExpectedRowCountException(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

}
