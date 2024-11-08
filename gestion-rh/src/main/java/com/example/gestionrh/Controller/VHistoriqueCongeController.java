package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import com.example.gestionrh.Model.Service.VUtilisateurDetaillerService;
import com.example.gestionrh.utils.EtatCongeConfig;
import com.example.gestionrh.utils.EtatUtilisateurConfig;
import com.example.gestionrh.utils.PaginationConfig;
import com.example.gestionrh.utils.TypeUtilisateurConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.sql.Date;
import java.time.LocalDate;


@Controller
@RequestMapping("v_historique_conge")
public class VHistoriqueCongeController{

	@Autowired
	private VHistoriqueCongeService vHistoriqueCongeService;

	@Autowired
	private VEtatDemandeService vEtatDemandeService;

	@Autowired
    private PaginationConfig paginationConfig;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private VUtilisateurDetaillerService vUtilisateurDetaillerService;

	@Autowired
	private TypeUtilisateurConfig typeUtilisateurConf;

	@Autowired
	private EtatUtilisateurConfig etatUtilisateurConfig;

	@Autowired 
	private EtatCongeConfig etatCongeConfig;

	@GetMapping("/mes-conges")
	public String mesConges(HttpSession session, HttpServletRequest request) {

		int etat_demande_valide = etatCongeConfig.getEtatValider();
		String idUtilisateur = (String)session.getAttribute("userId");

		List<VEtatDemande> demandeValiderParUtilisateur = vEtatDemandeService.demandeValiderPourUtilisateur(idUtilisateur, etat_demande_valide);
		for (VEtatDemande ve : demandeValiderParUtilisateur) {
			Date dateFinDate = ve.getDateFin();
			if (dateFinDate != null) {
				LocalDate localDateFin = dateFinDate.toLocalDate().plusDays(1);
				ve.setDateFin(Date.valueOf(localDateFin));
			}
		}

		List<VHistoriqueConge> historiqueConge = vHistoriqueCongeService.getByIdUtilisateur(idUtilisateur);

		request.setAttribute("historiqueConge", historiqueConge);
		request.setAttribute("demandeValiderParUtilisateur", demandeValiderParUtilisateur);
		
		return "/conge/mes-conges";
	}

	private boolean isAuthorizedForEtatConge(String role) {
        return role.equals("ADMIN") || role.equals("DIRECTEUR DE RATTACHEMENT") || role.equals("DIRECTEUR GENERAL");
    }
	
	@GetMapping("/etat-conge")
	public String getEtatConge(HttpServletRequest request,
							@RequestParam(defaultValue = "0") int page,
							@RequestParam(required = false) Integer size,
							HttpSession session,
							@RequestParam(value = "search", required = false) String searchTerm
							) {

		String userId = (String)session.getAttribute("userId");
		String userRole = utilisateurService.getUserRole(userId);

		if (!isAuthorizedForEtatConge(userRole)) {
			return "/utils/errorPage";
		}

		int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
		Pageable pageable = PageRequest.of(page, paginationSize);

		int etat_demande_valide = etatCongeConfig.getEtatValider();

		List<VEtatDemande> demandeValiderParUtilisateur = vEtatDemandeService.demandeValiderParUtilisateur(etat_demande_valide);
		for (VEtatDemande ve : demandeValiderParUtilisateur) {
			Date dateFinDate = ve.getDateFin();
			if (dateFinDate != null) {
				LocalDate localDateFin = dateFinDate.toLocalDate().plusDays(1);
				ve.setDateFin(Date.valueOf(localDateFin));
			}
		}

		int utilisateur_admin = typeUtilisateurConf.getAdmin();
		int etat_utilisateur_desactive = etatUtilisateurConfig.getDesactive();

		// Page<VHistoriqueConge> historiqueConges = vHistoriqueCongeService.getHistoriqueConge(pageable);
		// Page<Utilisateur> utilisateurPage = utilisateurService.getUtilisateurs(etat_utilisateur_desactive, utilisateur_admin, pageable);
		// Page<VUtilisateurDetailler> utilisateurPage = vUtilisateurDetaillerService.getDetailUtilisateurs(etat_utilisateur_desactive, utilisateur_admin, pageable);
		
		int type_utilisateur = (Integer)session.getAttribute("userType");
		int type_utilisateur_admin = typeUtilisateurConf.getAdmin();
		int type_utilisateur_directeur = typeUtilisateurConf.getDirection();
		int type_utilisateur_DG = typeUtilisateurConf.getDirecteurGeneral();

		String idDirection = (String)session.getAttribute("userDirectionId");

		Page<VUtilisateurDetailler> utilisateurPage = null;

        if(searchTerm != null && !searchTerm.isEmpty()) {
			if(type_utilisateur == type_utilisateur_admin) {
				utilisateurPage = new PageImpl<>(vUtilisateurDetaillerService.searchUtilisateurs(searchTerm));
			} else if(type_utilisateur == type_utilisateur_directeur || type_utilisateur == type_utilisateur_DG) {
				utilisateurPage = new PageImpl<>(vUtilisateurDetaillerService.searchUtilisateursEtatConge(idDirection, searchTerm));
			}
			paginationSize = (int)utilisateurPage.getTotalElements();
        } else {
			if(type_utilisateur == type_utilisateur_admin) {
				utilisateurPage = vUtilisateurDetaillerService.getDetailUtilisateurs(etat_utilisateur_desactive, utilisateur_admin, pageable);
			} else if(type_utilisateur == type_utilisateur_directeur || type_utilisateur == type_utilisateur_DG) {
				utilisateurPage = vUtilisateurDetaillerService.getDetailUtilisateursParDirection(etat_utilisateur_desactive, utilisateur_admin, idDirection, pageable);
			}
        }

		for(VUtilisateurDetailler u : utilisateurPage) {
			u.setHistoriqueConges(vHistoriqueCongeService.getByIdUtilisateur(u.getIdUtilisateur()));
		}

		request.setAttribute("utilisateurs", utilisateurPage);
		// request.setAttribute("historique_conge", historiqueConges);
		request.setAttribute("demandeValiderParUtilisateur", demandeValiderParUtilisateur);
		return "conge/etat-conge";
	}

	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VHistoriqueConge> getOne(@PathVariable Long numLigne) { 
		 Optional<VHistoriqueConge> vHistoriqueConge = this.vHistoriqueCongeService.getOne(numLigne);
		return vHistoriqueConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VHistoriqueConge> getAll() { 
		List<VHistoriqueConge> listVHistoriqueConge = this.vHistoriqueCongeService.getAll();
		return listVHistoriqueConge;
	}

}