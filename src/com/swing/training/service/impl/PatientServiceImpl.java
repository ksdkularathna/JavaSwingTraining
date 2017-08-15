/**
 * 
 */
package com.swing.training.service.impl;

import java.util.List;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PatientSearchCriteriaDto;
import com.swing.training.dtos.TrnxResponse;
import com.swing.training.dtos.TrnxStatus;
import com.swing.training.service.PatientService;
import com.swing.training.service.db.PatientDB;


/**
 * @author SDhananjaya
 * 
 */
public class PatientServiceImpl implements PatientService {

	public PatientDB patientDB;

	public PatientServiceImpl() {

		patientDB = new PatientDB();
	}

	@Override
	public TrnxResponse addPatient(PatientDto patientDto) {

		patientDB.addPatient(patientDto);
		return new TrnxResponse(TrnxStatus.SUCCESS, TrnxStatus.PATIENT_ADDED_MSG);
	}

	@Override
	public TrnxResponse editPatientFromTable(Object value, int rowIndex, int colIndex) {

		patientDB.editPatient(getPatientDto(value, rowIndex, colIndex));
		return new TrnxResponse(TrnxStatus.SUCCESS, TrnxStatus.PATIENT_ADDED_MSG);
	}

	@Override
	public PatientDto getPatient(int patientId) {

		return patientDB.getPatient(patientId);
	}

	@Override
	public List<PatientDto> getAllPatients() {

		return patientDB.patientList;
	}

	@Override
	public List<PatientDto> getPatientListFromSearchCriteria(
			PatientSearchCriteriaDto criteriaDto) {

		return patientDB.getListFromCriteria(criteriaDto);
	}

	/**
	 * Utility method for getting updated PatientDto from object value,row and
	 * col indexes
	 */

	private PatientDto getPatientDto(Object value, int row, int col) {

		PatientDto dto = patientDB.patientList.get(row);

		switch (col) {

		case 2:
			dto.setAddress(value.toString());
			break;
		case 3:
			dto.setEmploymentStatus(value.toString());
			break;
		}
		return dto;
	}

	@Override
	public TrnxResponse editPatient(PatientDto patientDto) {
		
		patientDB.editPatient(patientDto);
		return new TrnxResponse(TrnxStatus.SUCCESS, TrnxStatus.PATIENT_EDITED_MSG);
	}
}
