package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DetailUtilisateurRepository;
import com.example.gestionrh.Context.TypeUtilisateurRepository;
import com.example.gestionrh.Context.UtilisateurRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.TypeUtilisateur;
import com.example.gestionrh.Model.Entity.Utilisateur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private TypeUtilisateurRepository typeUtilisateurRepository;

	/* -- READ ONE -- */
	public Optional<Utilisateur> getOne(Object id) { return utilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<Utilisateur> getAll() { return utilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Utilisateur TypeConge) {  utilisateurRepository.save(TypeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  utilisateurRepository.deleteById(id); }

	public Page<Utilisateur> getUtilisateurs(int etat, int typeUtilisateur, Pageable pageable) {
        return utilisateurRepository.findAllExcludingEtatAndType(etat, typeUtilisateur, pageable);
    }

    public Page<Utilisateur> getAllUtilisateur(Pageable pageable) {
        return utilisateurRepository.findAll(pageable);
    }

	public List<Utilisateur> getUtilisateurActiveEtNonAdmin(int etat, int typeUtilisateur) {
		return utilisateurRepository.findByEtatAndTypeUtilisateurNot(etat, typeUtilisateur);
	}

	public String getUserRole(String userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // return mapTypeUtilisateurToRole(utilisateur.getTypeUtilisateur());
        TypeUtilisateur role = typeUtilisateurRepository.findByEtat(utilisateur.getTypeUtilisateur());

        if(role == null){
            throw new RuntimeException("Role not found");
        }

        return role.getLibelle();
    }

	// private String mapTypeUtilisateurToRole(int typeUtilisateur) {
    //     switch (typeUtilisateur) {
    //         case 10: return "DIRECTEUR_GENERAL";
    //         case 5: return "CHEF_DE_DEPARTEMENT";
    //         case 15: return "ADMIN";
    //         default: return "COLLABORATEUR";
    //     }
    // }

	@Transactional
    public String genererMdp(int length) {
        String password = (String) entityManager.createNativeQuery("SELECT generer_mdp(:length)")
                                                .setParameter("length", length)
                                                .getSingleResult();
        return password;
    }

	@Transactional
    public void ajouterUtilisateurAvecDetails(Utilisateur utilisateur, DetailUtilisateur detailUtilisateur) {
        utilisateurRepository.save(utilisateur); 

		String idUtilisateur = utilisateur.getId();

        detailUtilisateur.setIdUtilisateur(idUtilisateur);

        detailUtilisateurRepository.save(detailUtilisateur);
    }
    
}