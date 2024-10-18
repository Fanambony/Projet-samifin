package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.utils.EtatCongeConfig;
import com.example.gestionrh.utils.TypeUtilisateurConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("v_etat_demande")
public class VEtatDemandeController{

	@Autowired
	private VEtatDemandeService vEtatDemandeService;

	@Autowired
	private UtilisateurService utilisateurService;

	private boolean isAuthorizedForValidationConge(String role) {
        return role.equals("ADMIN") || role.equals("CHEF_DE_DEPARTEMENT") || role.equals("DIRECTEUR_GENERAL");
    }

	@GetMapping("/liste-demande")
	public String ListDemande(HttpSession session, HttpServletRequest request) {

		TypeUtilisateurConfig typeUtilisateurConf = new TypeUtilisateurConfig();
		int utilisateur_collaborateur = typeUtilisateurConf.getCollaborateur();
		int utilisateur_admin = typeUtilisateurConf.getAdmin();
		int utilisateur_direction = typeUtilisateurConf.getDirection();
		int utilisateur_DG = typeUtilisateurConf.getDirecteurGeneral();

		String idDirection = (String)session.getAttribute("userDirectionId");
		int typeUtilisateur = (Integer)session.getAttribute("userType"); 
		int etatUtilisateur = 0;

		String userId = (String)session.getAttribute("userId");

		String userRole = utilisateurService.getUserRole(userId);

		if (!isAuthorizedForValidationConge(userRole)) {
            return "/utils/errorPage"; // Forbidden page
        }
		// int etatDemande = 0;

		List<VEtatDemande> getDemande = new ArrayList<>();

		// int etatDemande = 1; Soumis
		// int etatDemande = 5; En attente
		// int etatDemande = 10; Valider
		// int etatDemande = 15; Refuser

		// int etatUtilisateur = 1; //employe
		// int etatUtilisateur = 5; //Directeur Departement
		// int etatUtilisateur = 10; //Directeur General

		EtatCongeConfig etatConge = new EtatCongeConfig();
		int etat_demande_valide = etatConge.getEtatValider();
		int etat_demande_refuser = etatConge.getEtatRefuser();
		int etat_demande_attente = etatConge.getEtatAttente();

		if(typeUtilisateur == utilisateur_direction) {
			etatUtilisateur = utilisateur_collaborateur;

			List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for(Integer ed : etatDemandes) {
				List<VEtatDemande> demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed);
        		allDemandes.addAll(demandes);
			}
			getDemande = allDemandes;

		} else if(typeUtilisateur == utilisateur_DG) {
			// etatUtilisateur = 5;
			List<Integer> etatUtilisateurs = Arrays.asList(utilisateur_collaborateur, utilisateur_direction, utilisateur_DG);

			List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for(Integer ed : etatDemandes) {
				List<VEtatDemande> demandes;
				for(Integer userStatus : etatUtilisateurs) {
					if (userStatus == utilisateur_collaborateur) {
						demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed);
					} else {
						demandes = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed);
					}
					allDemandes.addAll(demandes);
				}
			}
			getDemande = allDemandes;

		} else if(typeUtilisateur == utilisateur_admin) {
			// etatDemande = 10;
			// etatDemande = 15;
			// etatDemande = 5;
			// etatUtilisateur = 1;
			// etatUtilisateur = 5;
			// etatUtilisateur = 10;
		}

		request.setAttribute("demande", getDemande);
		return "demande_conge/liste-demande";
	}	
}