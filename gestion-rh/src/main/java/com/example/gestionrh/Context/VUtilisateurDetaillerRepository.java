package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;
import java.util.List;

public interface VUtilisateurDetaillerRepository extends JpaRepository<VUtilisateurDetailler, Object> {
    List<VUtilisateurDetailler> findByEtatTypeUtilisateurAndIdDirection(int typeUtilisateur, String idDirection);
    List<VUtilisateurDetailler> findByEtatTypeUtilisateur(int typeUtilisateur);
}