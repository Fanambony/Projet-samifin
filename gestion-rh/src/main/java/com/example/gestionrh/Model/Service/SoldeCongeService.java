package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.SoldeCongeRepository;
import com.example.gestionrh.Model.Entity.SoldeConge;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class SoldeCongeService {

	private final SoldeCongeRepository soldeCongeRepository;

	public SoldeCongeService(SoldeCongeRepository soldeCongeRepository) {this.soldeCongeRepository = soldeCongeRepository;}



	/* -- READ ONE -- */
	public Optional<SoldeConge> getOne(Object id) { return soldeCongeRepository.findById(id); }

	/* -- READ -- */
	public List<SoldeConge> getAll() { return soldeCongeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(SoldeConge SoldeConge) {  soldeCongeRepository.save(SoldeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  soldeCongeRepository.deleteById(id); }

}
