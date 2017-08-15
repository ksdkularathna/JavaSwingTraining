package com.swing.training.listners;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.swing.training.dtos.PatientDto;
import com.swing.training.jpanels.PatientRegestrationPanel;
import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;


/**
 * class is used as a listner for table
 * 
 * @author SDhananjaya
 * 
 */
public class TableListSelectionListener implements ListSelectionListener {

	JTable jTable;
	PatientRegestrationPanel regestrationPanel;
	PatientService patientService;

	public TableListSelectionListener(JTable jTable,
			PatientRegestrationPanel panel) {

		this.regestrationPanel = panel;
		this.jTable = jTable;
		patientService = new PatientServiceImpl();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (jTable.getSelectedRow() > -1) {
			/*
			 * fetch the selected patient details from the DB and populate it in
			 * the input fields
			 */
			regestrationPanel.setPatientDtoToUi(patientService
					.getPatient(jTable.getRowCount() - 1
							- jTable.getSelectedRow()));
		}
	}
}
