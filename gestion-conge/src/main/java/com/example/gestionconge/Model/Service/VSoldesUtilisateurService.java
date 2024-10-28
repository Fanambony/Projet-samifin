package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.VSoldesUtilisateurRepository;
import com.example.gestionconge.Model.Entity.VSoldesUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VSoldesUtilisateurService {

	private final VSoldesUtilisateurRepository vSoldesUtilisateurRepository;

	public VSoldesUtilisateurService(VSoldesUtilisateurRepository vSoldesUtilisateurRepository) {this.vSoldesUtilisateurRepository = vSoldesUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<VSoldesUtilisateur> getOne(Object id) { return vSoldesUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<VSoldesUtilisateur> getAll() { return vSoldesUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VSoldesUtilisateur VSoldesUtilisateur) {  vSoldesUtilisateurRepository.save(VSoldesUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  vSoldesUtilisateurRepository.deleteById(id); }

}
