package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Service.DirectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("direction")
public class DirectionController{

	private final DirectionService directionService;

	public DirectionController(DirectionService directionService){this.directionService = directionService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Direction> getOne(@PathVariable String id) { 
		 Optional<Direction> direction = this.directionService.getOne(id);
		return direction;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Direction> getAll() { 
		List<Direction> listDirection = this.directionService.getAll();
		return listDirection;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Direction formData) {
		Direction direction = new Direction();
		directionService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Direction formData) {
		Direction direction = new Direction();
		directionService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		directionService.delete(id);
	}
}
