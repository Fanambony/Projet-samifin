package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.EtatUtilisateur;
import com.example.gestionrh.Model.Entity.Filiation;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Genre;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;
import com.example.gestionrh.Model.Service.FiliationService;
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

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class UtilisateurController{

    @Autowired
	private UtilisateurService utilisateurService;

    @Autowired
    private DetailUtilisateurService detailUtilisateurService;

	@Autowired
    private DirectionService directionService;
    
    @Autowired
    private FonctionService fonctionService;
    
    @Autowired
    private GenreService genreService;

    @Autowired
    private EtatUtilisateurService etatUtilisateurService;

    @Autowired
    private FiliationService filiationService;

    @PostMapping("modifier-image")
    public String uploadImage(HttpSession session, @RequestParam("file") MultipartFile file) {
        String idUtilisateur = (String)session.getAttribute("userId");
        try {
            byte[] imageBytes = file.getBytes();
            Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(idUtilisateur);
            if (utilisateurOpt.isPresent()) {
                Utilisateur utilisateur = utilisateurOpt.get();
                utilisateur.setImage(imageBytes);
                utilisateurService.create(utilisateur);
            } else {
                return "redirect:/error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/detail-utilisateur";
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
        List<Genre> genre = genreService.getAll();
        List<EtatUtilisateur> etatUtilisateur = etatUtilisateurService.getAll();
        request.setAttribute("utilisateurs", utilisateurPage);
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        request.setAttribute("etat", etatUtilisateur);
		return "/utilisateur/list-utilisateur";
	}

    @GetMapping("detail-utilisateur")
    public String detailUtilisateur(HttpServletRequest request, HttpSession session) {
        String idUtilisateur = (String)session.getAttribute("userId");
        Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(idUtilisateur);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
    
            request.setAttribute("utilisateur", utilisateur);
            return "/utilisateur/detail-utilisateur";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("bulletin-consultation")
    public String bulletinConsultation(HttpSession session, HttpServletRequest request) {
        String idUtilisateur = (String)session.getAttribute("userId"); 
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(idUtilisateur);
        Utilisateur user = utilisateur.get();
        request.setAttribute("utilisateur", user);
        return "/sante/bulletin-consultation";
    }

    @GetMapping("demande-remboursement")
    public String demandeRemboursement(HttpSession session, HttpServletRequest request) {
        String idUtilisateur = (String)session.getAttribute("userId"); 
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(idUtilisateur);
        Utilisateur user = utilisateur.get();
        request.setAttribute("utilisateur", user);
        return "/sante/demande-remboursement";
    }

    @PostMapping("/modifier-mot-de-passe")
    public String modifierPassword(
            HttpSession session,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes) {
        
        String idUtilisateur = (String) session.getAttribute("userId");
        Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(idUtilisateur);

        if (utilisateurOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Utilisateur non trouvé.");
            return "redirect:/detail-utilisateur";
        }

        Utilisateur user = utilisateurOpt.get();
        boolean passwordUpdated = false;

        for (DetailUtilisateur detailUtilisateur : user.getDetailUtilisateurs()) {
            String motDePasseActuel = detailUtilisateur.getMdp();
            
            if (motDePasseActuel.equals(currentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    detailUtilisateur.setMdp(newPassword);
                    detailUtilisateurService.create(detailUtilisateur);
                    passwordUpdated = true;
                    break;
                } else {
                    redirectAttributes.addFlashAttribute("message", "Les mots de passe ne correspondent pas.");
                    return "redirect:/detail-utilisateur";
                }
            }
        }

        if (passwordUpdated) {
            redirectAttributes.addFlashAttribute("message", "Le mot de passe a été changé avec succès.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Le mot de passe actuel est incorrect.");
        }

        return "redirect:/detail-utilisateur";
    }

    @GetMapping("ajout-utilisateur")
    public String pageAjoutUtilisateur(HttpServletRequest request) {
        List<Direction> directions = directionService.getAll();
        List<Fonction> fonctions = fonctionService.getAll();
        List<Genre> genre = genreService.getAll();
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        return "/utilisateur/ajout-utilisateur";
    }
    
    
}