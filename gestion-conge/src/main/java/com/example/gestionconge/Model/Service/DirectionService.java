package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.DirectionRepository;
import com.example.gestionconge.Model.Entity.Direction;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class DirectionService {

	private final DirectionRepository directionRepository;

	public DirectionService(DirectionRepository directionRepository) {this.directionRepository = directionRepository;}



	/* -- READ ONE -- */
	public Optional<Direction> getOne(Object id) { return directionRepository.findById(id); }

	/* -- READ -- */
	public List<Direction> getAll() { return directionRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Direction Direction) {  directionRepository.save(Direction); }

	/* -- DELETE -- */
	public void delete(Object id) {  directionRepository.deleteById(id); }

}
