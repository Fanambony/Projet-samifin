package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.utils.EtatCongeConfig;
import com.example.gestionrh.utils.PaginationConfig;
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

	@Autowired
    private PaginationConfig paginationConfig;

	@Autowired
	private TypeUtilisateurConfig typeUtilisateurConf;

	private boolean isAuthorizedForValidationConge(String role) {
        return role.equals("ADMIN") || role.equals("DIRECTEUR DE RATTACHEMENT") || role.equals("DIRECTEUR GENERAL");
    }

	@GetMapping("/liste-demande")
	public String ListDemande(HttpSession session, 
							HttpServletRequest request,
							@RequestParam(defaultValue = "1") int page,
							@RequestParam(required = false) Integer size,
							@RequestParam(value = "search", required = false) String search
							) {

		int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
		
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

		List<VEtatDemande> getDemande = new ArrayList<>();
		EtatCongeConfig etatConge = new EtatCongeConfig();
		int etat_demande_valide = etatConge.getEtatValider();
		int etat_demande_refuser = etatConge.getEtatRefuser();
		int etat_demande_attente = etatConge.getEtatAttente();

		// Logique pour récupérer les demandes en fonction du type d'utilisateur
		if (typeUtilisateur == utilisateur_direction) {
			etatUtilisateur = utilisateur_collaborateur;
			List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for (Integer ed : etatDemandes) {
				List<VEtatDemande> demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed);
				allDemandes.addAll(demandes);
			}
			getDemande = allDemandes;

		} else if (typeUtilisateur == utilisateur_DG) {
			List<Integer> etatUtilisateurs = Arrays.asList(utilisateur_collaborateur, utilisateur_direction, utilisateur_DG);
			List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
			List<VEtatDemande> allDemandes = new ArrayList<>();
			for (Integer ed : etatDemandes) {
				for (Integer userStatus : etatUtilisateurs) {
					List<VEtatDemande> demandes;
					if (userStatus == utilisateur_collaborateur) {
						demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed);
					} else {
						demandes = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed);
					}
					allDemandes.addAll(demandes);
				}
			}
			getDemande = allDemandes;

		} else if (typeUtilisateur == utilisateur_admin) {
			getDemande = vEtatDemandeService.getAll();
		}

		// Si un terme de recherche est fourni, filtrer les demandes
		if (search != null && !search.trim().isEmpty()) {
			getDemande = vEtatDemandeService.searchByNameAndSurname(search);
		}
		

		// Trier par état de demande pour afficher les demandes en attente, refusées, puis validées
		getDemande.sort((d1, d2) -> {
			int etat1 = d1.getIdEtatDemande();
			int etat2 = d2.getIdEtatDemande();
			
			// Priorité : demandes en attente en premier, puis refusées, puis validées
			if (etat1 == etat_demande_attente) return -1;
			if (etat2 == etat_demande_attente) return 1;
			return Integer.compare(etat1, etat2);
		});

		// Pagination si aucune recherche n'est effectuée
		int totalItems = getDemande.size(); // Total des éléments sans pagination
		int totalPages = (int) Math.ceil((double) totalItems / paginationSize); // Calculer le nombre total de pages

		// Calculer les indices pour la pagination
		List<VEtatDemande> paginatedDemande;
		if (search == null || search.trim().isEmpty()) {
			// Appliquer la pagination uniquement si aucune recherche n'est effectuée
			int start = (page - 1) * paginationSize;
			int end = Math.min(start + paginationSize, totalItems);
			paginatedDemande = getDemande.subList(start, end);
		} else {
			// Aucune pagination pour les résultats de recherche
			paginatedDemande = getDemande;
			totalPages = 1; // Une seule page pour les résultats de recherche
		}

		// Passer les données à la vue
		request.setAttribute("demande", paginatedDemande);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalItems", totalItems);

		return "demande_conge/liste-demande";
	}


}