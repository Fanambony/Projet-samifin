package com.example.gestionrh.Context;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Object> {
    Page<Utilisateur> findAll(Pageable pageable);
}