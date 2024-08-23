package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.EtatUtilisateurRepository;
import com.example.gestionconge.Model.Entity.EtatUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class EtatUtilisateurService {

	private final EtatUtilisateurRepository etatUtilisateurRepository;

	public EtatUtilisateurService(EtatUtilisateurRepository etatUtilisateurRepository) {this.etatUtilisateurRepository = etatUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<EtatUtilisateur> getOne(Object id) { return etatUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<EtatUtilisateur> getAll() { return etatUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(EtatUtilisateur EtatUtilisateur) {  etatUtilisateurRepository.save(EtatUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  etatUtilisateurRepository.deleteById(id); }

}
