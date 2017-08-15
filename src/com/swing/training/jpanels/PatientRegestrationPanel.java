package com.swing.training.jpanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.swing.training.dtos.PatientDto;
import com.swing.training.listners.ButtonEnableListner;
import com.swing.training.listners.ClearBtnActionListner;
import com.swing.training.listners.DateLabelFormatter;
import com.swing.training.listners.SaveBtnActionListner;
import com.swing.training.listners.TableListSelectionListener;


public class PatientRegestrationPanel extends JPanel {

	private static final String[] EMP_STATUS_OPTIONS = { "Full time", "Part time",
			"Retired", "Student", "Unemployed", "--Select--" };
	private static final String NULL_STRING = "";
	private static final String NAME_NOT_NULL_MSG = "Please enter the name";
	private static final String BDAY_NOT_NULL_MSG = "Please enter the birthday";
	private static final String BDAY_IS_WRONG_MSG = "Please check your birthday";
	private static final String ADDRESS_NOT_NULL_MSG = "Please enter the address";
	private static final String GENDER_NOT_NULL_MSG = "Please select the gender";
	private static final String EMPPLOYMENT_STATUS_NOT_NULL_MSG = "Please select employement status";
	private static final String MALE = "Male";
	private static final int MIN_ADDRESS_CHARACTOR_COUNT = 10;
	private static final int DEFAULT_INDEX_OF_SELECTION = 5;

	private JLabel nameLabel, phoneNumLabel, birthDayLabel, genderLabel,
			addressLabel, empStatusLabel;
	private JTextField nameText, phoneNumText, patientIdText;
	private JRadioButton maleRatioBtn, femaleRatioBtn;
	private JTextArea addressText;
	private JComboBox empStatusCombo;
	private JButton clearBtn, saveBtn;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl birthdayPicker;
	private ButtonGroup genderButtonGroup;
	private UtilDateModel utilDateModel;
	private TablePanel tablePanel;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public PatientRegestrationPanel(TablePanel tablePanel) {

		this.tablePanel = tablePanel;
		setLayout(new GridBagLayout());
		setPanelComponents();
		disableSaveBtn();
		addListSelectionListenerToTable();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		setNameLabel(new JLabel("Name *"));
		add(getNameLabel(), gbConstraints);

		setPhoneNumLabel(new JLabel("Phone Number "));
		gbConstraints.gridy++;
		add(getPhoneNumLabel(), gbConstraints);

		setBirthDayLabel(new JLabel("Birthday *"));
		gbConstraints.gridy++;
		add(getBirthDayLabel(), gbConstraints);

		setGenderLabel(new JLabel("Gender *"));
		gbConstraints.gridy++;
		add(getGenderLabel(), gbConstraints);

		setAddressLabel(new JLabel("Address "));
		gbConstraints.gridy++;
		add(getAddressLabel(), gbConstraints);

		setEmpStatusLabel(new JLabel("Employment Status"));
		gbConstraints.gridy++;
		add(getEmpStatusLabel(), gbConstraints);

		setNameText(new JTextField(36));
		gbConstraints.gridx++;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		add(getNameText(), gbConstraints);

		setPhoneNumText(new JTextField(36));
		gbConstraints.gridy++;
		gbConstraints.gridwidth = 2;
		add(getPhoneNumText(), gbConstraints);

		setUtilDateModel(new UtilDateModel());
		getUtilDateModel().setSelected(Boolean.TRUE);
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		setDatePanel(new JDatePanelImpl(utilDateModel, properties));
		setBirthdayPicker(new JDatePickerImpl(datePanel,
				new DateLabelFormatter()));
		// birthdayPicker.set
		gbConstraints.gridy++;
		gbConstraints.gridwidth = 1;
		add(getBirthdayPicker(), gbConstraints);

		setMaleRatioBtn(new JRadioButton("Male"));
		gbConstraints.gridy++;
		gbConstraints.gridwidth = 1;
		getMaleRatioBtn().setActionCommand("Male");
		add(getMaleRatioBtn(), gbConstraints);

		setFemaleRatioBtn(new JRadioButton("Female"));
		gbConstraints.gridx++;
		gbConstraints.gridwidth = 1;
		getFemaleRatioBtn().setActionCommand("Female");
		add(getFemaleRatioBtn(), gbConstraints);

		setGenderButtonGroup(new ButtonGroup());
		getGenderButtonGroup().add(maleRatioBtn);
		getGenderButtonGroup().add(femaleRatioBtn);

		setAddressText(new JTextArea(5, 20));
		gbConstraints.gridx--;
		gbConstraints.gridy++;
		gbConstraints.gridwidth = 2;
		add(getAddressText(), gbConstraints);

		setEmpStatusCombo(new JComboBox(EMP_STATUS_OPTIONS));
		getEmpStatusCombo().setSelectedIndex(DEFAULT_INDEX_OF_SELECTION);
		gbConstraints.gridy++;
		gbConstraints.gridwidth = 1;
		add(getEmpStatusCombo(), gbConstraints);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		setClearBtn(new JButton("Clear"));
		GridBagConstraints gbConstraintsCancelBtn = new GridBagConstraints();
		gbConstraintsCancelBtn.gridx = 0;
		gbConstraintsCancelBtn.gridy = 0;
		gbConstraintsCancelBtn.weightx = 1;
		gbConstraintsCancelBtn.weighty = 1;
		gbConstraintsCancelBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsCancelBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsCancelBtn.fill=GridBagConstraints.NONE;
		buttonPanel.add(getClearBtn(), gbConstraintsCancelBtn);

		getClearBtn().addActionListener(new ClearBtnActionListner(this));

		setSaveBtn(new JButton("Save"));
		GridBagConstraints gbConstraintsSaveBtn = new GridBagConstraints();
		gbConstraintsSaveBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsSaveBtn.fill = GridBagConstraints.NONE;
		gbConstraintsSaveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsSaveBtn.gridx = 1;
		gbConstraintsSaveBtn.gridy = 0;
		gbConstraintsSaveBtn.weightx = 0;
		gbConstraintsSaveBtn.weighty = 1; 
		buttonPanel.add(getSaveBtn(), gbConstraintsSaveBtn);
		getSaveBtn().addActionListener(
				new SaveBtnActionListner(this, getTablePanel()));

		gbConstraints.anchor = GridBagConstraints.EAST;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 6;
		gbConstraints.fill=GridBagConstraints.HORIZONTAL;
		gbConstraints.gridwidth = 3;
		gbConstraints.gridheight = 1;
		
		add(buttonPanel, gbConstraints);

		setPatientIdText(new JTextField(5));
		gbConstraints.gridy++;
		getPatientIdText().setVisible(Boolean.FALSE);
		add(getPatientIdText(), gbConstraints);
	}

