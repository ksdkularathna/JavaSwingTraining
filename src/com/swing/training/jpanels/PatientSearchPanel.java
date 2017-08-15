package com.swing.training.jpanels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.swing.training.dtos.PatientSearchCriteriaDto;
import com.swing.training.listners.DateLabelFormatter;
import com.swing.training.listners.SearchBtnActionListner;


public class PatientSearchPanel extends JPanel {

	private static final String NULL_STRING = "";
	private static final String NAME_NOT_NULL_MSG = "Please enter the name";
	private static final String BDAY_NOT_NULL_MSG = "Please enter the birthday";
	private static final String BDAY_IS_WRONG_MSG = "Please check your birthday";
	private static final String BIRTH_YEAR_IS_WRONG = "Please check your birthyear";
	private static final String GENDER_NOT_NULL_MSG = "Please select the gender";
	private static final String MALE = "Male";

	private JLabel nameLabel, birthYearLabel;
	private JTextField nameText, birthYearText;
	private JRadioButton maleRatioBtn, femaleRatioBtn;
	private JButton searchBtn;

	private ButtonGroup genderButtonGroup;

	private GridBagConstraints gbConstraints = new GridBagConstraints();
	public TablePanel tablePanel;

	public PatientSearchPanel(TablePanel tablePanel) {

		this.tablePanel = tablePanel;
		setLayout(new GridBagLayout());
		setPanelComponents();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		setNameLabel(new JLabel("Name "));
		add(getNameLabel(), gbConstraints);

		setNameText(new JTextField(5));
		gbConstraints.gridx++;
		add(getNameText(), gbConstraints);

		setBirthYearLabel(new JLabel("Birth year "));
		gbConstraints.gridx++;
		add(getBirthYearLabel(), gbConstraints);

		setBirthYearText(new JTextField(10));
		gbConstraints.gridx++;
		add(getBirthYearText(), gbConstraints);

		setMaleRatioBtn(new JRadioButton("Male"));
		gbConstraints.gridx++;
		getMaleRatioBtn().setActionCommand("Male");
		add(getMaleRatioBtn(), gbConstraints);

		setFemaleRatioBtn(new JRadioButton("Female"));
		gbConstraints.gridx++;
		getFemaleRatioBtn().setActionCommand("Female");
		add(getFemaleRatioBtn(), gbConstraints);

		setGenderButtonGroup(new ButtonGroup());
		getGenderButtonGroup().add(maleRatioBtn);
		getGenderButtonGroup().add(femaleRatioBtn);
		getMaleRatioBtn().setSelected(Boolean.TRUE);

		setSearchBtn(new JButton("Search"));
		gbConstraints.gridx++;
		add(getSearchBtn(), gbConstraints);
		getSearchBtn().addActionListener(
				new SearchBtnActionListner(this, tablePanel));
	}

	/**
	 * validate the input fields and returns search criteria object
	 * 
	 * @return
	 */
	public PatientSearchCriteriaDto validateAndGetPatientSearchCriteria() {

		PatientSearchCriteriaDto criteriaDto = new PatientSearchCriteriaDto();

		criteriaDto.setName(getNameText().getText().trim());

		String birthYear = getBirthYearText().getText().trim();
		if (!birthYear.equals(NULL_STRING)) {

			if (!birthYear.matches("[0-9]+")) {

				JOptionPane.showMessageDialog(null, BIRTH_YEAR_IS_WRONG);
				getBirthYearText().requestFocus();
				return null;
			} else {

				criteriaDto.setBirthday(Integer.parseInt(birthYear));
			}
		}

		String gender = getGenderButtonGroup().getSelection()
				.getActionCommand().trim();
		criteriaDto.setGender(gender);

		return criteriaDto;
	}

	public JLabel getNameLabel() {
		if (nameLabel == null) {

			nameLabel = new JLabel();
		}
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getBirthYearLabel() {
		return birthYearLabel;
	}

	public void setBirthYearLabel(JLabel birthYearLabel) {
		this.birthYearLabel = birthYearLabel;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}

	public JTextField getBirthYearText() {
		return birthYearText;
	}

	public void setBirthYearText(JTextField birthYearText) {
		this.birthYearText = birthYearText;
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

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}

	public ButtonGroup getGenderButtonGroup() {
		return genderButtonGroup;
	}

	public void setGenderButtonGroup(ButtonGroup genderButtonGroup) {
		this.genderButtonGroup = genderButtonGroup;
	}
}
