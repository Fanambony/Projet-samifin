package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Service.TypeCongeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("type_conge")
public class TypeCongeController{

	@Autowired
	private TypeCongeService typeCongeService;

}