package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.PeriodeAbsence;
import com.example.gestionrh.Model.Entity.TypeConge;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.PeriodeAbsenceService;
import com.example.gestionrh.Model.Service.TypeCongeService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("detail_utilisateur")
public class DetailUtilisateurController{

	private final DetailUtilisateurService detailUtilisateurService;
    private final PeriodeAbsenceService periodeAbsencesService;
    private final TypeCongeService typeCongeService;
    private final VEtatDemandeService vEtatDemandeService;

	public DetailUtilisateurController(DetailUtilisateurService detailUtilisateurService, PeriodeAbsenceService periodeAbsencesService, TypeCongeService typeCongeService, VEtatDemandeService vEtatDemandeService){
        this.detailUtilisateurService = detailUtilisateurService;
        this.periodeAbsencesService = periodeAbsencesService;
        this.typeCongeService = typeCongeService;
        this.vEtatDemandeService = vEtatDemandeService;
    }

	@PostMapping("verifierLogin")
    public String Login(@RequestParam String mail, @RequestParam String mdp, HttpSession session, HttpServletRequest request) {
        try {
            DetailUtilisateur user = detailUtilisateurService.getByUser(mail, mdp);
            session.setAttribute("userId", user.getUtilisateur().getId());
            session.setAttribute("userDirectionId", user.getFonction().getDirection().getId());
            return "redirect:/detail_utilisateur/page-conge";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Mot de passe ou email non valide");
            return "authentification/login";
        }	
    }

    
    @GetMapping("page-conge")
    public String Accueil(HttpServletRequest request, HttpSession session) {
        String idUtilisateur = (String)session.getAttribute("userId");
        // Optional<DetailUtilisateur> utilisateur = detailUtilisateurService.getOne(idUtilisateur);
        // request.setAttribute("utilisateur", utilisateur);

        List<VEtatDemande> etatDemandes = vEtatDemandeService.getByIdUtilisateur(idUtilisateur);
        List<TypeConge> typeConges = typeCongeService.getAll();
        List<PeriodeAbsence> periodeAbsences = periodeAbsencesService.getAll();
        request.setAttribute("etatDemandes", etatDemandes);
        request.setAttribute("typeConge", typeConges);
        request.setAttribute("periodes", periodeAbsences);
        
        return "conge/conge";
    }
}
