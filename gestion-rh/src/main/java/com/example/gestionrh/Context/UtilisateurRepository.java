package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Object> {
    Utilisateur findByEmailAndMdp(String email, String mdp);
}