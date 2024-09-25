package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.FamilleRepository;
import com.example.gestionrh.Model.Entity.Famille;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class FamilleService {

	@Autowired
	private FamilleRepository familleRepository;

	/* -- READ ONE -- */
	public Optional<Famille> getOne(Object id) { return familleRepository.findById(id); }

	/* -- READ -- */
	public List<Famille> getAll() { return familleRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Famille Famille) {  familleRepository.save(Famille); }

	/* -- DELETE -- */
	public void delete(Object id) {  familleRepository.deleteById(id); }

	public List<Famille> findByIdEmploye(String idEmploye) {
		return familleRepository.findByIdEmploye(idEmploye);
	}
}