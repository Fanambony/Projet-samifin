package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.QualiteRepository;
import com.example.gestionrh.Model.Entity.Qualite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QualiteService {

	@Autowired
	private QualiteRepository qualiteRepository;

	/* -- READ ONE -- */
	public Optional<Qualite> getOne(Object id) { return qualiteRepository.findById(id); }

	/* -- READ -- */
	public List<Qualite> getAll() { return qualiteRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Qualite Qualite) {  qualiteRepository.save(Qualite); }

	/* -- DELETE -- */
	public void delete(Object id) {  qualiteRepository.deleteById(id); }

}
