package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.UtilisateurRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;
import org.springframework.stereotype.Service;



@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurService(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}

	public Utilisateur getByUser(String email, String mdp) throws Exception {
		Utilisateur utilisateur = utilisateurRepository.findByEmailAndMdp(email, mdp);
		if(utilisateur == null) throw new Exception("Authentification invalide");
		return utilisateur;
	}
}
