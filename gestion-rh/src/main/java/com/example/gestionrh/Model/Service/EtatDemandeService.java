package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.EtatDemandeRepository;
import com.example.gestionrh.Model.Entity.EtatDemande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class EtatDemandeService {

	@Autowired
	private EtatDemandeRepository etatDemandeRepository;


	/* -- READ ONE -- */
	public Optional<EtatDemande> getOne(Object id) { return etatDemandeRepository.findById(id); }

	/* -- READ -- */
	public List<EtatDemande> getAll() { return etatDemandeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(EtatDemande EtatDemande) {  etatDemandeRepository.save(EtatDemande); }

	/* -- DELETE -- */
	public void delete(Object id) {  etatDemandeRepository.deleteById(id); }

	public Integer getEtatSoumis() {
		return etatDemandeRepository.findEtatSoumis();
	}

}
