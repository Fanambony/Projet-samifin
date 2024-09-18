package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.SoldeUtilisateurRepository;
import com.example.gestionconge.Model.Entity.SoldeUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class SoldeUtilisateurService {

	private final SoldeUtilisateurRepository soldeUtilisateurRepository;

	public SoldeUtilisateurService(SoldeUtilisateurRepository soldeUtilisateurRepository) {this.soldeUtilisateurRepository = soldeUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<SoldeUtilisateur> getOne(Object id) { return soldeUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<SoldeUtilisateur> getAll() { return soldeUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(SoldeUtilisateur SoldeUtilisateur) {  soldeUtilisateurRepository.save(SoldeUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  soldeUtilisateurRepository.deleteById(id); }

}
