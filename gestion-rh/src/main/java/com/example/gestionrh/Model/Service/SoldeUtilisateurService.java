package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.SoldeUtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldeUtilisateurService {

	@Autowired
	private SoldeUtilisateurRepository soldeUtilisateurRepository;
}
