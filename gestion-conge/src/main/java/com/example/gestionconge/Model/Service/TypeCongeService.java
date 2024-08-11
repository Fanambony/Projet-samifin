package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.TypeCongeRepository;
import com.example.gestionconge.Model.Entity.TypeConge;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class TypeCongeService {

	private final TypeCongeRepository typeCongeRepository;

	public TypeCongeService(TypeCongeRepository typeCongeRepository) {this.typeCongeRepository = typeCongeRepository;}



	/* -- READ ONE -- */
	public Optional<TypeConge> getOne(Object id) { return typeCongeRepository.findById(id); }

	/* -- READ -- */
	public List<TypeConge> getAll() { return typeCongeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(TypeConge TypeConge) {  typeCongeRepository.save(TypeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  typeCongeRepository.deleteById(id); }

}
