package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DetailUtilisateurRepository;
import com.example.gestionrh.Context.UtilisateurRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.Utilisateur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class UtilisateurService {

	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private DetailUtilisateurRepository detailUtilisateurRepository;

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

	@Transactional
    public String genererMdp(int length) {
        String password = (String) entityManager.createNativeQuery("SELECT generer_mdp(:length)")
                                                .setParameter("length", length)
                                                .getSingleResult();
        return password;
    }

	@Transactional // Cette annotation gère la transaction pour cette méthode
    public void ajouterUtilisateurAvecDetails(Utilisateur utilisateur, DetailUtilisateur detailUtilisateur) {
        utilisateurRepository.save(utilisateur); 

		String idUtilisateur = utilisateur.getId();

        detailUtilisateur.setIdUtilisateur(idUtilisateur);

        detailUtilisateurRepository.save(detailUtilisateur);
    }
}