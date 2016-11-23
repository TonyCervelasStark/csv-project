package com.sopra.business;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;

import com.sopra.work.*;

public class FormationService {
	
	List<Employee> daDouble;

	@SuppressWarnings("unchecked")
	public Employee avoidDoubleAndEmptyEmployee(Employee employee, EntityManager em){
		
	String query = "SELECT e FROM Employee e WHERE e.name=:name AND e.firstname=:firstname AND e.agency=:agency";
	
	List<Employee> list;
	
	list = (List<Employee>) em
			.createQuery(query)
			.setParameter("name", employee.getName())
			.setParameter("firstname", employee.getFirstname())
			.setParameter("agency", employee.getAgency())
			.getResultList();
	
	if (list.isEmpty() && !employee.getName().isEmpty() && !employee.getFirstname().isEmpty()) {
		em.persist(employee);
	}
	
	return employee;
	
	}
	
	public Employee findEmployeeById(int id, EntityManager em) {
		return em.find(Employee.class, id) ;
	}
	
	@SuppressWarnings("unchecked")
	public Training avoidDoubleAndEmptyTraining(Training training, EntityManager em){
		
		String query = "SELECT t FROM Training t WHERE t.month=:month AND t.title=:title AND t.place=:place";
		
		List<Employee> list;
		
		list = (List<Employee>) em
				.createQuery(query)
				.setParameter("month", training.getMonth())
				.setParameter("title", training.getTitle())
				.setParameter("place", training.getPlace())
				.getResultList();
						

		
		if (list.isEmpty() && training.getMonth() != 0 && !training.getTitle().isEmpty()) {
			em.persist(training);

		}
		
		return training ;
		
	}
	
	@SuppressWarnings("unchecked")
	public TrainingDemand createTrainingDemand(TrainingDemand td, EntityManager em){
		
		
		String query = "SELECT td FROM TrainingDemand td WHERE td.employee=:employee AND td.training=:training";
		
		List<TrainingDemand> list;
		
		list = (List<TrainingDemand>) em
				.createQuery(query)
				.setParameter("employee", td.getEmployee())
				.setParameter("training", td.getTraining())
				.getResultList();
		
		if (list.isEmpty()) {
			em.persist(td);

		}
		
		return td ;
		
	}

}
