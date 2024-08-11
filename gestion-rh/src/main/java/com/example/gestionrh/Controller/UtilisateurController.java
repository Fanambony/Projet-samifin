package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.FonctionService;
import com.example.gestionrh.Model.Service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/")
public class UtilisateurController{

	private final UtilisateurService utilisateurService;
    private final DirectionService directionService;
    private final FonctionService fonctionService;

	public UtilisateurController(UtilisateurService utilisateurService, DirectionService directionService, FonctionService fonctionService){
        this.utilisateurService = utilisateurService;
        this.directionService = directionService;
        this.fonctionService = fonctionService;
    }

	@GetMapping("/")
    public String login() {
        return "authentification/login";
    }

    @GetMapping("deconnecter")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "/authentification/login";
	}

    @GetMapping("list-utilisateur")
	public String utilisateur(HttpServletRequest request, 
                                @RequestParam(defaultValue = "0") int page, 
                                @RequestParam(defaultValue = "5") int size) {
        Page<Utilisateur> utilisateurPage = utilisateurService.getUtilisateurs(page, size);
		// List<Utilisateur> utilisateurs = utilisateurService.getAll();
        List<Direction> directions = directionService.getAll();
        List<Fonction> fonctions = fonctionService.getAll();
        request.setAttribute("utilisateurs", utilisateurPage);
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
		return "/utilisateur/list-utilisateur";
	}

    @GetMapping("detail-utilisateur")
    public String detailUtilisateur(HttpServletRequest request, @RequestParam("id") String id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(id);
        request.setAttribute("utilisateur", utilisateur);
        return "/utilisateur/detail-utilisateur";
    }

}