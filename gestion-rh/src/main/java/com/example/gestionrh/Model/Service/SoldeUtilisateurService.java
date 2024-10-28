package com.example.gestionrh.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionrh.Context.SoldeUtilisateurRepository;
import com.example.gestionrh.Model.Entity.SoldeUtilisateur;

@Service
public class SoldeUtilisateurService {

    @Autowired
    private SoldeUtilisateurRepository soldeUtilisateurRepository;

    /* -- READ ONE -- */
	public Optional<SoldeUtilisateur> getOne(Object id) { return soldeUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<SoldeUtilisateur> getAll() { return soldeUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(SoldeUtilisateur SoldeUtilisateur) { soldeUtilisateurRepository.save(SoldeUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) { soldeUtilisateurRepository.deleteById(id); }

	public List<Object[]> getAnneeAndDateGenererGrouped() {
        return soldeUtilisateurRepository.findDistinctAnneeAndDateGenerer();
    }

	public boolean soldeDejaGenere(int annee) {
        // Requête pour vérifier l'existence d'un solde pour l'année donnée
        return soldeUtilisateurRepository.existsByAnnee(annee);
    }
}
