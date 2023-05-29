package com.main;

import com.dao.CategoryDAO;
import com.dao.CategoryDAOImpl;
import com.entity.Category;

public class Main {

	public static void main(String[] args) {

		// creiamo un'istanza del dao per testare i vari servizi
		CategoryDAO dao = new CategoryDAOImpl();

		// test dell'insert: la mia nuova category avra' id = 17
		dao.insertCategory(new Category("Comic"));

		dao.insertCategory(new Category("football")); // id = 18
		dao.insertCategory(new Category("rimosso")); // id = 19

		// test update su id 18
		dao.updateCategory(new Category((byte) 18, "VolleyBall"));

		// test rimozione di id = 19
		dao.deleteCategory(19);

		// test read: Select * from actor
		System.out.println("stampa di tutte le categorie\n");
		dao.getAllCategory().forEach(System.out::println);

	}

}
