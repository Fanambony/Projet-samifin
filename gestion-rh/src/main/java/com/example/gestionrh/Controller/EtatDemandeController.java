package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.EtatDemandeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("etat_demande")
public class EtatDemandeController{

	@Autowired
	private EtatDemandeService etatDemandeService;

}
