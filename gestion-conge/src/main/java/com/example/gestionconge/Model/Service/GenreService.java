package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.GenreRepository;
import com.example.gestionconge.Model.Entity.Genre;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class GenreService {

	private final GenreRepository genreRepository;

	public GenreService(GenreRepository genreRepository) {this.genreRepository = genreRepository;}



	/* -- READ ONE -- */
	public Optional<Genre> getOne(Object id) { return genreRepository.findById(id); }

	/* -- READ -- */
	public List<Genre> getAll() { return genreRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Genre Genre) {  genreRepository.save(Genre); }

	/* -- DELETE -- */
	public void delete(Object id) {  genreRepository.deleteById(id); }

}
