package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.UtilisateurRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurService(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}

	/* -- READ ONE -- */
	public Optional<Utilisateur> getOne(Object id) { return utilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<Utilisateur> getAll() { return utilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Utilisateur TypeConge) {  utilisateurRepository.save(TypeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  utilisateurRepository.deleteById(id); }

	public Page<Utilisateur> getUtilisateurs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return utilisateurRepository.findAll(pageable);
    }

	// public Utilisateur getById(String idUtilisateur) {
	// 	Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
	// 	return utilisateur;
	// }
}
