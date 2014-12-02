package ee.tkasekamp.sudoku.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ee.tkasekamp.sudoku.core.Sudoku;
import ee.tkasekamp.sudoku.reader.SudokuParser;

public class MainWindow {

	private JFrame frame;
	private Sudoku sudoku;
	private GamePanel gamePanel;
	private JFileChooser chooser;

	/**
	 * Create the application.
	 */
	public MainWindow(Sudoku sudoku) {
		this.sudoku = sudoku;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.sudoku.initialize();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sudoku");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmA = new JMenuItem("Test sudoku");
		mntmA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.readTestSudoku();
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmA);

		JMenuItem mntmD = new JMenuItem("Test Jigsaw sudoku");
		mntmD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.readTestJigsaw();
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmD);

		JMenuItem mntmC = new JMenuItem("Genereeri sudoku");
		mntmC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.generateSudoku();
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmC);

		JMenuItem mntmLaeTavalineSudoku = new JMenuItem("Lae tavaline sudoku");
		mntmLaeTavalineSudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				loadSudoku();
				sudoku.readGrid(SudokuParser.DEFAULT_GRID);
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmLaeTavalineSudoku);

		JMenuItem mntmLaeJigsawSudoku = new JMenuItem("Lae Jigsaw sudoku");
		mntmLaeJigsawSudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				loadSudoku();
				loadGrid();
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmLaeJigsawSudoku);

		JMenuItem mntmExit = new JMenuItem("Välju");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		mnNewMenu.add(mntmExit);

		JMenu mnNewMenu_1 = new JMenu("Lahenda");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmB = new JMenuItem("Lahenda sudoku");
		mnNewMenu_1.add(mntmB);
		mntmB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudoku.solveSudoku();
				gamePanel.setNumbers(sudoku.getTable());
			}
		});

		gamePanel = new GamePanel();
		frame.getContentPane().add(gamePanel);

		chooser = new JFileChooser();

	}

	public JFrame getFrame() {
		return frame;
	}

	public void reset() {
		sudoku.reset();
		gamePanel.reset();
	}

	public void updateLabel(String newName) {
		gamePanel.setName(newName);
	}

	private void loadSudoku() {
		chooser.setDialogTitle("Vali sudoku fail");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			sudoku.readSudoku(chooser.getSelectedFile().getAbsolutePath());
		}

	}

	public void loadGrid() {
		chooser.setDialogTitle("Vali regioonide fail");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			sudoku.readGrid(chooser.getSelectedFile().getAbsolutePath());
		}
	}

}
