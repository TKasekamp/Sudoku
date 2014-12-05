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

import javax.swing.JSeparator;

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
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Messages.getString("MainWindow.0")); //$NON-NLS-1$

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu(Messages.getString("MainWindow.1")); //$NON-NLS-1$
		menuBar.add(mnNewMenu);

		JMenuItem mntmA = new JMenuItem(Messages.getString("MainWindow.2")); //$NON-NLS-1$
		mntmA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.readTestSudoku();
				gamePanel.setRegion(sudoku.getGrid());
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmA);

		JMenuItem mntmD = new JMenuItem(Messages.getString("MainWindow.3")); //$NON-NLS-1$
		mntmD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.readTestJigsaw();
				gamePanel.setRegion(sudoku.getGrid());
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmD);

		JMenuItem mntmC = new JMenuItem(Messages.getString("MainWindow.4")); //$NON-NLS-1$
		mntmC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				sudoku.generateSudoku();
				gamePanel.setRegion(sudoku.getGrid());
				gamePanel.setNumbers(sudoku.getTable());
			}
		});

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		mnNewMenu.add(mntmC);

		JMenuItem mntmLaeTavalineSudoku = new JMenuItem(
				Messages.getString("MainWindow.5")); //$NON-NLS-1$
		mntmLaeTavalineSudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				loadSudoku();
				gamePanel.setRegion(sudoku.getGrid());
				gamePanel.setNumbers(sudoku.getTable());
			}
		});

		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		mnNewMenu.add(mntmLaeTavalineSudoku);

		JMenuItem mntmLaeJigsawSudoku = new JMenuItem(
				Messages.getString("MainWindow.6")); //$NON-NLS-1$
		mntmLaeJigsawSudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				loadSudoku();
				loadGrid();
				gamePanel.setRegion(sudoku.getGrid());
				gamePanel.setNumbers(sudoku.getTable());
			}
		});
		mnNewMenu.add(mntmLaeJigsawSudoku);

		JMenuItem mntmExit = new JMenuItem(Messages.getString("MainWindow.7")); //$NON-NLS-1$
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		mnNewMenu.add(mntmExit);

		JMenu mnNewMenu_1 = new JMenu(Messages.getString("MainWindow.8")); //$NON-NLS-1$
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmB = new JMenuItem(Messages.getString("MainWindow.9")); //$NON-NLS-1$
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

	private void loadSudoku() {
		chooser.setDialogTitle(Messages.getString("MainWindow.10")); //$NON-NLS-1$
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			sudoku.readSudoku(chooser.getSelectedFile().getAbsolutePath());
		}

	}

	public void loadGrid() {
		chooser.setDialogTitle(Messages.getString("MainWindow.11")); //$NON-NLS-1$
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			sudoku.readGrid(chooser.getSelectedFile().getAbsolutePath());
		}
	}

}
