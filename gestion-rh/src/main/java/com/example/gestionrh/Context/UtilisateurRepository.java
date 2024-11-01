package com.example.gestionrh.Context;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestionrh.Model.Entity.Utilisateur;

import java.util.List;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Object> {
    Page<Utilisateur> findAll(Pageable pageable);
    // Page<Utilisateur> findByEtatAndTypeUtilisateur(Pageable pageable, int etat, int typeUtilisateur);
    @Query("SELECT u FROM Utilisateur u WHERE u.etat != :etat AND u.typeUtilisateur != :typeUtilisateur")
    Page<Utilisateur> findAllExcludingEtatAndType(@Param("etat") int etat, @Param("typeUtilisateur") int typeUtilisateur, Pageable pageable);
    // List<Utilisateur> findByEtat(Integer etat);
    List<Utilisateur> findByEtatAndTypeUtilisateurNot(Integer etat, Integer typeUtilisateur);


}