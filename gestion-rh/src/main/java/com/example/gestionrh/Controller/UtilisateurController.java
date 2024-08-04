package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class UtilisateurController{

	private final UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}

	@GetMapping("/")
    public String login() {
        return "authentification/login";
    }
    
    @PostMapping("verifierLogin")
    public String Login(@RequestParam String mail, @RequestParam String mdp, HttpSession session, HttpServletRequest request) {
        System.out.println("mot de passe is "+mdp);
        System.out.println("mail is "+mail);
        try {
            Utilisateur user = utilisateurService.getByUser(mail, mdp);
            session.setAttribute("userId", user.getId());
            // session.setAttribute("userType", user.getFonction().getType());
            return "authentification/accueil";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Mot de passe ou email non valide");
            return "authentification/login";
        }	
    }

    @GetMapping("deconnecter")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "/authentification/login";
	}
}