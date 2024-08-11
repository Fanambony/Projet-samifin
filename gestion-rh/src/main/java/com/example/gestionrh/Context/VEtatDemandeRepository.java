package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.VEtatDemande;

public interface VEtatDemandeRepository extends JpaRepository<VEtatDemande, Object> {
    List<VEtatDemande> findByIdUtilisateur(String idUtilisateur);
    List<VEtatDemande> findByIdDirectionAndEtatUtilisateur(String idDirection, int etatUtilisateur);
}