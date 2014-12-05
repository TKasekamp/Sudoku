package ee.tkasekamp.sudoku.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SudokuReader {

	public List<String> readSudokuResources(String path) throws IOException {
		return scanFile(this.getClass().getResourceAsStream(path));
	}

	public List<String> readSudoku(String path) throws IOException {
		return scanFile(new FileInputStream(path));
	}

	private List<String> scanFile(InputStream in) throws IOException {
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader scanner = new BufferedReader(reader);
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
