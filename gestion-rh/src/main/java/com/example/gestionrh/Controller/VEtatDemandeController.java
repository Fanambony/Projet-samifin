package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.VEtatDemandeService;

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

	@GetMapping("/liste-demande")
	public String ListDemande(HttpSession session, HttpServletRequest request) {
		String idDirection = (String)session.getAttribute("userDirectionId");
		int typeUtilisateur = (Integer)session.getAttribute("userType"); 
		int etatUtilisateur = 0;
		// int etatDemande = 0;

		List<VEtatDemande> getDemande = new ArrayList<>();

		// int etatDemande = 1; Soumis
		// int etatDemande = 5; En attente
		// int etatDemande = 10; Valider
		// int etatDemande = 15; Refuser

		// int etatUtilisateur = 1; //employe
		// int etatUtilisateur = 5; //Directeur Departement
		// int etatUtilisateur = 10; //Directeur General

		if(typeUtilisateur == 5) {
			etatUtilisateur = 1;
			
			List<Integer> etatDemandes = Arrays.asList(5, 10, 15);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for(Integer ed : etatDemandes) {
				List<VEtatDemande> demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed);
        		allDemandes.addAll(demandes);
			}
			getDemande = allDemandes;

		} else if(typeUtilisateur == 10) {
			// etatUtilisateur = 5;
			List<Integer> etatUtilisateurs = Arrays.asList(1, 5, 10);

			List<Integer> etatDemandes = Arrays.asList(5, 10, 15);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for(Integer ed : etatDemandes) {
				List<VEtatDemande> demandes;
				for(Integer userStatus : etatUtilisateurs) {
					if (userStatus == 1) {
						demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed);
					} else {
						demandes = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed);
					}
					allDemandes.addAll(demandes);
				}
			}
			getDemande = allDemandes;

		} else if(typeUtilisateur == 15) {
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