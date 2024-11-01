package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.InterimUtilisateurRepository;
import com.example.gestionrh.Model.Entity.InterimUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class InterimUtilisateurService {

	private final InterimUtilisateurRepository interimUtilisateurRepository;

	public InterimUtilisateurService(InterimUtilisateurRepository interimUtilisateurRepository) {this.interimUtilisateurRepository = interimUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<InterimUtilisateur> getOne(Object id) { return interimUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<InterimUtilisateur> getAll() { return interimUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(InterimUtilisateur InterimUtilisateur) {  interimUtilisateurRepository.save(InterimUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  interimUtilisateurRepository.deleteById(id); }

}
