package com.sopra.Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.sopra.business.FormationService;
import com.sopra.work.Employee;

public class EmployeeParser {

	public static void employeeParser(EntityManager em) {

		
		Reader in;
		try {
			in = new FileReader("sopra-modified.csv");
			try {
				CSVParser records = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(in);
				for (CSVRecord record : records) {

					Employee employee = new Employee();
					if (record.get(1).isEmpty()) {
						employee.setAgency("undefined");
					} else {
						employee.setAgency(record.get(1));
					}

					employee.setName(record.get(7));
					employee.setFirstname(record.get(8));
					
					FormationService serviceEmployee = new FormationService();
					if (serviceEmployee.avoidDoubleAndEmptyEmployee(employee.getName(), employee.getFirstname(), employee.getAgency(),
							em)) {
						em.persist(employee);
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

}
