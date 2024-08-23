package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.TypeAbsence;
import com.example.gestionrh.Model.Entity.TypeConge;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.TypeAbsenceService;
import com.example.gestionrh.Model.Service.TypeCongeService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("detail_utilisateur")
public class DetailUtilisateurController{

    @Autowired
	private DetailUtilisateurService detailUtilisateurService;

    @Autowired
    private TypeCongeService typeCongeService;

    @Autowired
    private TypeAbsenceService typeAbsenceService;

    @Autowired
    private VEtatDemandeService vEtatDemandeService;

	@PostMapping("verifierLogin")
    public String Login(@RequestParam String mail, @RequestParam String mdp, HttpSession session, HttpServletRequest request) {
        try {
            DetailUtilisateur user = detailUtilisateurService.getByUser(mail, mdp);
            session.setAttribute("userId", user.getUtilisateur().getId());
            session.setAttribute("userDirectionId", user.getFonction().getDirection().getId());
            session.setAttribute("userType", user.getUtilisateur().getTypeUtilisateur());
            return "redirect:/detail_utilisateur/page-conge";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Mot de passe ou email non valide");
            return "authentification/login";
        }
    }
    
    @GetMapping("page-conge")
    public String Accueil(HttpServletRequest request, HttpSession session) {
        String idUtilisateur = (String)session.getAttribute("userId");
        List<VEtatDemande> etatDemandes = vEtatDemandeService.getByIdUtilisateur(idUtilisateur);
        List<TypeConge> typeConges = typeCongeService.getAll();
        List<TypeAbsence> typeAbsence = typeAbsenceService.getAll();
        request.setAttribute("etatDemandes", etatDemandes);
        request.setAttribute("typeConge", typeConges);
        request.setAttribute("typeAbsence", typeAbsence);
        return "conge/conge";
    }
}