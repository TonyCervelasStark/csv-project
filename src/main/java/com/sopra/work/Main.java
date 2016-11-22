package com.sopra.work;

import java.text.ParseException;

import javax.persistence.EntityManager;

import com.sopra.Parser.EmployeeParser;
import com.sopra.Parser.TrainingParser;

public class Main {

	public static void main(String[] args) throws ParseException {

		EntityManager em = EmFactory.createEntityManager();
		//em.getTransaction().begin();

		EmployeeParser.employeeParser(em);
		TrainingParser.trainingParser(em);
		em.getTransaction().commit();
		
		TrainingDemand td = new TrainingDemand();


		em.close();
		EmFactory.getInstance().close();
	}

}
