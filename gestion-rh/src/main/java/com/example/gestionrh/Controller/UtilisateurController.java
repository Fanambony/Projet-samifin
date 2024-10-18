package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Categorie;
import com.example.gestionrh.Model.Entity.CorpsAppartenance;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.EtatUtilisateur;
import com.example.gestionrh.Model.Entity.Famille;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Genre;
import com.example.gestionrh.Model.Entity.Indice;
import com.example.gestionrh.Model.Entity.LocaliteService;
import com.example.gestionrh.Model.Entity.Qualite;
import com.example.gestionrh.Model.Entity.ServiceEmployeur;
import com.example.gestionrh.Model.Entity.TypeUtilisateur;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.CategorieService;
import com.example.gestionrh.Model.Service.CorpsAppartenanceService;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;
import com.example.gestionrh.Model.Service.FamilleService;
import com.example.gestionrh.Model.Service.FonctionService;
import com.example.gestionrh.Model.Service.GenreService;
import com.example.gestionrh.Model.Service.IndiceService;
import com.example.gestionrh.Model.Service.LocaliteServiceService;
import com.example.gestionrh.Model.Service.PasswordService;
import com.example.gestionrh.Model.Service.QualiteService;
import com.example.gestionrh.Model.Service.ServiceEmployeurService;
import com.example.gestionrh.Model.Service.TypeUtilisateurService;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.example.gestionrh.utils.PaginationConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PasswordService passwordService;

    @Autowired 
    private QualiteService qualiteService;

    @Autowired
    private ServiceEmployeurService serviceEmployeurService;

    @Autowired
    private LocaliteServiceService localiteServiceService;

    @Autowired
    private IndiceService indiceService;

    @Autowired
    private CorpsAppartenanceService corpsAppartenanceService;

    @Autowired
    private CategorieService categorieService;


    @PostMapping("modifier-image")
    public String uploadImage(HttpSession session, @RequestParam("file") MultipartFile file) {
        String idUtilisateur = (String) session.getAttribute("userId");
        try {
            byte[] imageBytes = file.getBytes();
            Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(idUtilisateur);
            if (utilisateurOpt.isPresent()) {
                Utilisateur utilisateur = utilisateurOpt.get();
                utilisateur.setImage(imageBytes);
                utilisateurService.create(utilisateur);
                session.setAttribute("message", "L'image de profil a été modifiée avec succès.");
            } else {
                session.setAttribute("message", "Erreur : Utilisateur non trouvé.");
                return "redirect:/detail-utilisateur";
            }
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("message", "Erreur lors de la modification de l'image.");
            return "redirect:/detail-utilisateur";
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

        Pageable pageable = PageRequest.of(page, paginationSize);

        Page<Utilisateur> utilisateurPage = utilisateurService.getUtilisateurs(pageable);
		// List<Utilisateur> utilisateurs = utilisateurService.getAll();
        List<Direction> directions = directionService.getAll();
        List<Fonction> fonctions = fonctionService.getAll();
        List<Genre> genre = genreService.getAll();
        List<EtatUtilisateur> etatUtilisateur = etatUtilisateurService.getAll();
        List<TypeUtilisateur> typeUtilisateurs = typeUtilisateurService.getAll();

        List<Categorie> categories = categorieService.getAll();
        List<Qualite> qualites = qualiteService.getAll();
        List<ServiceEmployeur> serviceEmployeurs = serviceEmployeurService.getAll();
        List<LocaliteService> localiteServices = localiteServiceService.getAll();
        List<Indice> indices = indiceService.getAll();
        List<CorpsAppartenance> corpsAppartenances = corpsAppartenanceService.getAll();

        request.setAttribute("utilisateurs", utilisateurPage);
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        request.setAttribute("etat", etatUtilisateur);
        request.setAttribute("typeUtilisateurs", typeUtilisateurs);

        request.setAttribute("categories", categories);
        request.setAttribute("qualites", qualites);
        request.setAttribute("serviceEmployeurs", serviceEmployeurs);
        request.setAttribute("localiteServices", localiteServices);
        request.setAttribute("indices", indices);
        request.setAttribute("corpsAppartenances", corpsAppartenances);
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

        List<Categorie> categories = categorieService.getAll();
        List<Qualite> qualites = qualiteService.getAll();
        List<ServiceEmployeur> serviceEmployeurs = serviceEmployeurService.getAll();
        List<LocaliteService> localiteServices = localiteServiceService.getAll();
        List<Indice> indices = indiceService.getAll();
        List<CorpsAppartenance> corpsAppartenances = corpsAppartenanceService.getAll();
        List<Fonction> fonctions = fonctionService.getAll();

        request.setAttribute("categories", categories);
        request.setAttribute("qualites", qualites);
        request.setAttribute("serviceEmployeurs", serviceEmployeurs);
        request.setAttribute("localiteServices", localiteServices);
        request.setAttribute("indices", indices);
        request.setAttribute("corpsAppartenances", corpsAppartenances);
        request.setAttribute("fonctions", fonctions);
        
        request.setAttribute("famille", familles);
        request.setAttribute("utilisateur", user);
        return "/sante/bulletin-consultation";
    }

    @GetMapping("demande-remboursement")
    public String demandeRemboursement(HttpSession session, HttpServletRequest request) {
        String idUtilisateur = (String)session.getAttribute("userId"); 
        Optional<Utilisateur> utilisateur = utilisateurService.getOne(idUtilisateur);
        Utilisateur user = utilisateur.get();
        List<Direction> directions = directionService.getAll();
        request.setAttribute("directions", directions);
        request.setAttribute("utilisateur", user);
        return "/sante/demande-remboursement";
    }

    private boolean isAuthorizedForAttestation(String role) {
        return role.equals("ADMIN");
    }

    @GetMapping("/attestation-non-paiement")
    public String attestationNonPaiement(HttpServletRequest request,
                                        HttpSession session) {

        String userId = (String)session.getAttribute("userId");

		String userRole = utilisateurService.getUserRole(userId);

		if (!isAuthorizedForAttestation(userRole)) {
            return "/utils/errorPage";
        }

        int etatUtilisateurActive = 1;
        List<Utilisateur> listUtilisateurs = utilisateurService.getUtilisateurActive(etatUtilisateurActive);
        
        List<Famille> familles = familleService.getAll();

        request.setAttribute("listUtilisateur", listUtilisateurs);
        request.setAttribute("familles", familles);
        return "/sante/attestation-non-paiement";
    }

    @GetMapping("/familles-par-agent/{agentId}")
    @ResponseBody
    public List<Famille> getFamillesByAgent(@PathVariable String agentId) {
        return familleService.findByIdEmploye(agentId);
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

            // Utilisation d'une méthode pour vérifier le mot de passe chiffré
            if (passwordService.checkPassword(currentPassword, motDePasseActuel)) {
                if (newPassword.equals(confirmPassword)) {
                    // Chiffrer le nouveau mot de passe avant de le sauvegarder
                    String mdp_cripter = passwordService.encryptPassword(newPassword);
                    detailUtilisateur.setMdp(mdp_cripter);
                    detailUtilisateurService.create(detailUtilisateur); // Sauvegarde des détails mis à jour
                    passwordUpdated = true;
                    break;
                } else {
                    redirectAttributes.addFlashAttribute("message", "Les nouveaux mots de passe ne correspondent pas.");
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

        List<Qualite> qualites = qualiteService.getAll();
        List<ServiceEmployeur> serviceEmployeurs = serviceEmployeurService.getAll();
        List<LocaliteService> localiteServices = localiteServiceService.getAll();
        List<Indice> indices = indiceService.getAll();
        List<CorpsAppartenance> corpsAppartenances = corpsAppartenanceService.getAll();
        List<Categorie> categories = categorieService.getAll();
        request.setAttribute("directions", directions);
        request.setAttribute("fonctions", fonctions);
        request.setAttribute("genre", genre);
        request.setAttribute("typeUtilisateurs", typeUtilisateurs);

        request.setAttribute("qualites", qualites);
        request.setAttribute("serviceEmployeurs", serviceEmployeurs);
        request.setAttribute("localiteServices", localiteServices);
        request.setAttribute("indices", indices);
        request.setAttribute("corpsAppartenances", corpsAppartenances);
        request.setAttribute("categories", categories);

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

        String mdp_cripter = passwordService.encryptPassword(mot_de_passe);
        
        DetailUtilisateur detailUtilisateur = new DetailUtilisateur(matricule, email, mdp_cripter, telephone, dateEntre, id_fonction, qualite, categorie, corps_appartenance, indice, service_employeur, localite_service, numero_decision, mdpProvisoir);

        utilisateurService.ajouterUtilisateurAvecDetails(utilisateur, detailUtilisateur);
        
        return "redirect:/list-utilisateur";

    }

    @GetMapping("/generer-mot-de-passe")
    public ResponseEntity<String> genererMotDePasse() {
        int longueur_mdp = 10;
        String motDePasse = utilisateurService.genererMdp(longueur_mdp);
        return ResponseEntity.ok(motDePasse); // Retourne le mot de passe en JSON
    }

    @PostMapping("modifierUtilisateur")
    public String modifierFamille(@RequestParam("id_utilisateur") String id_utilisateur,
                                @RequestParam("id_detail_utilisateur") String id_detail_utilisateur,
                                @RequestParam(value = "nom", required = false) String nom,
                                @RequestParam(value = "prenom", required = false) String prenom,
                                @RequestParam(value = "dtn", required = false) String dtn,
                                @RequestParam(value = "genre", required = false) int genre,
                                @RequestParam(value = "matricule", required = false) String matricule,
                                @RequestParam(value = "dateEntree", required = false) String dateEntree,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "telephone", required = false) String telephone,
                                @RequestParam(value = "fonction", required = false) String fonction,
                                @RequestParam(value = "qualite", required = false) String qualite,
                                @RequestParam(value = "categorie", required = false) String categorie,
                                @RequestParam(value = "appartenance", required = false) String appartenance,
                                @RequestParam(value = "indice", required = false) String indice,
                                @RequestParam(value = "employeur", required = false) String employeur,
                                @RequestParam(value = "localite", required = false) String localite,
                                @RequestParam(value = "typeUtilisateur", required = false) int typeUtilisateur,
                                @RequestParam(value = "decision", required = false) String decision,
                                @RequestParam(value = "mot_de_passe", required = false) String mot_de_passe,
                                @RequestParam(value = "etatUtilisateur", required = false) int etatUtilisateur
                                ){

        Date date_naissance = Date.valueOf(dtn);
        Date date_entree = Date.valueOf(dateEntree);

        Optional<Utilisateur> utilisateurOptional = utilisateurService.getOne(id_utilisateur);
        Utilisateur utilisateur = utilisateurOptional.get();

        Optional<DetailUtilisateur> detailOptional = detailUtilisateurService.getOne(id_detail_utilisateur);
        DetailUtilisateur detail = detailOptional.get();

        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setDateNaissance(date_naissance);
        utilisateur.setIdGenre(genre);
        utilisateur.setTypeUtilisateur(typeUtilisateur);
        utilisateur.setEtat(etatUtilisateur);

        detail.setMatricule(matricule);
        detail.setEmail(email);
        detail.setTelephone(telephone);
        detail.setDateEntre(date_entree);
        detail.setIdFonction(fonction);
        detail.setIdQualite(qualite);
        detail.setIdCategorie(categorie);
        detail.setIdCorpsAppartenance(appartenance);
        detail.setIdIndice(indice);
        detail.setIdServiceEmployeur(employeur);
        detail.setIdLocaliteService(localite);
        detail.setNumeroDecision(decision);

        String ancienMdp = detail.getMdp();
        if (mot_de_passe != null && !mot_de_passe.isEmpty() && !mot_de_passe.equals(ancienMdp)) {
            String mdp_cripter = passwordService.encryptPassword(mot_de_passe);
            detail.setMdp(mdp_cripter);
            detail.setMdpProvisoir(true);
        } else {
            detail.setMdpProvisoir(false);
        }

        utilisateurService.create(utilisateur);
        detailUtilisateurService.create(detail);

        return "redirect:/list-utilisateur";
    }
}