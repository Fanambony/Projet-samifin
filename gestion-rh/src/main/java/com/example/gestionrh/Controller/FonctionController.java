package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Service.FonctionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fonction")
public class FonctionController{

	@Autowired
	private FonctionService fonctionService;

	@GetMapping("/getFonctionsByDirection")
	@ResponseBody
	public List<Fonction> fonctionParDirection(@RequestParam("directionId") String direction) {
		return fonctionService.fonctionParDirection(direction);
	}
	
}
