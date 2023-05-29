package com.dao;

import java.util.List;

import com.entity.Category;

public interface CategoryDAO {

	// query di inserimento
	public void insertCategory(Category actor);

	// query di update
	public void updateCategory(Category actor);

	// query di remove
	public void deleteCategory(int actorId);

	// query stampa tutte le categorie
	public List<Category> getAllCategory();

}
