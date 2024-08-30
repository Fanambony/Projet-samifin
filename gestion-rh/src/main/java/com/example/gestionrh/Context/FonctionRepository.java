package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Fonction;

public interface FonctionRepository extends JpaRepository<Fonction, Object> {
    List<Fonction> findByIdDirection(String idDirection);
}