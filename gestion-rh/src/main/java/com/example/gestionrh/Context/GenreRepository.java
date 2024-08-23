package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Object> {

}