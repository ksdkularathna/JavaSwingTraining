package com.swing.training.tablemodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.swing.training.dtos.PatientDto;
import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;

/**
 * class is used as a table model for Patient table
 * 
 * @author SDhananjaya
 * 
 */
public class PatientTableModel extends AbstractTableModel {

	static final int MAXIMM_MONTH_DISPLAY_LIMIT = 36;
	public String MONTHS_STRING = " months", YEARS_STRING = " years";
	PatientService patientService;
	private String[] tableColumnNames = { "Name", "Age", "Address",
			"Emp. Status" };
	private boolean DEBUG = false;

	private List<PatientDto> patientTableData = new ArrayList<>();

	public PatientTableModel() {

		patientService = new PatientServiceImpl();
		getData();
	}

	/**
	 * fetch the patient list from the DB to tha table
	 */
	public void getData() {

		setPatientTableData(patientService.getAllPatients());
		fireTableDataChanged();
	}

	/**
	 * returns the column count of the table
	 */
	public int getColumnCount() {

		return tableColumnNames.length;
	}

	/**
	 * returns the row count of the table
	 */
	public int getRowCount() {

		return getPatientTableData().size();
	}

	/**
	 * returns the column name of the table
	 */
	public String getColumnName(int col) {

		return tableColumnNames[col];
	}

	/**
	 * returns the cell value of the selected cell in the table
	 */
	public Object getValueAt(int row, int col) {

		PatientDto dto = getPatientTableData().get(row);
		switch (col) {
		case 0:
			return dto.getName();
		case 1:
			return formatAge(dto.getBirthday());
		case 2:
			return dto.getAddress();
		case 3:
			return dto.getEmploymentStatus();
		default:
			return "";
		}
	}

	public boolean isOlderThan(int row) {
		return getColor(getPatientTableData().get(row).getBirthday());
	}

	/**
	 * calculate the age from birthday. if age < 3 years return
	 * "<noOFMonths> Months" else "<noOfYears> Years"
	 * 
	 * @param birthday
	 * @return
	 */
	public String formatAge(Date birthday) {

		Calendar birthdayCalendar = new GregorianCalendar();
		Calendar todayCalendar = new GregorianCalendar();
		birthdayCalendar.setTime(birthday);
		todayCalendar.setTime(new Date());

		int yearDifference = todayCalendar.get(Calendar.YEAR)
				- birthdayCalendar.get(Calendar.YEAR);
		int monthDifference = yearDifference * 12
				+ todayCalendar.get(Calendar.MONTH)
				- birthdayCalendar.get(Calendar.MONTH);

		if (monthDifference < MAXIMM_MONTH_DISPLAY_LIMIT) {

			return monthDifference + MONTHS_STRING;
		} else {

			return yearDifference + YEARS_STRING;
		}
	}

	public boolean getColor(Date birthday) {

		Calendar birthdayCalendar = new GregorianCalendar();
		Calendar todayCalendar = new GregorianCalendar();
		birthdayCalendar.setTime(birthday);
		todayCalendar.setTime(new Date());

		int yearDifference = todayCalendar.get(Calendar.YEAR)
				- birthdayCalendar.get(Calendar.YEAR);
		int monthDifference = yearDifference * 12
				+ todayCalendar.get(Calendar.MONTH)
				- birthdayCalendar.get(Calendar.MONTH);

		if (monthDifference < MAXIMM_MONTH_DISPLAY_LIMIT) {

			return Boolean.FALSE;
		} else {

			return Boolean.TRUE;
		}
	}

	/**
	 * make the cell editable in particular row and col IDs
	 */
	public boolean isCellEditable(int row, int col) {

		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * get the update value in a particular cell and persist it in the DB
	 */
	public void setValueAt(Object value, int row, int col) {

		patientService.editPatientFromTable(value, row, col);
		fireTableCellUpdated(row, col);
	}

	public List<PatientDto> getPatientTableData() {
		return patientTableData;
	}

	public void setPatientTableData(List<PatientDto> patientTableData) {
		this.patientTableData = patientTableData;
	}
}