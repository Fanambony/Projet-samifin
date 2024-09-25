package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.FonctionRepository;
import com.example.gestionrh.Model.Entity.Fonction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class FonctionService {

	@Autowired
	private FonctionRepository fonctionRepository;

	/* -- READ ONE -- */
	public Optional<Fonction> getOne(Object id) { return fonctionRepository.findById(id); }

	/* -- READ -- */
	public List<Fonction> getAll() { return fonctionRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Fonction Fonction) {  fonctionRepository.save(Fonction); }

	/* -- DELETE -- */
	public void delete(Object id) {  fonctionRepository.deleteById(id); }

	public List<Fonction> fonctionParDirection(String direction) {
		List<Fonction> fonctions = fonctionRepository.findByIdDirection(direction);
		return fonctions;
	}
}
