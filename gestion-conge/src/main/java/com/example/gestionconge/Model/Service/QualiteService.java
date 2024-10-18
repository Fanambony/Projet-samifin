package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.QualiteRepository;
import com.example.gestionconge.Model.Entity.Qualite;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class QualiteService {

	private final QualiteRepository qualiteRepository;

	public QualiteService(QualiteRepository qualiteRepository) {this.qualiteRepository = qualiteRepository;}



	/* -- READ ONE -- */
	public Optional<Qualite> getOne(Object id) { return qualiteRepository.findById(id); }

	/* -- READ -- */
	public List<Qualite> getAll() { return qualiteRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Qualite Qualite) {  qualiteRepository.save(Qualite); }

	/* -- DELETE -- */
	public void delete(Object id) {  qualiteRepository.deleteById(id); }

}
