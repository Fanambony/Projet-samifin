package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;

public interface VHistoriqueCongeRepository extends JpaRepository<VHistoriqueConge, Object> {
    VHistoriqueConge findByIdUtilisateurAndIdTypeConge(String idUtilisateur, String idTypeConge);
    List<VHistoriqueConge> findByIdUtilisateur(String idUtilisateur);
    Page<VHistoriqueConge> findAll(Pageable pageable);
    List<VHistoriqueConge> findByIdTypeConge(String idTypeConge);
}