package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.ServiceEmployeurRepository;
import com.example.gestionrh.Model.Entity.ServiceEmployeur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployeurService {

	@Autowired
	private ServiceEmployeurRepository serviceEmployeurRepository;

	/* -- READ ONE -- */
	public Optional<ServiceEmployeur> getOne(Object id) { return serviceEmployeurRepository.findById(id); }

	/* -- READ -- */
	public List<ServiceEmployeur> getAll() { return serviceEmployeurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(ServiceEmployeur ServiceEmployeur) {  serviceEmployeurRepository.save(ServiceEmployeur); }

	/* -- DELETE -- */
	public void delete(Object id) {  serviceEmployeurRepository.deleteById(id); }

}
