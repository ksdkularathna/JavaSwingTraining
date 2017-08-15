package com.swing.training.service.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PatientSearchCriteriaDto;


public class PatientDB {

	public static final String NULL_STRING = "";
	public static int PATIENT_ID = 0;
	public static List<PatientDto> patientList = new ArrayList<>();

	/**
	 * add the patient dto to the DB
	 * 
	 * @param dto
	 */
	public void addPatient(PatientDto dto) {

		dto.setPatientId(Integer.toString(PATIENT_ID++));
		patientList.add(0, dto);
		printData();
	}

	/**
	 * get patient details from array list id
	 * 
	 * @param patientId
	 * @return
	 */
	public PatientDto getPatient(int patientId) {

		for (PatientDto dto : patientList) {

			if (Integer.parseInt(dto.getPatientId()) == patientId) {

				return dto;
			}
		}
		return null;
	}

	/**
	 * edit patient details in the DB
	 * 
	 * @param dto
	 */
	public void editPatient(PatientDto dto) {

		int i = 0;
		for (PatientDto patientDto : patientList) {

			if (patientDto.getPatientId().equals(dto.getPatientId())) {

				patientList.remove(i);
				patientList.add(i, dto);
				printData();
				return;
			}
			i++;
		}
	}

	/**
	 * get patient list according to the search criteria
	 * 
	 * @param criteriaDto
	 * @return
	 */
	public List<PatientDto> getPatientListFromSearchCriteria(
			PatientSearchCriteriaDto criteriaDto) {

		return getListFromCriteria(criteriaDto);
	}

	/**
	 * Utility method for searching the patients in the list
	 * 
	 * @param criteriaDto
	 * @return
	 */
	public List<PatientDto> getListFromCriteria(
			PatientSearchCriteriaDto criteriaDto) {

		String nameCriteria = criteriaDto.getName();
		int birthdayCriteria = criteriaDto.getBirthday();
		String genderCriteria = criteriaDto.getGender();

		List<PatientDto> patientDtos = new ArrayList<>();

		for (PatientDto patientDto : patientList) {

			if (!nameCriteria.equals(NULL_STRING) && birthdayCriteria != 0
					&& genderCriteria != null) {

				if (patientDto.getName().indexOf(nameCriteria) > -1
						&& compareDate(patientDto.getBirthday(),
								birthdayCriteria)
						&& patientDto.getGender().equals(genderCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}
			}

			else if (!nameCriteria.equals(NULL_STRING) && birthdayCriteria != 0) {

				if (patientDto.getName().indexOf(nameCriteria) > -1
						&& compareDate(patientDto.getBirthday(),
								birthdayCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}
			} else if (birthdayCriteria != 0 && genderCriteria != null) {

				if (compareDate(patientDto.getBirthday(), birthdayCriteria)
						&& patientDto.getGender().equals(genderCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}
			} else if (!nameCriteria.equals(NULL_STRING)
					&& genderCriteria != null) {

				if (patientDto.getName().indexOf(nameCriteria) > -1
						&& patientDto.getGender().equals(genderCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}

			} else if (!nameCriteria.equals(NULL_STRING)) {

				if (patientDto.getName().indexOf(nameCriteria) > -1) {

					patientDtos.add(patientDto);
					continue;
				}
			} else if (birthdayCriteria != 0) {

				if (compareDate(patientDto.getBirthday(), birthdayCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}
			} else if (genderCriteria != null) {

				if (patientDto.getGender().equals(genderCriteria)) {

					patientDtos.add(patientDto);
					continue;
				}
			}
		}
		return patientDtos;
	}

	/**
	 * Compare two dates day1 and day2 only by year and returns TRUE if equals
	 * otherwise FALSE.
	 * 
	 * @param birthday
	 * @param birthdayEnteredInCriteria
	 * @return
	 */
	public boolean compareDate(Date day1, int year) {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(day1);

		if (calendar1.get(Calendar.YEAR) == year) {

			return Boolean.TRUE;
		} else {

			return Boolean.FALSE;
		}
	}

	/**
	 * Utility method for printing current patient list
	 */
	public void printData() {

		for (PatientDto dto : patientList) {

			System.out.println(dto.toString());
		}
	}
}
