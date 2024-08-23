package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.FonctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("fonction")
public class FonctionController{

	@Autowired
	private FonctionService fonctionService;

}
