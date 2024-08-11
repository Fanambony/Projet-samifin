package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.DetailUtilisateurRepository;
import com.example.gestionconge.Model.Entity.DetailUtilisateur;
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

}
