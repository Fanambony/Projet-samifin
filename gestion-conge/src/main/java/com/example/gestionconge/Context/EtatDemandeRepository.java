package com.example.gestionconge.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionconge.Model.Entity.EtatDemande;

public interface EtatDemandeRepository extends JpaRepository<EtatDemande, Object> {

}