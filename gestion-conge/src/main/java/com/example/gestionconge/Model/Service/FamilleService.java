package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.FamilleRepository;
import com.example.gestionconge.Model.Entity.Famille;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class FamilleService {

	private final FamilleRepository familleRepository;

	public FamilleService(FamilleRepository familleRepository) {this.familleRepository = familleRepository;}



	/* -- READ ONE -- */
	public Optional<Famille> getOne(Object id) { return familleRepository.findById(id); }

	/* -- READ -- */
	public List<Famille> getAll() { return familleRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Famille Famille) {  familleRepository.save(Famille); }

	/* -- DELETE -- */
	public void delete(Object id) {  familleRepository.deleteById(id); }

}
