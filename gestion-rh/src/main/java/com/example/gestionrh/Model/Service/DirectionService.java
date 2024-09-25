package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.DirectionRepository;
import com.example.gestionrh.Model.Entity.Direction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class DirectionService {

	@Autowired
	private DirectionRepository directionRepository;

	/* -- READ ONE -- */
	public Optional<Direction> getOne(Object id) { return directionRepository.findById(id); }

	/* -- READ -- */
	public List<Direction> getAll() { return directionRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Direction Direction) {  directionRepository.save(Direction); }

	/* -- DELETE -- */
	public void delete(Object id) {  directionRepository.deleteById(id); }

}
