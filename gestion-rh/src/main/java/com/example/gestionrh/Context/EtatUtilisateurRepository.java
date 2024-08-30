package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gestionrh.Model.Entity.EtatUtilisateur;

public interface EtatUtilisateurRepository extends JpaRepository<EtatUtilisateur, Object> {
    @Query("SELECT MIN(e.etat) FROM EtatUtilisateur e")
    Integer findEtatActive();
}