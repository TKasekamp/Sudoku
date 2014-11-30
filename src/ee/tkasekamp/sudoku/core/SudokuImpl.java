package ee.tkasekamp.sudoku.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ee.tkasekamp.sudoku.App;
import ee.tkasekamp.sudoku.reader.SudokuParser;

public class SudokuImpl implements Sudoku {
	private SudokuParser parser;
	private int[][] table;
	private int[][] grid;
	public static final Integer[] NUMBERS = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	@Override
	public void initialize() {
		parser = new SudokuParser();

	}

	@Override
	public void readTestSudoku() {
		try {
			table = parser.parseSudokuResources(SudokuParser.TEST_SUDOKU);
			grid = parser.parseSudokuResources(SudokuParser.TEST_SUDOKU);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void solveSudoku() {
		while (!checkIfSolved()) {
			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table.length; j++) {
					int grid = calculateGrid(i, j);
					if (grid != SudokuParser.EMPTY) {
						table[i][j] = grid;
						App.window.updateTable(i, j, grid);
					}
				}
			}
		}

	}

	@Override
	public void reset() {
		table = null;
		grid = null;

	}

	/**
	 * Calculates what should be in this grid
	 * 
	 * @param i
	 *            table row
	 * @param j
	 *            table column
	 * @return
	 */
	private int calculateGrid(int i, int j) {
		// Checking if filled
		if (table[i][j] != SudokuParser.EMPTY)
			return table[i][j];

		int regionNr = grid[i][j];
		Set<Integer> nums = new HashSet<Integer>(Arrays.asList(NUMBERS));
		removeSubRegion(regionNr, nums);
		removeColumn(i, j, nums);
		removeLine(i, j, nums);
		return 2;
	}

	/**
	 * Sudoku is solved if there are no empty cells.
	 * 
	 * @return boolean
	 */
	private boolean checkIfSolved() {
		for (int[] is : table) {
			for (int i : is) {
				if (i == SudokuParser.EMPTY)
					return false;
			}
		}
		return true;
	}

	private void removeSubRegion(int regionNr, Set<Integer> nums) {

	}

	private void removeLine(int i, int j, Set<Integer> nums) {

	}

	private void removeColumn(int i, int j, Set<Integer> nums) {

	}

}
