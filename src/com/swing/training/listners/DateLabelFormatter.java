package com.swing.training.listners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * class is used as a date formatter for the JDatepicker
 * 
 * @author SDhananjaya
 * 
 */
public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}
}
