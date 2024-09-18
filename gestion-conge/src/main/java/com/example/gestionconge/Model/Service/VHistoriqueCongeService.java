package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.VHistoriqueCongeRepository;
import com.example.gestionconge.Model.Entity.VHistoriqueConge;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VHistoriqueCongeService {

	private final VHistoriqueCongeRepository vHistoriqueCongeRepository;

	public VHistoriqueCongeService(VHistoriqueCongeRepository vHistoriqueCongeRepository) {this.vHistoriqueCongeRepository = vHistoriqueCongeRepository;}



	/* -- READ ONE -- */
	public Optional<VHistoriqueConge> getOne(Object id) { return vHistoriqueCongeRepository.findById(id); }

	/* -- READ -- */
	public List<VHistoriqueConge> getAll() { return vHistoriqueCongeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VHistoriqueConge VHistoriqueConge) {  vHistoriqueCongeRepository.save(VHistoriqueConge); }

	/* -- DELETE -- */
	public void delete(Object id) {  vHistoriqueCongeRepository.deleteById(id); }

}
