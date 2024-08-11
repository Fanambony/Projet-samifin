package com.example.gestionconge.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionconge.Model.Entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Object> {

}