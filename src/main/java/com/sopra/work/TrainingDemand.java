package com.sopra.work;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TrainingDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;

	@ManyToOne
	Employee employee;

	@ManyToOne
	Training training;

	public Employee findEmployeeById(String name, EntityManager em) {
		return this.employee = em.find(Employee.class, name);
	}

	public Training findTrainingInDatabase(String title, EntityManager em) {
		return this.training = em.find(Training.class, title);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}
