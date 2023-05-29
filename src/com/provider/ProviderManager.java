package com.provider;

import java.util.Properties;

import org.hibernate.jpa.boot.internal.PersistenceXmlParser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProviderManager {

	/*
	 * In questo script stiamo definendo una serie di metodi statici per gestire
	 * l'EntityManager e l'EntityManagerFactory utilizzati per l'accesso ai dati nel
	 * database. In particolare, viene definita una costante (persistenceUnitName)
	 * che rappresenta il nome dell'unità di persistenza utilizzata, la quale viene
	 * individuata tramite il parser del file persistence.xml. Successivamente,
	 * vengono definiti i metodi getEntityManagerFactory() per ottenere
	 * l'EntityManagerFactory, getEntityManager() per ottenere l'EntityManager e
	 * beginTransaction() per iniziare una nuova transazione con il database,
	 * utilizzando il manager passato come parametro.
	 */

	private static final String persistenceUnitName = PersistenceXmlParser.locatePersistenceUnits(new Properties())
			.get(0).getName();

	public static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public static EntityManager getEntityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}

	public static void beginTransaction(EntityManager em) {
		em.getTransaction().begin();
	}

	// invio della transazione (contesto transazionale)
	public static void commitTransaction(EntityManager em) {
		em.getTransaction().commit();
	}

	// script racchiuso nel try cath nel caso la query non sia andata a buon fine
	public static void rollbackTransaction(EntityManager em) {
		em.getTransaction().rollback();
	}

	// ci permette di chiudere la sessione con EntityManager
	public static void closeTransaction(EntityManager em) {
		em.close();
	}

	// ci permette di chiudere la sessione con EntityManagerFactory
	public static void closeEntityManagerFactory(EntityManagerFactory emf) {
		emf.close();
	}
}
