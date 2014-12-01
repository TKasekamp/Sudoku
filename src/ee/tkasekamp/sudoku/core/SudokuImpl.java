package ee.tkasekamp.sudoku.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ee.tkasekamp.sudoku.reader.SudokuParser;
import static ee.tkasekamp.sudoku.reader.SudokuParser.EMPTY;

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
		readGrid(SudokuParser.DEFAULT_GRID);
		readSudoku(SudokuParser.TEST_SUDOKU);
	}

	@Override
	public void solveSudoku() {

		while (!checkIfSolved()) {
			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table.length; j++) {
					if (table[i][j] == EMPTY) {
						int grid = calculateGrid(i, j);
						if (grid != EMPTY) {
							table[i][j] = grid;
						}
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

	@Override
	public int[][] getTable() {
		return table;
	}

	@Override
	public void readSudoku(String path) {
		try {
			table = parser.parseSudokuResources(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void readGrid(String path) {
		try {
			grid = parser.parseSudokuResources(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void generateSudoku() {
		readGrid(SudokuParser.DEFAULT_GRID);

	}

	private int calculateGrid(int i, int j) {
		int regionNr = grid[i][j];
		Set<Integer> suitable = findSuitable(i, j, regionNr);
		System.out.println("Calculate grid for " + i + ", " + j);
		// System.out.println(suitable.toString());

		List<Set<Integer>> stuffList = new ArrayList<>();

		System.out.println("Starting with not suitable");
		for (int k = 0; k < table.length; k++) {
			for (int k2 = 0; k2 < table.length; k2++) {
				boolean isThis = (k == i) && (k2 == j);
				if (!isThis && (grid[k][k2] == regionNr)
						&& (table[k][k2] == EMPTY)) {
					Set<Integer> notSuitable = findAllInLine(k);
					notSuitable.addAll(findAllInColumn(k2));
					// System.out.println("for " + k + ", " + k2
					// + notSuitable.toString());
					stuffList.add(notSuitable);

				}
			}
		}
		// System.out.println(stuffList.toString());

		// number && is in all stuffList
		for (Integer number : suitable) {
			boolean isInAll = true;
			for (Set<Integer> set : stuffList) {
				isInAll = isInAll && set.contains(number);
			}
			if (isInAll)
				return number;
		}
		return EMPTY;
	}

	private Set<Integer> findAllInLine(int i) {
		Set<Integer> inThisLine = new HashSet<>();
		for (int j = 0; j < table.length; j++) {
			if (table[i][j] != EMPTY) {
				inThisLine.add(table[i][j]);
			}
		}
		return inThisLine;
	}

	private Set<Integer> findAllInColumn(int j) {
		Set<Integer> inThisColum = new HashSet<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i][j] != EMPTY) {
				inThisColum.add(table[i][j]);
			}
		}
		return inThisColum;
	}

	/**
	 * Finds a set of numbers that can be in this square.
	 * 
	 * @param i
	 *            table row
	 * @param j
	 *            table column
	 * @return
	 */
	private Set<Integer> findSuitable(int i, int j, int regionNr) {
		// Checking if filled
		Set<Integer> nums = new HashSet<Integer>(Arrays.asList(NUMBERS));
		nums = removeSubRegion(regionNr, nums);
		nums = removeColumn(j, nums);
		nums = removeLine(i, nums);
		return nums;
	}

	/**
	 * Sudoku is solved if there are no empty cells.
	 * 
	 * @return boolean
	 */
	private boolean checkIfSolved() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				if (table[i][j] == SudokuParser.EMPTY)
					return false;
			}
		}
		return true;
	}

	/**
	 * Removes all numbers from nums that are already present in the region
	 * specified by regionNr.
	 * 
	 * @param regionNr
	 * @param nums
	 */
	private Set<Integer> removeSubRegion(int regionNr, Set<Integer> nums) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == regionNr) {
					nums.remove(table[i][j]);
				}
			}
		}
		return nums;
	}

	private Set<Integer> removeLine(int i, Set<Integer> nums) {
		for (int j = 0; j < grid.length; j++) {
			nums.remove(table[i][j]);
		}
		return nums;

	}

	private Set<Integer> removeColumn(int j, Set<Integer> nums) {
		for (int i = 0; i < grid.length; i++) {
			nums.remove(table[i][j]);
		}
		return nums;
	}

}
