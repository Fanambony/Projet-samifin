package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.FiliationRepository;
import com.example.gestionrh.Model.Entity.Filiation;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FiliationService {

	private final FiliationRepository filiationRepository;

	public FiliationService(FiliationRepository filiationRepository) {this.filiationRepository = filiationRepository;}



	/* -- READ ONE -- */
	public Optional<Filiation> getOne(Object id) { return filiationRepository.findById(id); }

	/* -- READ -- */
	public List<Filiation> getAll() { return filiationRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Filiation Filiation) {  filiationRepository.save(Filiation); }

	/* -- DELETE -- */
	public void delete(Object id) {  filiationRepository.deleteById(id); }

}
