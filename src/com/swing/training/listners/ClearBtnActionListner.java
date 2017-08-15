package com.swing.training.listners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.swing.training.jpanels.PatientRegestrationPanel;


/**
 * Class is used as the listner for clear button
 * 
 * @author SDhananjaya
 * 
 */
public class ClearBtnActionListner implements ActionListener {

	PatientRegestrationPanel regestrationPanel;

	public ClearBtnActionListner(PatientRegestrationPanel regestrationPanel) {

		this.regestrationPanel = regestrationPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		regestrationPanel.clearInputFields();
	}
}
