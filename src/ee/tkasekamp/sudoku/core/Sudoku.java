package ee.tkasekamp.sudoku.core;

public interface Sudoku {

	public void initialize();

	public void reset();

	public void readTestSudoku();

	public void solveSudoku();

	public int[][] getTable();

	public void readSudoku(String path);

	public void readGrid(String path);
	
	public void generateSudoku();
	
	public void readTestJigsaw();
}
