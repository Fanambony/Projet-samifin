package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.EtatDemandeRepository;
import com.example.gestionconge.Model.Entity.EtatDemande;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class EtatDemandeService {

	private final EtatDemandeRepository etatDemandeRepository;

	public EtatDemandeService(EtatDemandeRepository etatDemandeRepository) {this.etatDemandeRepository = etatDemandeRepository;}



	/* -- READ ONE -- */
	public Optional<EtatDemande> getOne(Object id) { return etatDemandeRepository.findById(id); }

	/* -- READ -- */
	public List<EtatDemande> getAll() { return etatDemandeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(EtatDemande EtatDemande) {  etatDemandeRepository.save(EtatDemande); }

	/* -- DELETE -- */
	public void delete(Object id) {  etatDemandeRepository.deleteById(id); }

}
