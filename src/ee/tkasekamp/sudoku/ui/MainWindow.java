package ee.tkasekamp.sudoku.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ee.tkasekamp.sudoku.core.Sudoku;

public class MainWindow {

	private JFrame frame;
	private Sudoku sudoku;
	private GamePanel gamePanel;

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
			}
		});
		mnNewMenu.add(mntmA);

		JMenuItem mntmB = new JMenuItem("Lahenda sudoku");
		mntmB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudoku.solveSudoku();
			}
		});
		mnNewMenu.add(mntmB);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		mnNewMenu.add(mntmExit);

		gamePanel = new GamePanel();

	}

	public JFrame getFrame() {
		return frame;
	}

	public void reset() {
		sudoku.reset();
		gamePanel.reset();
	}

	public void updateTable(int i, int j, int gridContent) {
		System.out.println(i + ", " + j + ", " + gridContent);
	}

}
