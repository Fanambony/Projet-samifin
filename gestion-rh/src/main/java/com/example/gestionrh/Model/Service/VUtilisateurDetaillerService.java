package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VUtilisateurDetaillerRepository;
import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VUtilisateurDetaillerService {

	private final VUtilisateurDetaillerRepository vUtilisateurDetaillerRepository;

	public VUtilisateurDetaillerService(VUtilisateurDetaillerRepository vUtilisateurDetaillerRepository) {this.vUtilisateurDetaillerRepository = vUtilisateurDetaillerRepository;}



	/* -- READ ONE -- */
	public Optional<VUtilisateurDetailler> getOne(Object id) { return vUtilisateurDetaillerRepository.findById(id); }

	/* -- READ -- */
	public List<VUtilisateurDetailler> getAll() { return vUtilisateurDetaillerRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VUtilisateurDetailler VUtilisateurDetailler) {  vUtilisateurDetaillerRepository.save(VUtilisateurDetailler); }

	/* -- DELETE -- */
	public void delete(Object id) {  vUtilisateurDetaillerRepository.deleteById(id); }

	public List<VUtilisateurDetailler> getByTypeUtilisateurEtDireciton(int typeUtilisateur, String idDirection) {
		return vUtilisateurDetaillerRepository.findByEtatTypeUtilisateurAndIdDirection(typeUtilisateur, idDirection);
	}

	public List<VUtilisateurDetailler> getByTypeUtilisateur(int typeUtilisateur) {
		return vUtilisateurDetaillerRepository.findByEtatTypeUtilisateur(typeUtilisateur);
	}

}
