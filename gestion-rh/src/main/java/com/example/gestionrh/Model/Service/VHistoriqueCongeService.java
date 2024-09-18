package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VHistoriqueCongeRepository;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;
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

	public VHistoriqueConge historiqueCongeParUtilisateur(String idUtilisateur, String idTypeConge) {
		return vHistoriqueCongeRepository.findByIdUtilisateurAndIdTypeConge(idUtilisateur, idTypeConge);
	}
}