package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.TypeUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("type_utilisateur")
public class TypeUtilisateurController{

	@Autowired
	private TypeUtilisateurService typeUtilisateurService;

}