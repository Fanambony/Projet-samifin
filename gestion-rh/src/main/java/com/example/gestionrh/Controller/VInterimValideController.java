package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VInterimValide;
import com.example.gestionrh.Model.Service.VInterimValideService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("v_interim_valide")
public class VInterimValideController{

	private final VInterimValideService vInterimValideService;

	public VInterimValideController(VInterimValideService vInterimValideService){this.vInterimValideService = vInterimValideService;}



	/* -- READ ONE -- */
	@GetMapping("/{idInterimUtilisateur}")
	public Optional<VInterimValide> getOne(@PathVariable String idInterimUtilisateur) { 
		 Optional<VInterimValide> vInterimValide = this.vInterimValideService.getOne(idInterimUtilisateur);
		return vInterimValide;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VInterimValide> getAll() { 
		List<VInterimValide> listVInterimValide = this.vInterimValideService.getAll();
		return listVInterimValide;
	}
}
