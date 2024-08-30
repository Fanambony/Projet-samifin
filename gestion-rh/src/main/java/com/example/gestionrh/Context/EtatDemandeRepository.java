package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gestionrh.Model.Entity.EtatDemande;

public interface EtatDemandeRepository extends JpaRepository<EtatDemande, Object> {
    @Query("SELECT MIN(e.etat) FROM EtatDemande e")
    Integer findEtatSoumis();
}