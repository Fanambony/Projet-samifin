package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import com.example.gestionrh.utils.EtatCongeConfig;
import com.example.gestionrh.utils.PaginationConfig;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@GetMapping("/etat-conge")
	public String getEtatConge(HttpServletRequest request,
							@RequestParam(defaultValue = "0") int page,
							@RequestParam(required = false) Integer size) {

		int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
		Pageable pageable = PageRequest.of(page, paginationSize);

		EtatCongeConfig etatConge = new EtatCongeConfig();
		int etat_demande_valide = etatConge.getEtatValider();

		List<VEtatDemande> demandeValiderParUtilisateur = vEtatDemandeService.demandeValiderParUtilisateur(etat_demande_valide);
		for (VEtatDemande ve : demandeValiderParUtilisateur) {
			Date dateFinDate = ve.getDateFin();
			if (dateFinDate != null) {
				LocalDate localDateFin = dateFinDate.toLocalDate().plusDays(1);
				ve.setDateFin(Date.valueOf(localDateFin));
			}
		}

		Page<VHistoriqueConge> historiqueConges = vHistoriqueCongeService.getHistoriqueConge(pageable);
		Page<Utilisateur> utilisateurPage = utilisateurService.getUtilisateurs(pageable);

		for(Utilisateur u : utilisateurPage){
			u.setHistoriqueConges(vHistoriqueCongeService.getByIdUtilisateur(u.getId()));
		}

		request.setAttribute("utilisateurs", utilisateurPage);
		request.setAttribute("historique_conge", historiqueConges);
		request.setAttribute("demandeValiderParUtilisateur", demandeValiderParUtilisateur);
		return "conge/etat-conge";
	}
}