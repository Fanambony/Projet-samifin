package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.VEtatDemandeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("v_etat_demande")
public class VEtatDemandeController{

	private final VEtatDemandeService vEtatDemandeService;

	public VEtatDemandeController(VEtatDemandeService vEtatDemandeService){this.vEtatDemandeService = vEtatDemandeService;}



	/* -- READ ONE -- */
	@GetMapping("/{idDemandeConge}")
	public Optional<VEtatDemande> getOne(@PathVariable String idDemandeConge) { 
		 Optional<VEtatDemande> vEtatDemande = this.vEtatDemandeService.getOne(idDemandeConge);
		return vEtatDemande;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VEtatDemande> getAll() { 
		List<VEtatDemande> listVEtatDemande = this.vEtatDemandeService.getAll();
		return listVEtatDemande;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VEtatDemande formData) {
		VEtatDemande vEtatDemande = new VEtatDemande();
		vEtatDemandeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VEtatDemande formData) {
		VEtatDemande vEtatDemande = new VEtatDemande();
		vEtatDemandeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{idDemandeConge}")
	public void delete(@PathVariable String idDemandeConge) {
		vEtatDemandeService.delete(idDemandeConge);
	}

	@GetMapping("/liste-demande")
	public String ListDemande(HttpSession session, HttpServletRequest request) {
		String idDirection = (String)session.getAttribute("userDirectionId");
		int etatUtilisateur = 1;
		List<VEtatDemande> getDemande = vEtatDemandeService.demandeCongeParidDirectionParTypeUtilisateur(idDirection, etatUtilisateur);
		request.setAttribute("demande", getDemande);
		return "demande_conge/liste-demande";
	}	
}