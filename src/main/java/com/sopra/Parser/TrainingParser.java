package com.sopra.Parser;

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

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.sopra.business.FormationService;
import com.sopra.work.Training;

public class TrainingParser {

	public static void trainingParser(EntityManager em){
		
		Reader in;
		try {
			in = new FileReader("sopra-modified.csv");
			try {
				CSVParser records = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(in);
				for (CSVRecord record : records) {
					
					Training training = new Training();
					training.setMonth(Integer.valueOf(record.get(0)));
					if (record.get(2).isEmpty()) {
						training.setNbDays(BigDecimal.ZERO);
					} else {
						try {
							training.setNbDays(new BigDecimal(record.get(2).replaceAll(",", ".")));
						}
						catch (NumberFormatException h) {
							h.printStackTrace();
						}
					}
					training.setExpectedDate(modifyDate(record.get(3)));
					training.setRealDate(modifyDate(record.get(4)));
					training.setTitle(record.get(5));
					training.setPlace(record.get(6));
					training.setOrganism(record.get(9));

					FormationService serviceTraining = new FormationService();
					if(serviceTraining.avoidDoubleAndEmptyTraining(training.getMonth(), training.getTitle(), training.getPlace(), em)){
						em.persist(training);
					}
					
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
