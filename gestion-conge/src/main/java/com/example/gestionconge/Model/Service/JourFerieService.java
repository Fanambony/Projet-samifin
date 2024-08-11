package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.JourFerieRepository;
import com.example.gestionconge.Model.Entity.JourFerie;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class JourFerieService {

	private final JourFerieRepository jourFerieRepository;

	public JourFerieService(JourFerieRepository jourFerieRepository) {this.jourFerieRepository = jourFerieRepository;}



	/* -- READ ONE -- */
	public Optional<JourFerie> getOne(Object id) { return jourFerieRepository.findById(id); }

	/* -- READ -- */
	public List<JourFerie> getAll() { return jourFerieRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(JourFerie JourFerie) {  jourFerieRepository.save(JourFerie); }

	/* -- DELETE -- */
	public void delete(Object id) {  jourFerieRepository.deleteById(id); }

}
