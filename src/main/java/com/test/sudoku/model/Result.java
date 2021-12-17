package com.test.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public class Result {

	private Set<String> errors = new HashSet<>();
	
	public void add(String errorMessage) {
		errors.add(errorMessage);
	}

	public boolean isValid() {
		return errors.isEmpty();
	}

	public int errorCount() {
		return errors.size();
	}
	
	public boolean contains(String message) {
		return errors.stream().anyMatch(s -> s.contains(message));
	}
	
	public String getMessages() {
		StringBuilder stringBuilder = new StringBuilder();
		errors.stream().forEach(s -> stringBuilder.append((stringBuilder.length() > 0 ? "\n" : "") + s));
		return stringBuilder.toString();
	}

}
