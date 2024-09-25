package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.GenreRepository;
import com.example.gestionrh.Model.Entity.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	/* -- READ ONE -- */
	public Optional<Genre> getOne(Object id) { return genreRepository.findById(id); }

	/* -- READ -- */
	public List<Genre> getAll() { return genreRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Genre Genre) {  genreRepository.save(Genre); }

	/* -- DELETE -- */
	public void delete(Object id) {  genreRepository.deleteById(id); }

}
