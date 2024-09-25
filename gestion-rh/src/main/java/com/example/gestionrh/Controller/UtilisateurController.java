package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.EtatUtilisateur;
import com.example.gestionrh.Model.Entity.Famille;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Genre;
import com.example.gestionrh.Model.Entity.TypeUtilisateur;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;
import com.example.gestionrh.Model.Service.FamilleService;
import com.example.gestionrh.Model.Service.FonctionService;
import com.example.gestionrh.Model.Service.GenreService;
import com.example.gestionrh.Model.Service.TypeUtilisateurService;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.utils.PaginationConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    private TypeUtilisateurService typeUtilisateurService;

    @Autowired
    private PaginationConfig paginationConfig;

    @Autowired 
    private FamilleService familleService;

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
                                @RequestParam(required = false) Integer size) {

        int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();

        Page<Utilisateur> utilisateurPage = utilisateurService.getUtilisateurs(page, paginationSize);
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
        List<Famille> familles = familleService.findByIdEmploye(idUtilisateur);
        request.setAttribute("famille", familles);
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

    @GetMapping("/verifier-matricule")
    public ResponseEntity<Boolean> verifierMatricule(@RequestParam String matricule) {
        boolean existe = detailUtilisateurService.existeMatricule(matricule);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("ajout-utilisateur")
    public String pageAjoutUtilisateur(HttpServletRequest request) {
        List<Direction> directions = directionService.getAll();
        List<Fonction> fonctions = fonctionService.getAll();
        List<Genre> genre = genreService.getAll();
        List<TypeUtilisateur> typeUtilisateurs = typeUtilisateurService.getAll();
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        request.setAttribute("typeUtilisateurs", typeUtilisateurs);

        return "/utilisateur/ajout-utilisateur";
    }
    
    @RequestMapping(value = "/ajoutUtilisateur", method = RequestMethod.POST)
    public String ajouterUtilisateur(@RequestParam("nom") String nom, 
                                    @RequestParam("prenom") String prenom, 
                                    @RequestParam("genre") int id_genre,
                                    @RequestParam("date_naissance") String dateNaissanceString,
                                    @RequestParam("numero_decision") String numero_decision,
                                    @RequestParam("matricule") String matricule,
                                    @RequestParam("email") String email,
                                    @RequestParam("telephone") String telephone,
                                    @RequestParam("date_entre") String dateEntreString,
                                    @RequestParam("type_utilisateur") int type_utilisateur ,
                                    @RequestParam("direction") String idDirection,
                                    @RequestParam("id_fonction") String id_fonction,
                                    @RequestParam("qualite") String qualite,
                                    @RequestParam("categorie") String categorie,
                                    @RequestParam("corps_appartenance") String corps_appartenance,
                                    @RequestParam("indice") String indice,
                                    @RequestParam("service_employeur") String service_employeur,
                                    @RequestParam("localite_service") String localite_service,
                                    @RequestParam("mot_de_passe") String mot_de_passe,
                                    @RequestParam(value = "mdp_provisoir", defaultValue = "true") Boolean mdpProvisoir,
                                    HttpServletRequest request) {

        Date dateNaissance = Date.valueOf(dateNaissanceString);
        Date dateEntre = Date.valueOf(dateEntreString);

        Utilisateur utilisateur = new Utilisateur(nom, prenom, dateNaissance, id_genre, type_utilisateur, etatUtilisateurService.getEtatActive());
        
        DetailUtilisateur detailUtilisateur = new DetailUtilisateur(matricule, email, mot_de_passe, telephone, dateEntre, id_fonction, qualite, categorie, corps_appartenance, indice, service_employeur, localite_service, numero_decision, mdpProvisoir);

        utilisateurService.ajouterUtilisateurAvecDetails(utilisateur, detailUtilisateur);
        
        return "redirect:/list-utilisateur";

    }

    @GetMapping("/generer-mot-de-passe")
    public ResponseEntity<String> genererMotDePasse() {
        int longueur_mdp = 10;
        String motDePasse = utilisateurService.genererMdp(longueur_mdp);
        return ResponseEntity.ok(motDePasse); // Retourne le mot de passe en JSON
    }
}


// Utilisateur utilisateur = new Utilisateur();
        // DetailUtilisateur detailUtilisateur = new DetailUtilisateur();

        // Optional<Genre> genreOptional = genreService.getOne(id_genre);
        // Genre genre = genreOptional.get();

        // Optional<TypeUtilisateur> typeOptional = typeUtilisateurService.getOne(type_utilisateur);
        // TypeUtilisateur typeUtilisateur = typeOptional.get();

        // Optional<Fonction> fonctionOptional = fonctionService.getOne(id_fonction);
        // Fonction fonction = fonctionOptional.get();

        // Optional<Direction> directionOptional = directionService.getOne(idDirection);
        // Direction direction = directionOptional.get();

        

        // utilisateur.setNom(nom);
        // utilisateur.setPrenom(prenom);
        // utilisateur.setDateNaissance(dateNaissance);
        // utilisateur.setGenre(genre);
        // utilisateur.setType_utilisateur(typeUtilisateur);
        
        // detailUtilisateur.setMatricule(matricule);
        // detailUtilisateur.setEmail(email);
        // detailUtilisateur.setMdp(mot_de_passe);
        // detailUtilisateur.setTelephone(telephone);
        // detailUtilisateur.setDateEntre(dateEntre);
        // detailUtilisateur.setFonction(fonction);
        // detailUtilisateur.setQualite(qualite);
        // detailUtilisateur.setCategorie(categorie);
        // detailUtilisateur.setCorpsAppartenance(corps_appartenance);
        // detailUtilisateur.setIndice(indice);
        // detailUtilisateur.setServiceEmployeur(service_employeur);
        // detailUtilisateur.setLocaliteService(localite_service);
        // detailUtilisateur.setNumeroDecision(numero_decision);

        // // Sauvegarder les données dans la session
        // request.setAttribute("utilisateurTemp", utilisateur);
        // request.setAttribute("detailUtilisateurTemp", detailUtilisateur);
        // request.setAttribute("direction", direction);