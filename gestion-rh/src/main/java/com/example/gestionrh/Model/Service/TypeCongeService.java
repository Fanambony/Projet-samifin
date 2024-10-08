package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.TypeCongeRepository;
import com.example.gestionrh.Model.Entity.TypeConge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TypeCongeService {

	@Autowired
	private TypeCongeRepository typeCongeRepository;

	/* -- READ ONE -- */
	public Optional<TypeConge> getOne(Object id) { return typeCongeRepository.findById(id); }

	/* -- READ -- */
	public List<TypeConge> getAll() { return typeCongeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(TypeConge TypeConge) {  typeCongeRepository.save(TypeConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  typeCongeRepository.deleteById(id); }

}
