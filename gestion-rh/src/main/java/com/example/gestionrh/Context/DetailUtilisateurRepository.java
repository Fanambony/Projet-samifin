package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;

public interface DetailUtilisateurRepository extends JpaRepository<DetailUtilisateur, Object> {
    // DetailUtilisateur findByEmailAndMdp(String email, String mdp);
    // DetailUtilisateur findByEmailAndMdpAndUtilisateur_Etat(String email, String mdp, Integer etat);
    DetailUtilisateur findByEmailAndUtilisateur_Etat(String email, Integer etat);

    boolean existsByMatricule(String matricule);
    DetailUtilisateur findByIdUtilisateur(String idUtilisateur);
}