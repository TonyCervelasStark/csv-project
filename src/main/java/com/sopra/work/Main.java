package com.sopra.work;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.sopra.business.FormationService;

public class Main {

	public static void main(String[] args) throws ParseException {
		Reader in;
		try {
			in = new FileReader("sopra-modified.csv");
			try {
				CSVParser records = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(in);
				for (CSVRecord record : records) {

					// Table Employee
					Employee employee = new Employee();
					if (record.get(1).isEmpty()) {
						employee.setAgency("undefined");
					} else {
						employee.setAgency(record.get(1));
					}
					employee.setName(record.get(7));
					employee.setFirstname(record.get(8));

					// continue; passe à la commande suivante
					if (employee.getName().isEmpty() || employee.getFirstname().isEmpty())
						continue;

					FormationService serviceEmployee = new FormationService();
					employee = serviceEmployee.avoidDoubleAndEmptyEmployee(employee);

					// Table Training
					Training training = new Training();
					training.setMonth(Integer.valueOf(record.get(0)));
					if (record.get(2).isEmpty()) {
						training.setNbDays(BigDecimal.ZERO);
					} else {
						try {
							training.setNbDays(new BigDecimal(record.get(2).replaceAll(",", ".")));
						} catch (NumberFormatException h) {
							h.printStackTrace();
						}
					}
					training.setExpectedDate(modifyDate(record.get(3)));
					training.setRealDate(modifyDate(record.get(4)));
					training.setTitle(record.get(5));
					training.setPlace(record.get(6));
					training.setOrganism(record.get(9));
					FormationService serviceTraining = new FormationService();
					training = serviceTraining.avoidDoubleAndEmptyTraining(training);

					// Table de jointure
					TrainingDemand td = new TrainingDemand();
					td.setEmployee(employee);
					td.setTraining(training);
					FormationService serviceTrainingDemand = new FormationService();
					td = serviceTrainingDemand.createTrainingDemand(td);
				}
				// System.out.println(records.getRecordNumber());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EmFactory.close();
	}

	public static Date modifyDate(String text) {

		if (text.isEmpty()) {
			return null;
		}

		Date date;
		Calendar cal = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("dd-MMM", Locale.FRENCH);

		try {
			cal.setTime(format.parse(text));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, 46);
		date = cal.getTime();

		return date;
	}

}
