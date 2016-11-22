package com.sopra.work;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EmFactory {

	private static EntityManagerFactory instance;

	public EmFactory() {
		// "data" was the value of the name attribute of the
		// persistence.xml file.
		instance = Persistence.createEntityManagerFactory("data");
	}

	public EntityManager getEntityManager() {
		return instance.createEntityManager();
	}

	public void close() {
		instance.close();
	}
	
	public static EntityManagerFactory getInstance(){
		if (instance == null){
			instance = Persistence.createEntityManagerFactory("data");
		}
		return instance;
	}
	
	public static EntityManager createEntityManager(){
		return getInstance().createEntityManager();
	}
	

	public static <T> T transaction(Function<EntityManager,T> worker){

		EntityManager em = createEntityManager();
		em.getTransaction().begin();


		T result = worker.apply(em);

		em.getTransaction().commit();
		em.close();

		return result;
	}
	
	public static void voidTransaction(Consumer<EntityManager> worker){

		EntityManager em = createEntityManager();
		em.getTransaction().begin();


		worker.accept(em);

		em.getTransaction().commit();
		em.close();
	}

	
}
