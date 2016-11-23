package com.sopra.work;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmFactory {

	private static EntityManagerFactory instance = Persistence.createEntityManagerFactory("data");

	public EmFactory() {
	}

	public static void close() {
		instance.close();
		instance = null;
	}

	public static <T> T transaction(Function<EntityManager, T> worker) {

		EntityManager em = instance.createEntityManager();
		em.getTransaction().begin();

		T result = worker.apply(em);

		em.getTransaction().commit();
		em.close();

		return result;
	}

	public static void voidTransaction(Consumer<EntityManager> worker) {

		EntityManager em = instance.createEntityManager();
		em.getTransaction().begin();

		worker.accept(em);

		em.getTransaction().commit();
		em.close();
	}

}
