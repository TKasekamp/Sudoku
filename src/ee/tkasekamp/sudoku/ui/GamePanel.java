package ee.tkasekamp.sudoku.ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1431522427372007837L;
	private JTable table;
	private JLabel lblNiisamaKiri;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		super();
		setLayout(new BorderLayout(0, 0));

		table = new JTable();
		add(table, BorderLayout.CENTER);

		lblNiisamaKiri = new JLabel("Niisama kiri");
		add(lblNiisamaKiri, BorderLayout.SOUTH);

	}

	public void setNumbers(int[][] numbers) {
		table.setModel(new MyTableModel(numbers));

	}

	public void reset() {
		lblNiisamaKiri = new JLabel("Niisama kiri");
	}
	
	public void updateLabel(String newLabel) {
		lblNiisamaKiri.setText(newLabel);
	}

	private class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private int[][] numbers;

		public MyTableModel(int[][] numbers) {
			this.numbers = numbers;
		}

		@Override
		public int getColumnCount() {
			return 9;
		}

		@Override
		public int getRowCount() {
			return 9;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return numbers[rowIndex][columnIndex];
		}

	}

}
