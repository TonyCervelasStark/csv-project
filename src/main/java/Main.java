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


public class Main {

	public static void main(String[] args) throws ParseException {

		EntityManager em = EmFactory.createEntityManager();
		em.getTransaction().begin();

		Reader in;
		try {
			in = new FileReader("sopra-modified.csv");
			try {
				CSVParser records = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(in);

				for (CSVRecord record : records) {

					
					// Create Employee table
					Employee employee = new Employee();

					if (record.get(1).isEmpty()) {
						employee.agency = "undefined";
					} else
						employee.agency = record.get(1);

					employee.name = record.get(7);
					employee.firstname = record.get(8);

					em.persist(employee);
					
					
					// Create Training table
					Training training = new Training();
					
					training.month = Integer.valueOf(record.get(0));

					if (record.get(2).isEmpty()) {
						training.nbDays = new BigDecimal(0);
					} else
						training.nbDays = new BigDecimal(record.get(2).replaceAll(",", "."));
					
					training.expectedDate = modifyDate(record.get(3));
					training.realDate = modifyDate(record.get(4));
					training.title = record.get(5);
					training.place = record.get(6);
					training.organism = record.get(9);

					em.persist(training);
					
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

		em.getTransaction().commit();
		em.close();
		
		EmFactory.getInstance().close();
	}
	
	
public static Date modifyDate(String text){
		
		if(text.isEmpty()){
			return null;
		}
	
		Date date;
		Calendar cal = Calendar.getInstance();
		String modifiedText = text+".";
		DateFormat format = new SimpleDateFormat("dd-MMM", Locale.FRENCH);
		
		try {
			cal.setTime(format.parse(modifiedText));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, 46);
		date = cal.getTime();
		
		return date;
	}
	

}
