package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DemandeConge;
import com.example.gestionrh.Model.Entity.TypeConge;
import com.example.gestionrh.Model.Service.DemandeCongeService;
import com.example.gestionrh.Model.Service.TypeCongeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("demande_conge")
public class DemandeCongeController{

	private final DemandeCongeService demandeCongeService;
	private final TypeCongeService typeCongeService;

	public DemandeCongeController(DemandeCongeService demandeCongeService, TypeCongeService typeCongeService){
		this.demandeCongeService = demandeCongeService;
		this.typeCongeService = typeCongeService;
	}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<DemandeConge> getOne(@PathVariable String id) { 
		 Optional<DemandeConge> demandeConge = this.demandeCongeService.getOne(id);
		return demandeConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<DemandeConge> getAll() { 
		List<DemandeConge> listDemandeConge = this.demandeCongeService.getAll();
		return listDemandeConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		demandeCongeService.delete(id);
	}

	@GetMapping("/ajout_conge")
	public String demanderConge(HttpSession session,
								@RequestParam("typeConge") String typeConge,
								@RequestParam("date_debut") String date_debut_string,
								@RequestParam("debut_absence") String debut_absence,
								@RequestParam("date_fin") String date_fin_string,
								@RequestParam("fin_absence") String fin_absence,
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
