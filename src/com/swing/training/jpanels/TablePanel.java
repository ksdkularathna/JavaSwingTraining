package com.swing.training.jpanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.swing.training.listners.SaveBtnActionListner;
import com.swing.training.tablemodels.PatientTableModel;
import com.swing.training.tablemodels.cellrenderers.AgeCellRenderer;


public class TablePanel extends JPanel {

	public static final String SPACE_STRING = " ", MONTHS_STRING = "months";
	public static final int MINIMUM_AGE = 3;

	private JTable table;
	private PatientTableModel patientTableModel;
	
	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public TablePanel(PatientTableModel patientTableModel) {

		this.patientTableModel = patientTableModel;
		setLayout(new GridBagLayout());
		setPanelComponents();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		
		table = new JTable();
		table.setModel(getPatientTableModel());
		table.setDefaultRenderer(Object.class, new AgeCellRenderer());
		getTable().setPreferredScrollableViewportSize(new Dimension(518, 70));
		JScrollPane scrollPane = new JScrollPane(getTable());
		add(scrollPane, gbConstraints);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public PatientTableModel getPatientTableModel() {
		return patientTableModel;
	}

	public void setPatientTableModel(PatientTableModel patientTableModel) {
		this.patientTableModel = patientTableModel;
	}
}
