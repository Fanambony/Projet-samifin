package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DetailUtilisateurRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class DetailUtilisateurService {
	
	@Autowired
	private DetailUtilisateurRepository detailUtilisateurRepository;

	/* -- READ ONE -- */
	public Optional<DetailUtilisateur> getOne(Object id) { return detailUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<DetailUtilisateur> getAll() { return detailUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(DetailUtilisateur DetailUtilisateur) {  detailUtilisateurRepository.save(DetailUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  detailUtilisateurRepository.deleteById(id); }

	
	public DetailUtilisateur getByUser(String email, String mdp, int etat_utilisateur) throws Exception {
		DetailUtilisateur utilisateur = detailUtilisateurRepository.findByEmailAndMdpAndUtilisateur_Etat(email, mdp, etat_utilisateur);
		if(utilisateur == null) throw new Exception("Authentification invalide");
		return utilisateur;
	}

	public boolean existeMatricule(String matricule) {
		return detailUtilisateurRepository.existsByMatricule(matricule);
	}
	
}