	public void disableSaveBtn() {

		final ButtonModel saveBtnModel = getSaveBtn().getModel();
		Document nameDocument = getNameText().getDocument();
		Document phoneNumDocument = getPhoneNumText().getDocument();
		Document addressDocument = getAddressText().getDocument();

		ButtonEnableListner buttonEnablement = new ButtonEnableListner(
				saveBtnModel);
		buttonEnablement.addDocument(nameDocument);
		buttonEnablement.addDocument(phoneNumDocument);
		buttonEnablement.addDocument(addressDocument);
	}

	/**
	 * returns validated patient details
	 * 
	 * @return
	 */
	public PatientDto validateAndGetPatientDetails() {

		PatientDto patientDto = new PatientDto();

		if (getNameText().getText().trim().equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, NAME_NOT_NULL_MSG);
			getNameText().requestFocus();
			return null;
		} else {

			patientDto.setName(getNameText().getText().trim());
		}

		String birthday = getBirthdayPicker().getJFormattedTextField()
				.getText().trim();
		if (birthday.equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, BDAY_NOT_NULL_MSG);
			getBirthdayPicker().requestFocus();
			return null;
		} else {

			int day = Integer.parseInt(birthday.substring(0, 2));
			int month = Integer.parseInt(birthday.substring(3, 5));
			int year = Integer.parseInt(birthday.substring(6));

			Calendar calendarBirthday = Calendar.getInstance();
			Calendar calendarToday = Calendar.getInstance();
			calendarToday.setTime(new Date());
			calendarBirthday.set(year, month - 1, day);

			if (calendarBirthday.after(calendarToday)) {

				JOptionPane.showMessageDialog(null, BDAY_IS_WRONG_MSG);
				getBirthdayPicker().requestFocus();
				return null;
			} else {

				patientDto.setBirthday(calendarBirthday.getTime());
			}
		}

		if (getGenderButtonGroup().isSelected(getMaleRatioBtn().getModel())) {

			patientDto.setGender(getGenderButtonGroup().getSelection()
					.getActionCommand());
		} else if (getGenderButtonGroup().isSelected(
				getFemaleRatioBtn().getModel())) {

			patientDto.setGender(getGenderButtonGroup().getSelection()
					.getActionCommand());
		} else {

			JOptionPane.showMessageDialog(null, GENDER_NOT_NULL_MSG);
			return null;
		}

		if (getEmpStatusCombo().getSelectedIndex() == DEFAULT_INDEX_OF_SELECTION) {

			JOptionPane
					.showMessageDialog(null, EMPPLOYMENT_STATUS_NOT_NULL_MSG);
			getEmpStatusCombo().requestFocus();
			return null;
		} else {

			patientDto.setEmploymentStatus(getEmpStatusCombo()
					.getSelectedItem().toString());
		}

		String address = getAddressText().getText().trim();
		if (address.equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, ADDRESS_NOT_NULL_MSG);
			getAddressText().requestFocus();
			return null;
		} else if (address.length() < MIN_ADDRESS_CHARACTOR_COUNT) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane
					.showConfirmDialog(
							null,
							"Address is less than 10 charactors. Would you like continue?",
							"Warning", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {

				patientDto.setAddress(address);
			} else {

				getAddressText().requestFocus();
				return null;
			}
		} else {

			patientDto.setAddress(address);
		}

		patientDto.setPhoneNumber(getPhoneNumText().getText().trim());

		String patientId = getPatientIdText().getText();
		if (!patientId.equals(NULL_STRING)) {

			patientDto.setPatientId(getPatientIdText().getText());
		}

		return patientDto;
	}

	/**
	 * adds the list selection listener to the table
	 */
	public void addListSelectionListenerToTable() {

		getTablePanel()
				.getTable()
				.getSelectionModel()
				.addListSelectionListener(
						new TableListSelectionListener(getTablePanel()
								.getTable(), this));
	}

	/**
	 * set the input PatientDto details to input fields
	 * 
	 * @param dto
	 */
	public void setPatientDtoToUi(PatientDto dto) {

		getNameText().setText(dto.getName());
		getPhoneNumText().setText(dto.getPhoneNumber());
		getAddressText().setText(dto.getAddress());
		getEmpStatusCombo().setSelectedItem(dto.getEmploymentStatus());
		if (dto.getGender().equals(MALE))
			getGenderButtonGroup().setSelected(getMaleRatioBtn().getModel(),
					Boolean.TRUE);
		else
			getGenderButtonGroup().setSelected(getFemaleRatioBtn().getModel(),
					Boolean.TRUE);
		Calendar birthday = Calendar.getInstance();
		birthday.setTime(dto.getBirthday());
		getBirthdayPicker().getModel().setDay(birthday.get(Calendar.DATE));
		getBirthdayPicker().getModel().setMonth(birthday.get(Calendar.MONTH));
		getBirthdayPicker().getModel().setYear(birthday.get(Calendar.YEAR));
		getPatientIdText().setText(dto.getPatientId());
	}

	/**
	 * clear the input fields
	 */
	public void clearInputFields() {

		getNameText().setText(NULL_STRING);
		getPhoneNumText().setText(NULL_STRING);
		getBirthdayPicker().getJFormattedTextField().setText(null);
		getUtilDateModel().setValue(new Date());
		getAddressText().setText(NULL_STRING);
		getGenderButtonGroup().clearSelection();
		getEmpStatusCombo().setSelectedIndex(DEFAULT_INDEX_OF_SELECTION);
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getPhoneNumLabel() {
		return phoneNumLabel;
	}

	public void setPhoneNumLabel(JLabel phoneNumLabel) {
		this.phoneNumLabel = phoneNumLabel;
	}

	public JLabel getBirthDayLabel() {
		return birthDayLabel;
	}

	public void setBirthDayLabel(JLabel birthDayLabel) {
		this.birthDayLabel = birthDayLabel;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JLabel getAddressLabel() {
		return addressLabel;
	}

	public void setAddressLabel(JLabel addressLabel) {
		this.addressLabel = addressLabel;
	}

	public JLabel getEmpStatusLabel() {
		return empStatusLabel;
	}

	public void setEmpStatusLabel(JLabel empStatusLabel) {
		this.empStatusLabel = empStatusLabel;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}

	public JTextField getPhoneNumText() {
		return phoneNumText;
	}

	public void setPhoneNumText(JTextField phoneNumText) {
		this.phoneNumText = phoneNumText;
	}

	public JTextField getPatientIdText() {
		return patientIdText;
	}

	public void setPatientIdText(JTextField patientIdText) {
		this.patientIdText = patientIdText;
	}

	public JRadioButton getMaleRatioBtn() {
		return maleRatioBtn;
	}

	public void setMaleRatioBtn(JRadioButton maleRatioBtn) {
		this.maleRatioBtn = maleRatioBtn;
	}

	public JRadioButton getFemaleRatioBtn() {
		return femaleRatioBtn;
	}

	public void setFemaleRatioBtn(JRadioButton femaleRatioBtn) {
		this.femaleRatioBtn = femaleRatioBtn;
	}

	public JTextArea getAddressText() {
		return addressText;
	}

	public void setAddressText(JTextArea addressText) {
		this.addressText = addressText;
	}

	public JComboBox getEmpStatusCombo() {
		return empStatusCombo;
	}

	public void setEmpStatusCombo(JComboBox empStatusCombo) {
		this.empStatusCombo = empStatusCombo;
	}

	public JButton getClearBtn() {
		return clearBtn;
	}

	public void setClearBtn(JButton clearBtn) {
		this.clearBtn = clearBtn;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	public JDatePanelImpl getDatePanel() {
		return datePanel;
	}

	public void setDatePanel(JDatePanelImpl datePanel) {
		this.datePanel = datePanel;
	}

	public JDatePickerImpl getBirthdayPicker() {
		return birthdayPicker;
	}

	public void setBirthdayPicker(JDatePickerImpl birthdayPicker) {
		this.birthdayPicker = birthdayPicker;
	}

	public ButtonGroup getGenderButtonGroup() {
		return genderButtonGroup;
	}

	public void setGenderButtonGroup(ButtonGroup genderButtonGroup) {
		this.genderButtonGroup = genderButtonGroup;
	}

	public UtilDateModel getUtilDateModel() {
		return utilDateModel;
	}

	public void setUtilDateModel(UtilDateModel utilDateModel) {
		this.utilDateModel = utilDateModel;
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
}
