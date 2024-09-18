package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.SoldeUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("solde_utilisateur")
public class SoldeUtilisateurController{

	@Autowired
	private SoldeUtilisateurService soldeUtilisateurService;

}
