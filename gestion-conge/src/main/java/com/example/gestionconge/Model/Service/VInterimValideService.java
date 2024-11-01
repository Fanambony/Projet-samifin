package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.VInterimValideRepository;
import com.example.gestionconge.Model.Entity.VInterimValide;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VInterimValideService {

	private final VInterimValideRepository vInterimValideRepository;

	public VInterimValideService(VInterimValideRepository vInterimValideRepository) {this.vInterimValideRepository = vInterimValideRepository;}



	/* -- READ ONE -- */
	public Optional<VInterimValide> getOne(Object id) { return vInterimValideRepository.findById(id); }

	/* -- READ -- */
	public List<VInterimValide> getAll() { return vInterimValideRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VInterimValide VInterimValide) {  vInterimValideRepository.save(VInterimValide); }

	/* -- DELETE -- */
	public void delete(Object id) {  vInterimValideRepository.deleteById(id); }

}
