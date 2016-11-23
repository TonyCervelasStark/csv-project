package com.sopra.business;

import java.util.ArrayList;
import java.util.List;

import com.sopra.work.*;

public class FormationService {

	public Employee avoidDoubleAndEmptyEmployee(Employee employee) {
		return EmFactory.transaction(em -> {
			String query = "SELECT e FROM Employee e WHERE e.name=:name AND e.firstname=:firstname AND e.agency=:agency";

			List<Employee> list;

			list = em.createQuery(query, Employee.class).setParameter("name", employee.getName())
					.setParameter("firstname", employee.getFirstname()).setParameter("agency", employee.getAgency())
					.getResultList();

			if (list.isEmpty()) {
				em.persist(employee);
				return employee;
			} else {
				return list.get(0);
			}
		});
	}

	public Training avoidDoubleAndEmptyTraining(Training training) {
		return EmFactory.transaction(em -> {
			String query = "SELECT t FROM Training t WHERE t.month=:month AND t.title=:title AND t.place=:place";

			List<Training> list = new ArrayList<>();

			list = em.createQuery(query, Training.class).setParameter("month", training.getMonth())
					.setParameter("title", training.getTitle()).setParameter("place", training.getPlace())
					.getResultList();

			if (list.isEmpty() && training.getMonth() != 0 && !training.getTitle().isEmpty()) {
				em.persist(training);
				return training;
			} else {
				return list.get(0);
			}

		});
	}

	public TrainingDemand createTrainingDemand(TrainingDemand td) {
		return EmFactory.transaction(em -> {
			String query = "SELECT tr FROM TrainingDemand tr WHERE tr.employee=:employee AND tr.training=:training";

			List<TrainingDemand> list = em.createQuery(query, TrainingDemand.class)
					.setParameter("employee", td.getEmployee()).setParameter("training", td.getTraining())
					.getResultList();

			if (list.isEmpty()) {
				em.persist(td);
				return td;
			} else {
				return list.get(0);
			}
		});
	}

}
