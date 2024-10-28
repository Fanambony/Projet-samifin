package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.SoldeFinAnnee;
import com.example.gestionrh.Model.Entity.SoldeUtilisateur;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Entity.VSoldesUtilisateur;
import com.example.gestionrh.Model.Service.SoldeFinAnneeService;
import com.example.gestionrh.Model.Service.SoldeUtilisateurService;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import com.example.gestionrh.Model.Service.VSoldesUtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("v_soldes_utilisateur")
public class VSoldesUtilisateurController{

	@Autowired
	private VSoldesUtilisateurService vSoldesUtilisateurService;

	@Autowired
	private SoldeUtilisateurService soldeUtilisateurService;

	@Autowired
	private VHistoriqueCongeService vHistoriqueCongeService;

	@Autowired
	private SoldeFinAnneeService soldeFinAnneeService;

	@Autowired
	private UtilisateurService utilisateurService;

	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VSoldesUtilisateur> getOne(@PathVariable Long numLigne) { 
		 Optional<VSoldesUtilisateur> vSoldesUtilisateur = this.vSoldesUtilisateurService.getOne(numLigne);
		return vSoldesUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VSoldesUtilisateur> getAll() { 
		List<VSoldesUtilisateur> listVSoldesUtilisateur = this.vSoldesUtilisateurService.getAll();
		return listVSoldesUtilisateur;
	}

	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vSoldesUtilisateurService.delete(numLigne);
	}

	private boolean isAuthorizedForGenererSolde(String role) {
        return role.equals("ADMIN");
    }

    @GetMapping("generer-solde")
	public String genererSolde(HttpServletRequest request,
								HttpSession session
								) {

		String userId = (String)session.getAttribute("userId");
		String userRole = utilisateurService.getUserRole(userId);
							
		if (!isAuthorizedForGenererSolde(userRole)) {
			return "/utils/errorPage";
		}
		
		List<Object[]> soldeGenrer = soldeUtilisateurService.getAnneeAndDateGenererGrouped();
		request.setAttribute("soldeGenrer", soldeGenrer);
        return "/conge/generer-solde";
    }

	@PostMapping("generer-solde-conge")
	public String genererSoldeConge(@RequestParam("annee") int annee, RedirectAttributes redirectAttributes) {

		if (soldeUtilisateurService.soldeDejaGenere(annee)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Le solde de congé pour l'année " + annee + " a déjà été généré.");
			return "redirect:/v_soldes_utilisateur/generer-solde"; // Redirige vers la page de génération
		}

		String idCongeAnnuel = "TCG001"; 
		List<VHistoriqueConge> vHistoriqueConges = vHistoriqueCongeService.getByIdTypConges(idCongeAnnuel);

		for(VHistoriqueConge v : vHistoriqueConges) {
			int anneePrecedent = annee - 1;
			SoldeFinAnnee soldeFinAnnee = new SoldeFinAnnee(v.getIdUtilisateur(), anneePrecedent, v.getSoldeRestant());
			soldeFinAnneeService.create(soldeFinAnnee);
		}

		List<VSoldesUtilisateur> soldeUtilisateurs = vSoldesUtilisateurService.getAll();

		Date today = Date.valueOf(LocalDate.now());

		for(VSoldesUtilisateur solde : soldeUtilisateurs) {
			String idUtilisateur = solde.getIdUtilisateur();
			String idTypeConge = solde.getIdTypeConge();
			double soldeCalculer = solde.getSoldeCalcule();
			
			SoldeUtilisateur soldeUtilisateur = new SoldeUtilisateur(idUtilisateur, idTypeConge, annee, soldeCalculer, today);

			soldeUtilisateurService.create(soldeUtilisateur);

		}
		return "redirect:/v_soldes_utilisateur/generer-solde";
	}
	
}
