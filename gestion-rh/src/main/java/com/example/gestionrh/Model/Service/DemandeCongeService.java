package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DemandeCongeRepository;
import com.example.gestionrh.Model.Entity.DemandeConge;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class DemandeCongeService {

	private final DemandeCongeRepository demandeCongeRepository;

	public DemandeCongeService(DemandeCongeRepository demandeCongeRepository) {this.demandeCongeRepository = demandeCongeRepository;}



	/* -- READ ONE -- */
	public Optional<DemandeConge> getOne(Object id) { return demandeCongeRepository.findById(id); }

	/* -- READ -- */
	public List<DemandeConge> getAll() { return demandeCongeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(DemandeConge DemandeConge) {  demandeCongeRepository.save(DemandeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  demandeCongeRepository.deleteById(id); }

}
