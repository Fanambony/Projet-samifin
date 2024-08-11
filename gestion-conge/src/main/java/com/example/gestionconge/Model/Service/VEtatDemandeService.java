package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.VEtatDemandeRepository;
import com.example.gestionconge.Model.Entity.VEtatDemande;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VEtatDemandeService {

	private final VEtatDemandeRepository vEtatDemandeRepository;

	public VEtatDemandeService(VEtatDemandeRepository vEtatDemandeRepository) {this.vEtatDemandeRepository = vEtatDemandeRepository;}



	/* -- READ ONE -- */
	public Optional<VEtatDemande> getOne(Object id) { return vEtatDemandeRepository.findById(id); }

	/* -- READ -- */
	public List<VEtatDemande> getAll() { return vEtatDemandeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VEtatDemande VEtatDemande) {  vEtatDemandeRepository.save(VEtatDemande); }

	/* -- DELETE -- */
	public void delete(Object id) {  vEtatDemandeRepository.deleteById(id); }

}
