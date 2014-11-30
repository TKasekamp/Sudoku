package ee.tkasekamp.sudoku;

import java.awt.EventQueue;

import ee.tkasekamp.sudoku.core.Sudoku;
import ee.tkasekamp.sudoku.core.SudokuImpl;
import ee.tkasekamp.sudoku.ui.MainWindow;

public class App {
	public static MainWindow window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final Sudoku sudoku = new SudokuImpl();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				window = new MainWindow(sudoku);
				window.getFrame().setVisible(true);

			}
		});

	}
}
