package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VHistoriqueCongeRepository;
import com.example.gestionrh.Model.Entity.VHistoriqueConge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VHistoriqueCongeService {

	@Autowired
	private VHistoriqueCongeRepository vHistoriqueCongeRepository;

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

	public Page<VHistoriqueConge> getHistoriqueConge(Pageable pageable) {
        return vHistoriqueCongeRepository.findAll(pageable);
    }

	public List<VHistoriqueConge> getByIdUtilisateur(String idutilisateur) {
		return vHistoriqueCongeRepository.findByIdUtilisateur(idutilisateur);
	}

	public List<VHistoriqueConge> getByIdTypConges(String idTypeConge) {
		return vHistoriqueCongeRepository.findByIdTypeConge(idTypeConge);
	}
}