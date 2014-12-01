package ee.tkasekamp.sudoku.reader;

import java.io.IOException;
import java.util.List;

public class SudokuParser {
	private SudokuReader reader;
	public static final String SEPARATOR = "-";
	public static final int EMPTY = 0;
	public static final String TEST_SUDOKU = "/testSudoku.txt";
	public static final String DEFAULT_GRID = "/defaultGrid.txt";

	public SudokuParser() {
		reader = new SudokuReader();
	}

	public int[][] parseSudokuResources(String path) throws IOException {
		List<String> lines = reader.readSudokuResources(path);
		return createTable(lines);
	}

	public int[][] parseSudoku(String path) throws IOException {
		List<String> lines = reader.readSudoku(path);
		return createTable(lines);
	}

	/**
	 * Creates a int matrix from a list of lines.
	 * 
	 * @param lines
	 * @return
	 */
	private int[][] createTable(List<String> lines) {
		int[][] n = new int[9][9];
		String number = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] numbers = line.split(" ");
			for (int j = 0; j < numbers.length; j++) {
				number = numbers[j];
				if (number.equals(SEPARATOR)) {
					n[i][j] = EMPTY;
				} else {
					n[i][j] = Integer.parseInt(number);
				}

			}
		}
		return n;
	}

}
