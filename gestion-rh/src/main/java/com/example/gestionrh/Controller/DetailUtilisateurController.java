package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DetailUtilisateur;
import com.example.gestionrh.Model.Entity.TypeAbsence;
import com.example.gestionrh.Model.Entity.TypeConge;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Service.DetailUtilisateurService;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;
import com.example.gestionrh.Model.Service.PasswordService;
import com.example.gestionrh.Model.Service.TypeAbsenceService;
import com.example.gestionrh.Model.Service.TypeCongeService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import com.example.gestionrh.utils.PaginationConfig;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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

    @Autowired
    private EtatUtilisateurService etatUtilisateurService;

    @Autowired
    private VHistoriqueCongeService vHistoriqueCongeService;

    @Autowired
    private PaginationConfig paginationConfig;

    @Autowired
    private PasswordService passwordService;

	@PostMapping("verifierLogin")
    public String Login(@RequestParam String mail, @RequestParam String mdp, HttpSession session, HttpServletRequest request) {
        try {
            int etat_utilisateur_active = etatUtilisateurService.getEtatActive();
            DetailUtilisateur user = detailUtilisateurService.getByUser(mail, etat_utilisateur_active);

            if (user == null) {
                throw new Exception("Authentification invalide");
            }

            // Vérifier si le mot de passe correspond
            if (!BCrypt.checkpw(mdp, user.getMdp())) { // Assurez-vous que getMdp() retourne le mot de passe chiffré
                throw new Exception("Mot de passe non valide");
            }

            if (user.getMdpProvisoir() != null && user.getMdpProvisoir()) {
                // Rediriger vers la page de modification du mot de passe si le mot de passe est provisoire
                session.setAttribute("userId", user.getUtilisateur().getId());
                return "authentification/modifier-mdp"; // Page pour modifier le mot de passe
            }
            
            byte[] imageBytes = user.getUtilisateur().getImage();
            if (imageBytes != null && imageBytes.length > 0) {
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                session.setAttribute("image", imageBase64);
            }

            session.setAttribute("userId", user.getUtilisateur().getId());
            session.setAttribute("userDirectionId", user.getFonction().getDirection().getId());
            session.setAttribute("userType", user.getUtilisateur().getTypeUtilisateur());
            return "redirect:/detail_utilisateur/page-conge";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Mot de passe ou email non valide");
            return "authentification/login";
        }
    }

    @PostMapping("/modifier-mdp-provisoir")
    public String modifierMotDePasse(@RequestParam String nouveauMdp, 
                                     @RequestParam String confirmerMdp, 
                                     HttpSession session, 
                                     HttpServletRequest request) {
        try {
            String userId = (String) session.getAttribute("userId");

            if (userId == null) {
                // Rediriger vers la page de login si l'utilisateur n'est pas connecté
                return "authentification/login";
            }

            DetailUtilisateur detailUtilisateur = detailUtilisateurService.findByIdUtilisateur(userId);

            if (!nouveauMdp.equals(confirmerMdp)) {
                // Les mots de passe ne correspondent pas
                request.setAttribute("errorMessage", "Les mots de passe ne correspondent pas.");
                return "authentification/login";
            }

            // Mettre à jour le mot de passe de l'utilisateur
            Optional<DetailUtilisateur> utilisateurOptional = detailUtilisateurService.getOne(detailUtilisateur.getId());
            DetailUtilisateur utilisateur = utilisateurOptional.get();
            String mdp_cripter = passwordService.encryptPassword(nouveauMdp);
            utilisateur.setMdp(mdp_cripter);
            utilisateur.setMdpProvisoir(false);  // Mettre à jour pour indiquer que le mot de passe n'est plus provisoire
            detailUtilisateurService.create(utilisateur);

            // Rediriger vers une autre page après la mise à jour (ex: page d'accueil)
            return "redirect:/detail_utilisateur/page-conge";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erreur lors de la modification du mot de passe.");
            return "authentification/modifier-mdp";
        }
    }
    
    @GetMapping("page-conge")
    public String Accueil(HttpServletRequest request, HttpSession session,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(required = false) Integer size) {
        String idUtilisateur = (String) session.getAttribute("userId");

        // Si size n'est pas spécifié dans l'URL, utilisez la valeur par défaut
        int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();

        Pageable pageable = PageRequest.of(page, paginationSize);
        
        // Récupérer les demandes avec pagination
        Page<VEtatDemande> etatDemandesPage = vEtatDemandeService.getByIdUtilisateur(idUtilisateur, pageable);
        
        List<TypeConge> typeConges = typeCongeService.getAll();
        List<TypeAbsence> typeAbsence = typeAbsenceService.getAll();
        String message = request.getParameter("message");
        String type = request.getParameter("type");

        String idConge = "TCG001";
        String idAutorisation = "TCG002";
        
        VHistoriqueConge historiqueConge = vHistoriqueCongeService.historiqueCongeParUtilisateur(idUtilisateur, idConge);
        VHistoriqueConge historiquePersmission = vHistoriqueCongeService.historiqueCongeParUtilisateur(idUtilisateur, idAutorisation);
        
        // Attributs pour la vue
        request.setAttribute("etatDemandesPage", etatDemandesPage);
        request.setAttribute("totalPages", etatDemandesPage.getTotalPages());
        request.setAttribute("currentPage", etatDemandesPage.getNumber());
        request.setAttribute("size", paginationSize);
        
        request.setAttribute("typeConge", typeConges);
        request.setAttribute("typeAbsence", typeAbsence);
        request.setAttribute("historiqueConge", historiqueConge);
        request.setAttribute("historiquePersmission", historiquePersmission);
        request.setAttribute("message", message);
        request.setAttribute("type", type);
        
        return "conge/conge";
    }
}