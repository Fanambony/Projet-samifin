package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.TypeUtilisateur;

public interface TypeUtilisateurRepository extends JpaRepository<TypeUtilisateur, Object> {
    TypeUtilisateur findByEtat(int etat);
}