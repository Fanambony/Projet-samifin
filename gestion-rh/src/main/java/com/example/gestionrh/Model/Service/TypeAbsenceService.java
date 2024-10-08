package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.TypeAbsenceRepository;
import com.example.gestionrh.Model.Entity.TypeAbsence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class TypeAbsenceService {

	@Autowired
	private TypeAbsenceRepository typeAbsenceRepository;

	/* -- READ ONE -- */
	public Optional<TypeAbsence> getOne(Object id) { return typeAbsenceRepository.findById(id); }

	/* -- READ -- */
	public List<TypeAbsence> getAll() { return typeAbsenceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(TypeAbsence TypeAbsence) {  typeAbsenceRepository.save(TypeAbsence); }

	/* -- DELETE -- */
	public void delete(Object id) {  typeAbsenceRepository.deleteById(id); }

}
