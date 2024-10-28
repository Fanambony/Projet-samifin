package com.example.gestionrh.Context;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gestionrh.Model.Entity.SoldeUtilisateur;

public interface SoldeUtilisateurRepository extends JpaRepository<SoldeUtilisateur, Object> {
    @Query("SELECT DISTINCT s.annee, s.dateGenerer FROM SoldeUtilisateur s GROUP BY s.annee, s.dateGenerer")
    List<Object[]> findDistinctAnneeAndDateGenerer();
    boolean existsByAnnee(int annee);
}