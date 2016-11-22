package com.sopra.business;

import java.util.List;

import javax.persistence.EntityManager;

import com.sopra.work.*;

public class FormationService {
	
	List<Employee> daDouble;

	@SuppressWarnings("unchecked")
	public boolean avoidDoubleAndEmptyEmployee(String name, String firstname, String agency, EntityManager em){
		
	String query = "SELECT e FROM Employee e WHERE e.name=:name AND e.firstname=:firstname AND e.agency=:agency";
	
	List<Employee> list;
	
	list = (List<Employee>) em
			.createQuery(query)
			.setParameter("name", name)
			.setParameter("firstname", firstname)
			.setParameter("agency", agency)
			.getResultList();
	
	return (list.isEmpty() && !name.isEmpty() && !firstname.isEmpty()) ;
	}
	
	public Employee findEmployeeById(int id, EntityManager em) {
		return em.find(Employee.class, id) ;
	}
	
	@SuppressWarnings("unchecked")
	public boolean avoidDoubleAndEmptyTraining(int month, String title, String place, EntityManager em){
		
		String query = "SELECT t FROM Training t WHERE t.month=:month AND t.title=:title AND t.place=:place";
		
		List<Employee> list;
		
		list = (List<Employee>) em
				.createQuery(query)
				.setParameter("month", month)
				.setParameter("title", title)
				.setParameter("place", place)
				.getResultList();
		
		return (list.isEmpty() && month != 0 && !title.isEmpty()) ;
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean createTrainingDemand(Employee employee, Training training, EntityManager em){
		
		TrainingDemand td = new TrainingDemand();
		
		String query = "SELECT td FROM TrainingDemand td WHERE td.employee=:employee AND td.training=:training";
		
		List<Employee> list;
		
		list = (List<Employee>) em
				.createQuery(query)
				.setParameter("employee", td.getEmployee())
				.setParameter("training", td.getTraining())
				.getResultList();
		
		return (list.isEmpty()) ;
		
	}

}
