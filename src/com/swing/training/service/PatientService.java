/**
 * 
 */
package com.swing.training.service;

import java.util.List;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PatientSearchCriteriaDto;
import com.swing.training.dtos.TrnxResponse;


/**
 * @author SDhananjaya
 * 
 */
public interface PatientService {

	/**
	 * add a patient
	 * 
	 * @param patientDto
	 * @return
	 */
	public TrnxResponse addPatient(PatientDto patientDto);

	/**
	 * edit updated details from patient table
	 * 
	 * @param value
	 * @param rowIndex
	 *            TODO
	 * @param colIndex
	 *            TODO
	 * @return
	 */
	public TrnxResponse editPatientFromTable(Object value, int rowIndex,
			int colIndex);

	/**
	 * get patient details according to the id
	 * 
	 * @param patientId
	 * @return
	 */
	public PatientDto getPatient(int patientId);

	/**
	 * Get all the patient list
	 * 
	 * @return List<PatientDto>
	 */
	public List<PatientDto> getAllPatients();

	/**
	 * returns the patient list according to the search criteria
	 * 
	 * @return
	 */
	public List<PatientDto> getPatientListFromSearchCriteria(
			PatientSearchCriteriaDto criteriaDto);

	/**
	 * edit and persist PatientDto 
	 * 
	 * @param patientDto
	 * @return
	 */
	public TrnxResponse editPatient(PatientDto patientDto);
}
