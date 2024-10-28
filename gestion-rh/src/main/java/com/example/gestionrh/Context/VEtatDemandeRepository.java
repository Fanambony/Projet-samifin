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
    
    // @Query("SELECT v FROM VEtatDemande v WHERE v.idEtatDemande = :idEtatDemande AND v.dateDebut > CURRENT_DATE")
    // Page<VEtatDemande> findByIdEtatDemandeAndDateDebutAfter(@Param("idEtatDemande") int idEtatDemande, Pageable pageable);
    Page<VEtatDemande> findByIdEtatDemande(int idEtatDemande, Pageable pageable);

    VEtatDemande findByIdDemandeConge(String idDemande);
    


    List<VEtatDemande> findByIdEtatDemande(int idEtatDemande);
    // @Query("SELECT v FROM VEtatDemande v WHERE v.idUtilisateur = :idUtilisateur AND " +
    //     "(v.typeConge LIKE %:searchTerm% OR v.etatDemande LIKE %:searchTerm%)")
    // List<VEtatDemande> findByUtilisateurAndSearchTerm(@Param("idUtilisateur") String idUtilisateur,
    //                                                 @Param("searchTerm") String searchTerm);
    // @Query("SELECT v FROM VEtatDemande v WHERE v.idUtilisateur = :idUtilisateur AND " +
    //     "(v.typeConge LIKE %:searchTerm% OR v.etatDemande LIKE %:searchTerm% OR " +
    //     "CAST(v.nombreJoursConge AS string) LIKE %:searchTerm% OR " +
    //     "CAST(v.dateDemande AS string) LIKE %:searchTerm%)")
    // List<VEtatDemande> findByUtilisateurAndSearchTerm(@Param("idUtilisateur") String idUtilisateur,
    //                                                 @Param("searchTerm") String searchTerm);
    @Query(value = "SELECT * FROM v_etat_demande v WHERE v.id_utilisateur = :idUtilisateur AND " +
               "(v.type_conge LIKE %:searchTerm% OR v.etat_demande LIKE %:searchTerm% OR " +
               "CAST(v.nombre_jours_conge AS CHAR) LIKE %:searchTerm% OR " +
               "TO_CHAR(v.date_demande, 'DD FMMonth YYYY') LIKE %:searchTerm%)", 
        nativeQuery = true)
    List<VEtatDemande> findByUtilisateurAndSearchTerm(@Param("idUtilisateur") String idUtilisateur,
                                                  @Param("searchTerm") String searchTerm);

    



    
}