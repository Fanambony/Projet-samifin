package com.example.gestionconge.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionconge.Model.Entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Object> {

}