package com.swing.training.listners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.TrnxResponse;
import com.swing.training.dtos.TrnxStatus;
import com.swing.training.jpanels.PatientRegestrationPanel;
import com.swing.training.jpanels.TablePanel;
import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;


/**
 * class is used as a action listner for save button
 * 
 * @author SDhananjaya
 * 
 */
public class SaveBtnActionListner implements ActionListener {

	PatientRegestrationPanel regestrationPanel;
	TablePanel tablePanel;
	PatientService patientService;

	public SaveBtnActionListner(PatientRegestrationPanel regestrationPanel,
			TablePanel panel) {

		this.tablePanel = panel;
		this.regestrationPanel = regestrationPanel;
		patientService = new PatientServiceImpl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		PatientDto validatedPatientDto = regestrationPanel
				.validateAndGetPatientDetails();

		TrnxResponse response;
		if (validatedPatientDto != null) {

			if (validatedPatientDto.getPatientId() == null) {

				/* new user */
				response = patientService.addPatient(validatedPatientDto);
				JOptionPane.showMessageDialog(null, response.getMessage());
			} else {

				response = patientService.editPatient(validatedPatientDto);
				JOptionPane.showMessageDialog(null, response.getMessage());
			}

			if (response.getStatus().equals(TrnxStatus.SUCCESS)) {

				/* Update the table */
				tablePanel.getPatientTableModel().getData();
				/* Clear input fields */
				regestrationPanel.clearInputFields();
			}
		}
	}
}