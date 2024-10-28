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
        return role.equals("ADMIN") || role.equals("CHEF_DE_DEPARTEMENT") || role.equals("DIRECTEUR_GENERAL");
    }

	// @GetMapping("/liste-demande")
	// public String ListDemande(HttpSession session, 
	// 						HttpServletRequest request
	// 						) {

	// 	TypeUtilisateurConfig typeUtilisateurConf = new TypeUtilisateurConfig();
	// 	int utilisateur_collaborateur = typeUtilisateurConf.getCollaborateur();
	// 	int utilisateur_admin = typeUtilisateurConf.getAdmin();
	// 	int utilisateur_direction = typeUtilisateurConf.getDirection();
	// 	int utilisateur_DG = typeUtilisateurConf.getDirecteurGeneral();

	// 	String idDirection = (String)session.getAttribute("userDirectionId");
	// 	int typeUtilisateur = (Integer)session.getAttribute("userType"); 
	// 	int etatUtilisateur = 0;

	// 	String userId = (String)session.getAttribute("userId");

	// 	String userRole = utilisateurService.getUserRole(userId);

	// 	if (!isAuthorizedForValidationConge(userRole)) {
    //         return "/utils/errorPage"; // Forbidden page
    //     }
	// 	// int etatDemande = 0;

	// 	List<VEtatDemande> getDemande = new ArrayList<>();

	// 	EtatCongeConfig etatConge = new EtatCongeConfig();
	// 	int etat_demande_valide = etatConge.getEtatValider();
	// 	int etat_demande_refuser = etatConge.getEtatRefuser();
	// 	int etat_demande_attente = etatConge.getEtatAttente();

	// 	if(typeUtilisateur == utilisateur_direction) {
	// 		etatUtilisateur = utilisateur_collaborateur;

	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		for(Integer ed : etatDemandes) {
	// 			List<VEtatDemande> demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed);
    //     		allDemandes.addAll(demandes);
	// 		}
	// 		getDemande = allDemandes;

	// 	} else if(typeUtilisateur == utilisateur_DG) {
	// 		// etatUtilisateur = 5;
	// 		List<Integer> etatUtilisateurs = Arrays.asList(utilisateur_collaborateur, utilisateur_direction, utilisateur_DG);

	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		for(Integer ed : etatDemandes) {
	// 			List<VEtatDemande> demandes;
	// 			for(Integer userStatus : etatUtilisateurs) {
	// 				if (userStatus == utilisateur_collaborateur) {
	// 					demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed);
	// 				} else {
	// 					demandes = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed);
	// 				}
	// 				allDemandes.addAll(demandes);
	// 			}
	// 		}
	// 		getDemande = allDemandes;

	// 	} else if(typeUtilisateur == utilisateur_admin) {
	// 		// etatDemande = 10;
	// 		// etatDemande = 15;
	// 		// etatDemande = 5;
	// 		// etatUtilisateur = 1;
	// 		// etatUtilisateur = 5;
	// 		// etatUtilisateur = 10;
	// 	}

	// 	request.setAttribute("demande", getDemande);
	// 	return "demande_conge/liste-demande";
	// }	


	// @GetMapping("/liste-demande")
	// public String ListDemande(HttpSession session, 
	// 						HttpServletRequest request,
	// 						@RequestParam(defaultValue = "0") int page,
	// 						@RequestParam(required = false) Integer size) {

	// 	int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
	// 	Pageable pageable = PageRequest.of(page, paginationSize);

	// 	TypeUtilisateurConfig typeUtilisateurConf = new TypeUtilisateurConfig();
	// 	int utilisateur_collaborateur = typeUtilisateurConf.getCollaborateur();
	// 	int utilisateur_admin = typeUtilisateurConf.getAdmin();
	// 	int utilisateur_direction = typeUtilisateurConf.getDirection();
	// 	int utilisateur_DG = typeUtilisateurConf.getDirecteurGeneral();

	// 	String idDirection = (String) session.getAttribute("userDirectionId");
	// 	int typeUtilisateur = (Integer) session.getAttribute("userType"); 
	// 	int etatUtilisateur = 0;

	// 	String userId = (String) session.getAttribute("userId");
	// 	String userRole = utilisateurService.getUserRole(userId);

	// 	if (!isAuthorizedForValidationConge(userRole)) {
	// 		return "/utils/errorPage"; // Forbidden page
	// 	}

	// 	EtatCongeConfig etatConge = new EtatCongeConfig();
	// 	int etat_demande_valide = etatConge.getEtatValider();
	// 	int etat_demande_refuser = etatConge.getEtatRefuser();
	// 	int etat_demande_attente = etatConge.getEtatAttente();

	// 	// Initialiser la variable getDemandePage avec une page vide par défaut
	// 	Page<VEtatDemande> getDemandePage = Page.empty(pageable); 

	// 	if (typeUtilisateur == utilisateur_direction) {
	// 		etatUtilisateur = utilisateur_collaborateur;

	// 		// Liste des états de demande à inclure
	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);

	// 		// Liste pour stocker toutes les demandes paginées
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		int totalDemandes = 0;

	// 		// Boucler à travers chaque état et récupérer les demandes paginées
	// 		for (Integer ed : etatDemandes) {
	// 			Page<VEtatDemande> demandesPage = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed, pageable);
	// 			allDemandes.addAll(demandesPage.getContent()); // Ajoutez les demandes à la liste
	// 			totalDemandes += demandesPage.getTotalElements(); // Comptez le total des éléments
	// 		}

	// 		// Créer une nouvelle page à partir de la liste de toutes les demandes
	// 		getDemandePage = new PageImpl<>(allDemandes, pageable, totalDemandes);

	// 	} else if (typeUtilisateur == utilisateur_DG) {
	// 		// Liste des types d'utilisateurs à inclure
	// 		List<Integer> etatUtilisateurs = Arrays.asList(utilisateur_collaborateur, utilisateur_direction, utilisateur_DG);
		
	// 		// Liste des états de demande à inclure
	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
		
	// 		// Liste pour stocker toutes les demandes paginées
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		int totalDemandes = 0;
		
	// 		// Boucler à travers chaque état et chaque utilisateur
	// 		for (Integer ed : etatDemandes) {
	// 			for (Integer userStatus : etatUtilisateurs) {
	// 				Page<VEtatDemande> demandesPage;
	// 				if (userStatus == utilisateur_collaborateur) {
	// 					// Si c'est un collaborateur, filtrer par direction
	// 					demandesPage = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed, pageable);
	// 				} else {
	// 					// Pour les autres utilisateurs, juste par type
	// 					demandesPage = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed, pageable);
	// 				}

	// 				// Ajouter les demandes à la liste
	// 				allDemandes.addAll(demandesPage.getContent());
	// 				totalDemandes += demandesPage.getTotalElements(); // Compter le total des éléments
	// 			}
	// 		}
		
	// 		// Créer une nouvelle page à partir de la liste de toutes les demandes
	// 		getDemandePage = new PageImpl<>(allDemandes, pageable, totalDemandes);
		
	// 	} else if (typeUtilisateur == utilisateur_admin) {
	// 		// Si vous avez une logique spécifique pour les administrateurs, ajoutez-la ici
	// 		// getDemandePage peut être assigné ici si nécessaire
	// 	}

		

	// 	// Ajouter l'objet Page dans la requête
	// 	request.setAttribute("demande", getDemandePage);
	// 	return "demande_conge/liste-demande";
	// }


	// @GetMapping("/liste-demande")
	// public String ListDemande(HttpSession session, 
	// 						HttpServletRequest request,
	// 						@RequestParam(defaultValue = "1") int page, // Numéro de la page demandée, par défaut 1
	// 						@RequestParam(required = false) Integer size // Nombre d'éléments par page, par défaut 10
	// 						) {

	// 	int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
		
		
	// 	// TypeUtilisateurConfig typeUtilisateurConf = new TypeUtilisateurConfig();
	// 	int utilisateur_collaborateur = typeUtilisateurConf.getCollaborateur();
	// 	int utilisateur_admin = typeUtilisateurConf.getAdmin();
	// 	int utilisateur_direction = typeUtilisateurConf.getDirection();
	// 	int utilisateur_DG = typeUtilisateurConf.getDirecteurGeneral();

	// 	String idDirection = (String)session.getAttribute("userDirectionId");
	// 	int typeUtilisateur = (Integer)session.getAttribute("userType"); 
	// 	int etatUtilisateur = 0;

	// 	String userId = (String)session.getAttribute("userId");
	// 	String userRole = utilisateurService.getUserRole(userId);

	// 	if (!isAuthorizedForValidationConge(userRole)) {
	// 		return "/utils/errorPage"; // Forbidden page
	// 	}

	// 	List<VEtatDemande> getDemande = new ArrayList<>();
	// 	EtatCongeConfig etatConge = new EtatCongeConfig();
	// 	int etat_demande_valide = etatConge.getEtatValider();
	// 	int etat_demande_refuser = etatConge.getEtatRefuser();
	// 	int etat_demande_attente = etatConge.getEtatAttente();

	// 	if (typeUtilisateur == utilisateur_direction) {
	// 		etatUtilisateur = utilisateur_collaborateur;
	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		for (Integer ed : etatDemandes) {
	// 			List<VEtatDemande> demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur, ed);
	// 			allDemandes.addAll(demandes);
	// 		}
	// 		getDemande = allDemandes;

	// 	} else if (typeUtilisateur == utilisateur_DG) {
	// 		List<Integer> etatUtilisateurs = Arrays.asList(utilisateur_collaborateur, utilisateur_direction, utilisateur_DG);
	// 		List<Integer> etatDemandes = Arrays.asList(etat_demande_valide, etat_demande_refuser, etat_demande_attente);
	// 		List<VEtatDemande> allDemandes = new ArrayList<>();
	// 		for (Integer ed : etatDemandes) {
	// 			for (Integer userStatus : etatUtilisateurs) {
	// 				List<VEtatDemande> demandes;
	// 				if (userStatus == utilisateur_collaborateur) {
	// 					demandes = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, userStatus, ed);
	// 				} else {
	// 					demandes = vEtatDemandeService.demandeCongeParTypeUtilisateur(userStatus, ed);
	// 				}
	// 				allDemandes.addAll(demandes);
	// 			}
	// 		}
	// 		getDemande = allDemandes;

	// 	} else if (typeUtilisateur == utilisateur_admin) {
	// 		// Ajoutez ici le code pour l'administrateur si nécessaire.
	// 	}

	// 	// Pagination
	// 	int totalItems = getDemande.size(); // Total des éléments sans pagination
	// 	int totalPages = (int) Math.ceil((double) totalItems / paginationSize); // Calculer le nombre total de pages

	// 	// Calculer les indices pour la pagination
	// 	int start = (page - 1) * paginationSize;
	// 	int end = Math.min(start + paginationSize, totalItems);
	// 	List<VEtatDemande> paginatedDemande = getDemande.subList(start, end);

	// 	// Passer les données à la vue
	// 	request.setAttribute("demande", paginatedDemande);
	// 	request.setAttribute("currentPage", page);
	// 	request.setAttribute("totalPages", totalPages);
	// 	request.setAttribute("totalItems", totalItems);

	// 	return "demande_conge/liste-demande";
	// }


	@GetMapping("/liste-demande")
	public String ListDemande(HttpSession session, 
							HttpServletRequest request,
							@RequestParam(defaultValue = "1") int page, // Numéro de la page demandée, par défaut 1
							@RequestParam(required = false) Integer size // Nombre d'éléments par page, par défaut 10
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

		// Trier par état de demande pour afficher les demandes en attente, refusées, puis validées
		getDemande.sort((d1, d2) -> {
			int etat1 = d1.getIdEtatDemande();
			int etat2 = d2.getIdEtatDemande();
			
			// Priorité : demandes en attente en premier, puis refusées, puis validées
			if (etat1 == etat_demande_attente) return -1;
			if (etat2 == etat_demande_attente) return 1;
			// if (etat1 == etat_demande_refuser && etat2 != etat_demande_attente) return -1;
			// if (etat2 == etat_demande_refuser && etat1 != etat_demande_attente) return 1;
			return Integer.compare(etat1, etat2);
		});

		// Pagination
		int totalItems = getDemande.size(); // Total des éléments sans pagination
		int totalPages = (int) Math.ceil((double) totalItems / paginationSize); // Calculer le nombre total de pages

		// Calculer les indices pour la pagination
		int start = (page - 1) * paginationSize;
		int end = Math.min(start + paginationSize, totalItems);
		List<VEtatDemande> paginatedDemande = getDemande.subList(start, end);

		// Passer les données à la vue
		request.setAttribute("demande", paginatedDemande);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalItems", totalItems);

		return "demande_conge/liste-demande";
	}

}