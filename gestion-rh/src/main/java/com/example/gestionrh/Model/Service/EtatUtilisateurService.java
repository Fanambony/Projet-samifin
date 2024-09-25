package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.EtatUtilisateurRepository;
import com.example.gestionrh.Model.Entity.EtatUtilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class EtatUtilisateurService {

	@Autowired
	private EtatUtilisateurRepository etatUtilisateurRepository;

	/* -- READ ONE -- */
	public Optional<EtatUtilisateur> getOne(Object id) { return etatUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<EtatUtilisateur> getAll() { return etatUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(EtatUtilisateur EtatUtilisateur) {  etatUtilisateurRepository.save(EtatUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  etatUtilisateurRepository.deleteById(id); }

	public Integer getEtatActive() {
		return etatUtilisateurRepository.findEtatActive();
	}
}
