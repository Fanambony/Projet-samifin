package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Famille;

public interface FamilleRepository extends JpaRepository<Famille, Object> {
    List<Famille> findByIdEmploye(String idEmploye);
}