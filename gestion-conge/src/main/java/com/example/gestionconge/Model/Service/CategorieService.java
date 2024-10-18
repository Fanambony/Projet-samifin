package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.CategorieRepository;
import com.example.gestionconge.Model.Entity.Categorie;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class CategorieService {

	private final CategorieRepository categorieRepository;

	public CategorieService(CategorieRepository categorieRepository) {this.categorieRepository = categorieRepository;}



	/* -- READ ONE -- */
	public Optional<Categorie> getOne(Object id) { return categorieRepository.findById(id); }

	/* -- READ -- */
	public List<Categorie> getAll() { return categorieRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Categorie Categorie) {  categorieRepository.save(Categorie); }

	/* -- DELETE -- */
	public void delete(Object id) {  categorieRepository.deleteById(id); }

}
