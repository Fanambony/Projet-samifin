package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.NombreCongeAnneeRepository;
import com.example.gestionconge.Model.Entity.NombreCongeAnnee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NombreCongeAnneeService {

	private final NombreCongeAnneeRepository nombreCongeAnneeRepository;

	public NombreCongeAnneeService(NombreCongeAnneeRepository nombreCongeAnneeRepository) {this.nombreCongeAnneeRepository = nombreCongeAnneeRepository;}



	/* -- READ ONE -- */
	public Optional<NombreCongeAnnee> getOne(Object id) { return nombreCongeAnneeRepository.findById(id); }

	/* -- READ -- */
	public List<NombreCongeAnnee> getAll() { return nombreCongeAnneeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(NombreCongeAnnee NombreCongeAnnee) {  nombreCongeAnneeRepository.save(NombreCongeAnnee); }

	/* -- DELETE -- */
	public void delete(Object id) {  nombreCongeAnneeRepository.deleteById(id); }

}
