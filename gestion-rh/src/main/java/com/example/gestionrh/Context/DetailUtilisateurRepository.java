package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.DetailUtilisateur;

public interface DetailUtilisateurRepository extends JpaRepository<DetailUtilisateur, Object> {
    DetailUtilisateur findByEmailAndMdp(String email, String mdp);
}