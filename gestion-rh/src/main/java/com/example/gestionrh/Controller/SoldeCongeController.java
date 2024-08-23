package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.SoldeCongeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("solde_conge")
public class SoldeCongeController{

	@Autowired
	private SoldeCongeService soldeCongeService;

}
