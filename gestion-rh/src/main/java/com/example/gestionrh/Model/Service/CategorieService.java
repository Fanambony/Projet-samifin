package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.CategorieRepository;
import com.example.gestionrh.Model.Entity.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;

	/* -- READ ONE -- */
	public Optional<Categorie> getOne(Object id) { return categorieRepository.findById(id); }

	/* -- READ -- */
	public List<Categorie> getAll() { return categorieRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Categorie Categorie) {  categorieRepository.save(Categorie); }

	/* -- DELETE -- */
	public void delete(Object id) {  categorieRepository.deleteById(id); }

}
