import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;


public class Main {

	public static void main(String[] args) {

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
					Date date = new Date();

					training.month = Integer.valueOf(record.get(0));

					/*if (record.get(2).isEmpty()) {
						training.nbDays = new BigDecimal(0);
					} else
						training.nbDays = new BigDecimal(record.get(2));*/
					
					training.nbDays = new BigDecimal(0);
					
					training.expectedDate = date;
					training.realDate = date;
					training.title = record.get(5);
					training.place = record.get(6);
					training.organism = record.get(9);

					em.persist(training);

					// try date
					/*
					 * DateTimeFormatter formatter =
					 * DateTimeFormatter.ofPattern("dd-LLLL", Locale.FRANCE);
					 * String text = record.get(3); LocalDate parsedDate =
					 * LocalDate.parse(text, formatter);
					 * 
					 * System.out.println(parsedDate);
					 */

					/*
					 * Training training = new Training();
					 * 
					 * if(record.get(1).isEmpty()){ employee.agency =
					 * "undefined"; } else employee.agency = record.get(1);
					 * 
					 * 
					 * employee.name = record.get(7); employee.firstname=
					 * record.get(8);
					 */

					// display records
					// System.out.println(record);

					/*
					 * // Define elements of the table String mois =
					 * record.get(0); String agence = record.get(1); String
					 * nbJours = record.get(2); String dateAttendue =
					 * record.get(3); String dateReelle = record.get(4); String
					 * intitule = record.get(5); String lieu = record.get(6);
					 * String nom = record.get(7); String prenom =
					 * record.get(8); String organisme = record.get(9);
					 * System.out.println(agence);
					 */

					/*
					 * //Display elements of the table
					 * System.out.println("//////////////////////");
					 * System.out.println(mois); System.out.println(agence);
					 * System.out.println(nbJours);
					 * System.out.println(dateAttendue);
					 * System.out.println(dateReelle);
					 * System.out.println(intitule); System.out.println(lieu);
					 * System.out.println(nom); System.out.println(prenom);
					 * System.out.println(organisme);
					 * System.out.println("//////////////////////");
					 */

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

}
