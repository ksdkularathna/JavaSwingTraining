package com.swing.training.tablemodels.cellrenderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.swing.training.tablemodels.PatientTableModel;


/**
 * class is used as a renderer for patient table model
 * 
 * @author SDhananjaya
 * 
 */
public class AgeCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {

		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);
		Boolean isAgeOlderThanThreeMonths = ((PatientTableModel) table
				.getModel()).isOlderThan(row);

		if (isAgeOlderThanThreeMonths) {

			component.setBackground(Color.WHITE);
		} else {

			component.setBackground(Color.RED);
		}

		return component;
	}
}
