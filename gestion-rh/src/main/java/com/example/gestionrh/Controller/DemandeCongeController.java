package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DemandeConge;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Service.DemandeCongeService;
import com.example.gestionrh.Model.Service.EtatDemandeService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import com.example.gestionrh.utils.EtatCongeConfig;
import com.example.gestionrh.utils.PaginationConfig;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping("demande_conge")
public class DemandeCongeController{

	@Autowired
	private DemandeCongeService demandeCongeService;

	@Autowired
	private EtatDemandeService etatDemandeService;

	@Autowired
	private VEtatDemandeService vEtatDemandeService;

	@Autowired
	private VHistoriqueCongeService vHistoriqueCongeService;

	@Autowired
    private PaginationConfig paginationConfig;

	@GetMapping("annuler-conge") 
	public String annuler(@RequestParam("idDemande") String idDemande) {
		demandeCongeService.delete(idDemande);
		return "redirect:/demande_conge/annulation-conge";
	}

	@GetMapping("/annulation-conge")
	public String annulerConge(HttpServletRequest request,
								@RequestParam(defaultValue = "0") int page, 
                                @RequestParam(required = false) Integer size) {

		int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();

        Pageable pageable = PageRequest.of(page, paginationSize);
		
		EtatCongeConfig etatConge = new EtatCongeConfig();
		int etat_demande_valide = etatConge.getEtatValider();
		Page<VEtatDemande> list_demande_valider = vEtatDemandeService.findByIdEtatDemandeAndDateDebutAfter(etat_demande_valide, pageable);
		request.setAttribute("list_demande_valider", list_demande_valider);
		return "conge/annulation-conge";
	}

	@PostMapping("/modifier-conge")
	public String modifierConge(@RequestParam("idDemande") String idDemande,
								@RequestParam("date_debut") String dateDebutString,
								@RequestParam("date_fin") String dateFinString,
								@RequestParam("debut_absence") int debutAbsence,
								@RequestParam("fin_absence") int finAbsence,
								@RequestParam("commentaire") String commentaire
								) {

		Date dateDebut = Date.valueOf(dateDebutString);
		Date dateFin = Date.valueOf(dateFinString);

		Optional<DemandeConge> findById = demandeCongeService.getOne(idDemande);
		DemandeConge d = findById.get();
		d.setDateDebut(dateDebut);
		d.setDateFin(dateFin);
		d.setDebutAbsence(debutAbsence);
		d.setFinAbsence(finAbsence);
		d.setCommentaire(commentaire);

		demandeCongeService.create(d);

		return "redirect:/detail_utilisateur/page-conge";
	}
	
	@GetMapping("/supprimer-conge")
	public String suppressionConge(@RequestParam("idDemande") String id) {
		demandeCongeService.delete(id);
		return "redirect:/detail_utilisateur/page-conge";
	}

	@GetMapping("auto-confirmation")
	public String autoConfirmation(
									@RequestParam("idDemande") String id,
									@RequestParam("etat") int etat,
									HttpSession session,
									RedirectAttributes redirectAttributes) {

		Optional<DemandeConge> findById = demandeCongeService.getOne(id);
		if (!findById.isPresent()) {
			redirectAttributes.addFlashAttribute("message", "Demande introuvable");
			redirectAttributes.addFlashAttribute("type", "error");
			System.out.println("Demande introuvable");
			return "redirect:/detail_utilisateur/page-conge"; 
		}
		DemandeConge demande_conge = findById.get();

		Optional<VEtatDemande> findByIdDemande = vEtatDemandeService.getOne(id);
		if (!findByIdDemande.isPresent()) {
			redirectAttributes.addFlashAttribute("message", "État de la demande introuvable");
			redirectAttributes.addFlashAttribute("type", "error");
			System.out.println("État de la demande introuvable");
			return "redirect:/detail_utilisateur/page-conge";
		}
		VEtatDemande vEtat = findByIdDemande.get();
		double solde_demander = vEtat.getNombreJoursConge();

		String id_type_conge = demande_conge.getIdTypeConge();
		String idUtilisateur = (String)session.getAttribute("userId");
		VHistoriqueConge historiqueConge = vHistoriqueCongeService.historiqueCongeParUtilisateur(idUtilisateur, id_type_conge);
		
		double solde_restant = 0;

		if (historiqueConge != null) {
			solde_restant = historiqueConge.getSoldeRestant();
		}

		if (solde_restant >= solde_demander) {
			demande_conge.setEtatDemande(etat);        
			demandeCongeService.create(demande_conge);
			redirectAttributes.addFlashAttribute("message", "Demande envoyée avec succès");
			redirectAttributes.addFlashAttribute("type", "success");
		} else {
			redirectAttributes.addFlashAttribute("message", "Le solde restant est insuffisant pour cette demande de congé");
			redirectAttributes.addFlashAttribute("type", "error");
		}

		return "redirect:/detail_utilisateur/page-conge";
	}

	
	@PostMapping("/ajout_conge")
	public String demanderConge(HttpSession session,
								@RequestParam("typeConge") String typeConge,
								@RequestParam("date_debut") String date_debut_string,
								@RequestParam("debut_absence") int debut_absence,
								@RequestParam("date_fin") String date_fin_string,
								@RequestParam("fin_absence") int fin_absence,
								@RequestParam("commentaire") String commentaire
								) {
		
		Date date_demande = new Date(System.currentTimeMillis());
		int etat_conge_soumis = etatDemandeService.getEtatSoumis();

		Date date_debut = Date.valueOf(date_debut_string);
		Date date_fin = Date.valueOf(date_fin_string);
		String utilisateur = (String)session.getAttribute("userId");
			
		DemandeConge demandeConge = new DemandeConge(typeConge, utilisateur, date_demande, date_debut, debut_absence, date_fin, fin_absence, commentaire, etat_conge_soumis);
		demandeCongeService.create(demandeConge);
		return "redirect:/detail_utilisateur/page-conge";
	}
	
	@GetMapping("valider-conge")
	public String validerConge(
									@RequestParam("idDemande") String id,
									@RequestParam("etat") int etat,
									HttpSession session
									) {

		Optional<DemandeConge> findById = demandeCongeService.getOne(id);
		DemandeConge d = findById.get();
		String utilisateur = (String)session.getAttribute("userId");

		d.setEtatDemande(etat);
		d.setIdValidateur(utilisateur);
		
		demandeCongeService.create(d);
		return "redirect:/v_etat_demande/liste-demande";
	}

	@GetMapping("refuser-conge")
	public String refuserConge(
									@RequestParam("idDemande") String id,
									@RequestParam("etat") int etat
									) {

		Optional<DemandeConge> findById = demandeCongeService.getOne(id);
		DemandeConge d = findById.get();
		d.setEtatDemande(etat);
		
		demandeCongeService.create(d);
		return "redirect:/v_etat_demande/liste-demande";
	}
}