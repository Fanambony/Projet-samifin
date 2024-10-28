package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;
import com.example.gestionrh.Model.Service.VUtilisateurDetaillerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("v_utilisateur_detailler")
public class VUtilisateurDetaillerController{

	@Autowired
	private VUtilisateurDetaillerService vUtilisateurDetaillerService;

	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VUtilisateurDetailler> getOne(@PathVariable Long numLigne) { 
		 Optional<VUtilisateurDetailler> vUtilisateurDetailler = this.vUtilisateurDetaillerService.getOne(numLigne);
		return vUtilisateurDetailler;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VUtilisateurDetailler> getAll() { 
		List<VUtilisateurDetailler> listVUtilisateurDetailler = this.vUtilisateurDetaillerService.getAll();
		return listVUtilisateurDetailler;
	}

	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vUtilisateurDetaillerService.delete(numLigne);
	}
}
