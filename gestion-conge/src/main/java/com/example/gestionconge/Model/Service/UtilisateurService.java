package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.UtilisateurRepository;
import com.example.gestionconge.Model.Entity.Utilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurService(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<Utilisateur> getOne(Object id) { return utilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<Utilisateur> getAll() { return utilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Utilisateur Utilisateur) {  utilisateurRepository.save(Utilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  utilisateurRepository.deleteById(id); }

}
