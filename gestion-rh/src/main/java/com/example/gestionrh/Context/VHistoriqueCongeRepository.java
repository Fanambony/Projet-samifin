package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;

public interface VHistoriqueCongeRepository extends JpaRepository<VHistoriqueConge, Object> {
    VHistoriqueConge findByIdUtilisateurAndIdTypeConge(String idUtilisateur, String idTypeConge);
}