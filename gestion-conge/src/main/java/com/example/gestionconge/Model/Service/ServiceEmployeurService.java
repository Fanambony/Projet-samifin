package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.ServiceEmployeurRepository;
import com.example.gestionconge.Model.Entity.ServiceEmployeur;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class ServiceEmployeurService {

	private final ServiceEmployeurRepository serviceEmployeurRepository;

	public ServiceEmployeurService(ServiceEmployeurRepository serviceEmployeurRepository) {this.serviceEmployeurRepository = serviceEmployeurRepository;}



	/* -- READ ONE -- */
	public Optional<ServiceEmployeur> getOne(Object id) { return serviceEmployeurRepository.findById(id); }

	/* -- READ -- */
	public List<ServiceEmployeur> getAll() { return serviceEmployeurRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(ServiceEmployeur ServiceEmployeur) {  serviceEmployeurRepository.save(ServiceEmployeur); }

	/* -- DELETE -- */
	public void delete(Object id) {  serviceEmployeurRepository.deleteById(id); }

}
