package ee.tkasekamp.sudoku.core;

import java.util.Random;

import ee.tkasekamp.sudoku.reader.SudokuParser;

public class SudokuImpl implements Sudoku {
	private SudokuParser parser;
	private int[][] table;
	private int[][] grid;
	private boolean solutionFound = false;

	@Override
	public void initialize() {
		parser = new SudokuParser();

	}

	@Override
	public void reset() {
		table = null;
		grid = null;
		solutionFound = false;

	}

	@Override
	public void readTestSudoku() {
		table = parser.parseSudokuResources(SudokuParser.TEST_SUDOKU);
		grid = parser.parseSudokuResources(SudokuParser.DEFAULT_GRID);

	}

	@Override
	public void solveSudoku() {
		solve(0, 0);

	}

	@Override
	public int[][] getTable() {
		return table;
	}

	@Override
	public void readSudoku(String path) {

		table = parser.parseSudoku(path);
		grid = parser.parseSudokuResources(SudokuParser.DEFAULT_GRID);

	}

	@Override
	public void readGrid(String path) {

		grid = parser.parseSudoku(path);

	}

	@Override
	public void generateSudoku() {

		grid = parser.parseSudokuResources(SudokuParser.DEFAULT_GRID);

		table = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				table[i][j] = SudokuParser.EMPTY;
			}
		}
		// Starting at a random point
		solve(randomNumber(), randomNumber());
		// Making it a bit more random
		shuffleTable();
	}

	@Override
	public void readTestJigsaw() {
		grid = parser.parseSudokuResources(SudokuParser.JIGSAW_GRID);
		table = parser.parseSudokuResources(SudokuParser.TEST_JIGSAW);

	}

	/**
	 * Recursive function that tries to put a number into a cell. If no numbers
	 * can be inserted then it backtracks and starts with a new number.
	 * 
	 * @param i
	 * @param j
	 */
	private void solve(int i, int j) {
		if (checkIfSolved()) {
			solutionFound = true;
			return;
		}

		if (table[i][j] != SudokuParser.EMPTY)
			solveHepler(i, j);
		else {
			// Find a valid number for the empty cell
			for (int num = 1; num < 10; num++) {
				if (checkIfInRow(i, num) && checkIfInCol(j, num)
						&& checkIfInGrid(i, j, num)) {
					table[i][j] = num;

					solveHepler(i, j);

					if (solutionFound)
						return;

				}
			}

			table[i][j] = SudokuParser.EMPTY;
		}
	}

	/**
	 * Finds the next cell to solve. If at the end then starts at the beginning.
	 * 
	 * @param i
	 * @param j
	 */
	private void solveHepler(int i, int j) {

		if (i == 8 && j == 8)
			solve(0, 0);
		else if (j < 8)
			solve(i, j + 1);
		else
			solve(i + 1, 0);

	}

	private boolean checkIfInRow(int i, int num) {
		for (int j = 0; j < 9; j++)
			if (table[i][j] == num)
				return false;

		return true;
	}

	private boolean checkIfInCol(int j, int num) {
		for (int i = 0; i < 9; i++)
			if (table[i][j] == num)
				return false;

		return true;
	}

	private boolean checkIfInGrid(int row, int col, int num) {
		int gridNr = grid[row][col];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == gridNr && table[i][j] == num) {
					return true;
				}
			}
		}
		return true;
	}

	/**
	 * Sudoku is solved if there are no empty cells.
	 * 
	 * @return boolean
	 */
	private boolean checkIfSolved() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (table[i][j] == SudokuParser.EMPTY)
					return false;
			}
		}
		return true;
	}

	private int randomNumber() {
		Random random = new Random();
		return (int) (random.nextDouble() * 9);
	}

	/**
	 * Switches rows to make the table more random.
	 */
	private void shuffleTable() {
		for (int a = 0; a < randomNumber(); a++) {
			int i = randomNumber();
			int j = randomNumber();
			int[] s = table[i];
			table[i] = table[j];
			table[j] = s;

		}
	}

	@Override
	public int[][] getGrid() {
		return grid;
	}

}
