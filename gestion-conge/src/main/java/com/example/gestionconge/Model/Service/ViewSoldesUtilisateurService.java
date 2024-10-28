package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.ViewSoldesUtilisateurRepository;
import com.example.gestionconge.Model.Entity.ViewSoldesUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class ViewSoldesUtilisateurService {

	private final ViewSoldesUtilisateurRepository viewSoldesUtilisateurRepository;

	public ViewSoldesUtilisateurService(ViewSoldesUtilisateurRepository viewSoldesUtilisateurRepository) {this.viewSoldesUtilisateurRepository = viewSoldesUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<ViewSoldesUtilisateur> getOne(Object id) { return viewSoldesUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<ViewSoldesUtilisateur> getAll() { return viewSoldesUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(ViewSoldesUtilisateur ViewSoldesUtilisateur) {  viewSoldesUtilisateurRepository.save(ViewSoldesUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  viewSoldesUtilisateurRepository.deleteById(id); }

}
