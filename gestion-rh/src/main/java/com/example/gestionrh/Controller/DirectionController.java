package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Service.DirectionService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("direction")
public class DirectionController{

	@Autowired
	private DirectionService directionService;

	// @GetMapping("gerer-direction")
	// public String gererDirection(HttpServletRequest request) {
	// 	List<Direction> directions = directionService.getAll();
	// 	request.setAttribute("directions", directions);
	// 	return "/direction/gestion-direction";
	// }
	
}
