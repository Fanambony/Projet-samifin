package com.example.gestionrh.Context;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;
import java.util.List;

public interface VUtilisateurDetaillerRepository extends JpaRepository<VUtilisateurDetailler, Object> {
    List<VUtilisateurDetailler> findByEtatTypeUtilisateurAndIdDirection(int typeUtilisateur, String idDirection);
    List<VUtilisateurDetailler> findByEtatTypeUtilisateur(int typeUtilisateur);
    List<VUtilisateurDetailler> findByIdDirectionAndIdUtilisateurNotAndEtatUtilisateurNotAndEtatTypeUtilisateurNot(String idDirection, String idUtilisateur, int etatDesactiver, int etat_admin);
    Page<VUtilisateurDetailler> findAll(Pageable pageable);
    VUtilisateurDetailler findByIdUtilisateur(String idUtilisateur);

    @Query(value = "SELECT * FROM v_utilisateur_detailler u " +
               "WHERE u.nom ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.prenom ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.matricule ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.direction ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.etat ILIKE CONCAT('%', %:searchTerm%, '%')", 
       nativeQuery = true)
    List<VUtilisateurDetailler> findBySearchTerm(@Param("searchTerm") String searchTerm);


    @Query(value = "SELECT * FROM v_utilisateur_detailler u " +
               "WHERE u.id_direction = :idDirection AND "+
               "u.nom ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.prenom ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.matricule ILIKE CONCAT('%', %:searchTerm%, '%') " +
               "OR u.direction ILIKE CONCAT('%', %:searchTerm%, '%')" ,
       nativeQuery = true)
    List<VUtilisateurDetailler> findBySearchEtatConge(@Param("idDirection") String idDirection, @Param("searchTerm") String searchTerm);

    @Query("SELECT v FROM VUtilisateurDetailler v WHERE v.etatUtilisateur != :etat AND v.etatTypeUtilisateur != :typeUtilisateur")
    Page<VUtilisateurDetailler> findAllExcludingEtatAndType(@Param("etat") int etat, @Param("typeUtilisateur") int typeUtilisateur, Pageable pageable);

    @Query("SELECT v FROM VUtilisateurDetailler v WHERE v.etatUtilisateur != :etat AND v.etatTypeUtilisateur != :typeUtilisateur AND idDirection = :idDirection")
    Page<VUtilisateurDetailler> findAllExcludingEtatAndTypeParDirection(@Param("etat") int etat, @Param("typeUtilisateur") int typeUtilisateur, String idDirection, Pageable pageable);
}