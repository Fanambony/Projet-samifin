package com.example.gestionconge.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionconge.Model.Entity.DemandeConge;

public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Object> {

}