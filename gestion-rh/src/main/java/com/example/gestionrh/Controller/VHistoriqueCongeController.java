package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VHistoriqueConge;
import com.example.gestionrh.Model.Service.VHistoriqueCongeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("v_historique_conge")
public class VHistoriqueCongeController{

	private final VHistoriqueCongeService vHistoriqueCongeService;

	public VHistoriqueCongeController(VHistoriqueCongeService vHistoriqueCongeService){this.vHistoriqueCongeService = vHistoriqueCongeService;}



	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VHistoriqueConge> getOne(@PathVariable Long numLigne) { 
		 Optional<VHistoriqueConge> vHistoriqueConge = this.vHistoriqueCongeService.getOne(numLigne);
		return vHistoriqueConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VHistoriqueConge> getAll() { 
		List<VHistoriqueConge> listVHistoriqueConge = this.vHistoriqueCongeService.getAll();
		return listVHistoriqueConge;
	}
}