package ee.tkasekamp.sudoku.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SudokuReader {

	public List<String> readSudokuResources(String path) throws IOException {
		InputStreamReader reader = new InputStreamReader(this.getClass()
				.getResourceAsStream(path), "UTF-8");
		BufferedReader scanner = new BufferedReader(reader);

		return scanFile(scanner);
	}

	public List<String> readSudoku(String path) throws IOException {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(
				path), "UTF-8");
		BufferedReader scanner = new BufferedReader(reader);

		return scanFile(scanner);
	}

	private List<String> scanFile(BufferedReader scanner) throws IOException {
		List<String> sudokuList = new ArrayList<>();
		String line;
		while ((line = scanner.readLine()) != null) {
			if (!line.equals(""))
				sudokuList.add(line);

		}
		scanner.close();

		return sudokuList;
	}
}
