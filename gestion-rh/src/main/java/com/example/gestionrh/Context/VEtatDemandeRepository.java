package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.gestionrh.Model.Entity.VEtatDemande;

public interface VEtatDemandeRepository extends JpaRepository<VEtatDemande, Object> {
    Page<VEtatDemande> findByIdUtilisateur(String idUtilisateur, Pageable pageable);
    List<VEtatDemande> findByIdDirectionAndEtatUtilisateurAndIdEtatDemande(String idDirection, int etatUtilisateur, int etatemande);
    List<VEtatDemande> findByEtatUtilisateurAndIdEtatDemande(int etatUtilisateur, int etatemande);
    @Query("SELECT v FROM VEtatDemande v WHERE v.idEtatDemande = :idEtatDemande AND v.dateDebut > CURRENT_DATE")
    Page<VEtatDemande> findByIdEtatDemandeAndDateDebutAfter(@Param("idEtatDemande") int idEtatDemande, Pageable pageable);
    List<VEtatDemande> findByIdEtatDemande(int idEtatDemande);
    
}