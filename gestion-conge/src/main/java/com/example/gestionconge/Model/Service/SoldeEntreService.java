package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.SoldeEntreRepository;
import com.example.gestionconge.Model.Entity.SoldeEntre;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class SoldeEntreService {

	private final SoldeEntreRepository soldeEntreRepository;

	public SoldeEntreService(SoldeEntreRepository soldeEntreRepository) {this.soldeEntreRepository = soldeEntreRepository;}



	/* -- READ ONE -- */
	public Optional<SoldeEntre> getOne(Object id) { return soldeEntreRepository.findById(id); }

	/* -- READ -- */
	public List<SoldeEntre> getAll() { return soldeEntreRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(SoldeEntre SoldeEntre) {  soldeEntreRepository.save(SoldeEntre); }

	/* -- DELETE -- */
	public void delete(Object id) {  soldeEntreRepository.deleteById(id); }

}
