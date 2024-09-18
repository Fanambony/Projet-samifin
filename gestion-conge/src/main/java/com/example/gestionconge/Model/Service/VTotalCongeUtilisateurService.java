package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.VTotalCongeUtilisateurRepository;
import com.example.gestionconge.Model.Entity.VTotalCongeUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VTotalCongeUtilisateurService {

	private final VTotalCongeUtilisateurRepository vTotalCongeUtilisateurRepository;

	public VTotalCongeUtilisateurService(VTotalCongeUtilisateurRepository vTotalCongeUtilisateurRepository) {this.vTotalCongeUtilisateurRepository = vTotalCongeUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<VTotalCongeUtilisateur> getOne(Object id) { return vTotalCongeUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<VTotalCongeUtilisateur> getAll() { return vTotalCongeUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VTotalCongeUtilisateur VTotalCongeUtilisateur) {  vTotalCongeUtilisateurRepository.save(VTotalCongeUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  vTotalCongeUtilisateurRepository.deleteById(id); }

}
