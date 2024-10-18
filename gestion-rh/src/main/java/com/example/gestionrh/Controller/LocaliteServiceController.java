package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.LocaliteService;
import com.example.gestionrh.Model.Service.LocaliteServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("localite_service")
public class LocaliteServiceController{

	private final LocaliteServiceService localiteServiceService;

	public LocaliteServiceController(LocaliteServiceService localiteServiceService){this.localiteServiceService = localiteServiceService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<LocaliteService> getOne(@PathVariable String id) { 
		 Optional<LocaliteService> localiteService = this.localiteServiceService.getOne(id);
		return localiteService;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<LocaliteService> getAll() { 
		List<LocaliteService> listLocaliteService = this.localiteServiceService.getAll();
		return listLocaliteService;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody LocaliteService formData) {
		LocaliteService localiteService = new LocaliteService();
		localiteServiceService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody LocaliteService formData) {
		LocaliteService localiteService = new LocaliteService();
		localiteServiceService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		localiteServiceService.delete(id);
	}
}
