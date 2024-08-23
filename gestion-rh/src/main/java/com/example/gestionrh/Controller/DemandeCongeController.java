package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DemandeConge;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DemandeCongeService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("demande_conge")
public class DemandeCongeController{

	@Autowired
	private DemandeCongeService demandeCongeService;

	@PostMapping("/modifier-conge")
	public String modifierConge() {

		

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
									@RequestParam("etat") int etat
									) {

		Optional<DemandeConge> findById = demandeCongeService.getOne(id);
		DemandeConge d = findById.get();
		d.setEtatDemande(etat);
		
		demandeCongeService.create(d);
		return "redirect:/detail_utilisateur/page-conge";
	}
	

	@GetMapping("/ajout_conge")
	public String demanderConge(HttpSession session,
								@RequestParam("typeConge") String typeConge,
								@RequestParam("date_debut") String date_debut_string,
								@RequestParam("debut_absence") int debut_absence,
								@RequestParam("date_fin") String date_fin_string,
								@RequestParam("fin_absence") int fin_absence,
								@RequestParam("commentaire") String commentaire
								) {
						
		Date date_debut = Date.valueOf(date_debut_string);
		Date date_fin = Date.valueOf(date_fin_string);
		String utilisateur = (String)session.getAttribute("userId");
			
		DemandeConge demandeConge = new DemandeConge(typeConge, utilisateur, date_debut, debut_absence, date_fin, fin_absence, commentaire);
		demandeCongeService.create(demandeConge);
		return "redirect:/detail_utilisateur/page-conge";
	}
	
	

	// @GetMapping("/demande-conge")
	// public String demandeConge(HttpServletRequest request) {
	// 	List<TypeConge> typeConge = typeCongeService.getAll();
	// 	request.setAttribute("typeConge", typeConge);
	// 	return "conge/demande-conge";
	// }

	//view liste demande
	// @GetMapping("/mes-demande")
	// public String listDemande() {
	// 	return "conge/mes-demande";
	// }

	// @GetMapping("/demande-personnel")
	// public String demandePersonnels() {
	// 	return "/conge/demande-personnel";
	// }
	
	
}
