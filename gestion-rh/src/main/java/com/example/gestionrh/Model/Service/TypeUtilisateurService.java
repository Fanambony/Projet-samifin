package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.TypeUtilisateurRepository;
import com.example.gestionrh.Model.Entity.TypeUtilisateur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TypeUtilisateurService {

	private final TypeUtilisateurRepository typeUtilisateurRepository;

	public TypeUtilisateurService(TypeUtilisateurRepository typeUtilisateurRepository) {this.typeUtilisateurRepository = typeUtilisateurRepository;}



	/* -- READ ONE -- */
	public Optional<TypeUtilisateur> getOne(Object id) { return typeUtilisateurRepository.findById(id); }

	/* -- READ -- */
	public List<TypeUtilisateur> getAll() { return typeUtilisateurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(TypeUtilisateur TypeUtilisateur) {  typeUtilisateurRepository.save(TypeUtilisateur); }

	/* -- DELETE -- */
	public void delete(Object id) {  typeUtilisateurRepository.deleteById(id); }

}
