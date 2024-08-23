package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.VEtatDemande;

public interface VEtatDemandeRepository extends JpaRepository<VEtatDemande, Object> {
    List<VEtatDemande> findByIdUtilisateur(String idUtilisateur);
    List<VEtatDemande> findByIdDirectionAndEtatUtilisateurAndIdEtatDemande(String idDirection, int etatUtilisateur, int etatemande);
    List<VEtatDemande> findByEtatUtilisateurAndIdEtatDemande(int etatUtilisateur, int etatemande);
}