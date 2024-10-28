package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.SoldeFinAnneeRepository;
import com.example.gestionrh.Model.Entity.SoldeFinAnnee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class SoldeFinAnneeService {

	private final SoldeFinAnneeRepository soldeFinAnneeRepository;

	public SoldeFinAnneeService(SoldeFinAnneeRepository soldeFinAnneeRepository) {this.soldeFinAnneeRepository = soldeFinAnneeRepository;}



	/* -- READ ONE -- */
	public Optional<SoldeFinAnnee> getOne(Object id) { return soldeFinAnneeRepository.findById(id); }

	/* -- READ -- */
	public List<SoldeFinAnnee> getAll() { return soldeFinAnneeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(SoldeFinAnnee SoldeFinAnnee) {  soldeFinAnneeRepository.save(SoldeFinAnnee); }

	/* -- DELETE -- */
	public void delete(Object id) {  soldeFinAnneeRepository.deleteById(id); }

}
