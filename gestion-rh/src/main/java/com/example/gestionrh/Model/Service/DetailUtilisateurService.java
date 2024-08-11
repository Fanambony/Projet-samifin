package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DetailUtilisateurRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class DetailUtilisateurService {

	private final DetailUtilisateurRepository detailUtilisateurRepository;

	public DetailUtilisateurService(DetailUtilisateurRepository detailUtilisateurRepository) {this.detailUtilisateurRepository = detailUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<DetailUtilisateur> getOne(Object id) { return detailUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<DetailUtilisateur> getAll() { return detailUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(DetailUtilisateur DetailUtilisateur) {  detailUtilisateurRepository.save(DetailUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  detailUtilisateurRepository.deleteById(id); }

	
	public DetailUtilisateur getByUser(String email, String mdp) throws Exception {
		DetailUtilisateur utilisateur = detailUtilisateurRepository.findByEmailAndMdp(email, mdp);
		if(utilisateur == null) throw new Exception("Authentification invalide");
		return utilisateur;
	}

}
