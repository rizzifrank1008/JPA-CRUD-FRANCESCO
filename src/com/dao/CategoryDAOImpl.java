package com.dao;

import java.util.List;

import com.entity.Category;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CategoryDAOImpl implements CategoryDAO {

	/*
	 * l'EntityManagerFactory e l'EntityManager sono fondamentali per l'interazione
	 * tra l'applicazione e il database, e sono utilizzati per gestire le operazioni
	 * di lettura/scrittura dei dati e la comunicazione tra queste due entità.
	 * 
	 */
	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public void insertCategory(Category category) {
		initRoutine();
		em.persist(category);
		closeRoutine();
	}

	@Override
	public void updateCategory(Category category) {
		initRoutine();
		em.merge(category);
		closeRoutine();
	}

	/*
	 * nella delete andiamo a fare il remove in relazione ad una funzione che prima
	 * ci trova la categoria in questione da rimuovere by ID
	 */
	@Override
	public void deleteCategory(int categoryId) {
		initRoutine();
		em.remove(em.find(Category.class, categoryId));
		closeRoutine();
	}

	@Override
	public List<Category> getAllCategory() {
		initRoutine();
		List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
		closeRoutine();
		return categories;
	}

	/*
	 * racchiudo i metodi del provider manger in due metodi uno di inizio e uno di
	 * chiusura in modo da fare ogni volta una transazione per query
	 */
	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closeRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita: eseguo il rollback.");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}

}
