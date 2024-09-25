package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Famille;
import com.example.gestionrh.Model.Entity.Filiation;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.FamilleService;
import com.example.gestionrh.Model.Service.FiliationService;
import com.example.gestionrh.Model.Service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("famille")
public class FamilleController{

	@Autowired
	private FamilleService familleService;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private FiliationService filiationService;

	@PostMapping("modifierFamille")
	public String modifierFamille(@RequestParam("id_utilisateur") String idUtilisateur,
								@RequestParam("id_famille") String idFamille,
								@RequestParam("nom") String nom,
								@RequestParam("prenom") String prenom,
								@RequestParam("date_naissance") String dateNaissanceString,
								@RequestParam("filiation") String filiation
								) {

		Date dateNaissance = Date.valueOf(dateNaissanceString);
		
		Optional<Famille> findById = familleService.getOne(idFamille);
		Famille famille = findById.get();

		famille.setNom(nom);
		famille.setPrenom(prenom);
		famille.setDateNaissance(dateNaissance);
		famille.setIdFiliation(filiation);

		familleService.create(famille);

		return "redirect:/famille/gererFamille?idUtilisateur=" + idUtilisateur;
	}

	@GetMapping("gererFamille")
	public String gererFamille(@RequestParam("idUtilisateur") String idUtilisateur,
								HttpServletRequest request
								) {
		Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(idUtilisateur);
		
        List<Filiation> filiations = filiationService.getAll();

		if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
    
			request.setAttribute("filiations", filiations);
            request.setAttribute("utilisateur", utilisateur);
			return "/famille/gerer-famille";
        } else {
            return "redirect:/";
        }
	}

	@GetMapping("suprimerMembre")
	public String suprimerMembre(@RequestParam("idFamille") String idFamille,
								@RequestParam("idUtilisateur") String idUtilisateur
								) {
		familleService.delete(idFamille);
		return "redirect:/famille/gererFamille?idUtilisateur=" + idUtilisateur;
	}
	
	@PostMapping("ajouterFamille")
	public String ajouterFamille(@RequestParam("nom") String nom,
								@RequestParam("prenom") String prenom,
								@RequestParam("filiation") String filiation,
								@RequestParam("date_naissance") String dateNaissanceString,
								@RequestParam("id_utilisateur") String id_utilisateur
								) {
		Date date_naissance = Date.valueOf(dateNaissanceString);
		Famille famille = new Famille(id_utilisateur, filiation, nom, prenom, date_naissance);
		familleService.create(famille);

		return "redirect:/famille/gererFamille?idUtilisateur=" + id_utilisateur;
	}
}