package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.EtatUtilisateur;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Genre;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;
import com.example.gestionrh.Model.Service.FonctionService;
import com.example.gestionrh.Model.Service.GenreService;
import com.example.gestionrh.Model.Service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/")
public class UtilisateurController{

    @Autowired
	private UtilisateurService utilisateurService;

	@Autowired
    private DirectionService directionService;
    
    @Autowired
    private FonctionService fonctionService;
    
    @Autowired
    private GenreService genreService;

    @Autowired
    private EtatUtilisateurService etatUtilisateurService;

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
        List<Genre> genre = genreService.getAll();
        List<EtatUtilisateur> etatUtilisateur = etatUtilisateurService.getAll();
        request.setAttribute("utilisateurs", utilisateurPage);
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        request.setAttribute("page", page);
        request.setAttribute("etat", etatUtilisateur);
		return "/utilisateur/list-utilisateur";
	}

    @GetMapping("detail-utilisateur")
    public String detailUtilisateur(HttpServletRequest request, @RequestParam("id") String id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(id);
        request.setAttribute("utilisateur", utilisateur);
        return "/utilisateur/detail-utilisateur";
    }

    @GetMapping("page-sante")
    public String pageSante(HttpSession session, HttpServletRequest request) {
        String idUtilisateur = (String)session.getAttribute("userId"); 
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(idUtilisateur);
        request.setAttribute("utilisateur", utilisateur);
        return "/sante/page-sante";
    }
}