package ee.tkasekamp.sudoku.ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1431522427372007837L;
	private JTable table;
	private int[][] regions;

	/* Colors */
	private final Color REGION_1;
	private final Color REGION_2;
	private final Color REGION_3;
	private final Color REGION_4;
	private final Color REGION_5;
	private final Color REGION_6;
	private final Color REGION_7;
	private final Color REGION_8;
	private final Color REGION_9;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		super();
		setLayout(new BorderLayout(0, 0));

		table = new JTable();
		add(table, BorderLayout.CENTER);
		table.setRowHeight(40);

		REGION_1 = new Color(111, 100, 202);
		REGION_2 = new Color(86, 207, 135);
		REGION_3 = new Color(224, 94, 160);
		REGION_4 = new Color(55, 185, 108);
		REGION_5 = new Color(211, 63, 138);
		REGION_6 = new Color(147, 138, 223);
		REGION_7 = new Color(236, 132, 185);
		REGION_8 = new Color(82, 70, 179);
		REGION_9 = new Color(126, 226, 166);
	}

	public void setNumbers(int[][] numbers) {
		table.setModel(new MyTableModel(numbers));
		TableColumn column = null;
		for (int i = 0; i < 9; i++) {
			column = table.getColumnModel().getColumn(i);
			column.setCellRenderer(new StatusColumnCellRenderer());

		}

	}

	public void setRegion(int[][] regions) {
		this.regions = regions;
	}

	public void reset() {
		regions = null;
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

	/**
	 * From http://stackoverflow.com/questions/5673430/java-jtable-change-cell-
	 * color
	 */
	private class StatusColumnCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = -7315543733834005667L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int col) {

			// Cells are by default rendered as a JLabel.
			JLabel label = (JLabel) super.getTableCellRendererComponent(table,
					value, false, false, row, col);

			setHorizontalAlignment(JLabel.CENTER);
			String labelText = label.getText();
			label.setText("<html><h2>" + labelText + "</h2></html>");
			
			int regionNr = regions[row][col];
			switch (regionNr) {
			case 1:
				label.setBackground(REGION_1);
				break;
			case 2:
				label.setBackground(REGION_2);
				break;
			case 3:
				label.setBackground(REGION_3);
				break;
			case 4:
				label.setBackground(REGION_4);
				break;
			case 5:
				label.setBackground(REGION_5);
				break;
			case 6:
				label.setBackground(REGION_6);
				break;
			case 7:
				label.setBackground(REGION_7);
				break;
			case 8:
				label.setBackground(REGION_8);
				break;
			case 9:
				label.setBackground(REGION_9);
				break;

			default:
				break;
			}

			// Return the JLabel which renders the cell.
			return label;

		}
	}
}
