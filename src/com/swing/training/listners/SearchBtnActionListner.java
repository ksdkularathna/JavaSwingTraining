package com.swing.training.listners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PatientSearchCriteriaDto;
import com.swing.training.jpanels.PatientSearchPanel;
import com.swing.training.jpanels.TablePanel;
import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;


/**
 * class is used as a action listner for save button
 * 
 * @author SDhananjaya
 *
 */
public class SearchBtnActionListner implements ActionListener {

	private PatientSearchPanel patientSearchPanel;
	private TablePanel tablePanel;
	private PatientService patientService;

	public SearchBtnActionListner(PatientSearchPanel patientSearchPanel,
			TablePanel tablePanel) {

		this.patientSearchPanel = patientSearchPanel;
		this.tablePanel = tablePanel;
		patientService = new PatientServiceImpl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		PatientSearchCriteriaDto criteriaDto = getPatientSearchPanel()
				.validateAndGetPatientSearchCriteria();

		if (criteriaDto != null) {

			List<PatientDto> patientResultSet = patientService
					.getPatientListFromSearchCriteria(criteriaDto);
			/* fetch the new data to the table */
			getTablePanel().getPatientTableModel().setPatientTableData(patientResultSet);
			/* Update the table */
			getTablePanel().getPatientTableModel().fireTableDataChanged();
		}
	}

	public PatientSearchPanel getPatientSearchPanel() {
		return patientSearchPanel;
	}

	public void setPatientSearchPanel(PatientSearchPanel patientSearchPanel) {
		this.patientSearchPanel = patientSearchPanel;
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
}
