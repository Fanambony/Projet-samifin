package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VUtilisateurDetaillerRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<VUtilisateurDetailler> getByIdDirection(String idDirection, String idUtilisateur, int etatDesactiver) {
		return vUtilisateurDetaillerRepository.findByIdDirectionAndIdUtilisateurNotAndEtatUtilisateurNot(idDirection, idUtilisateur, etatDesactiver);
	}

	public Page<VUtilisateurDetailler> getAll(Pageable pageable) {
		return vUtilisateurDetaillerRepository.findAll(pageable);
	}

    public List<VUtilisateurDetailler> searchUtilisateurs(String searchTerm) {
        return vUtilisateurDetaillerRepository.findBySearchTerm(searchTerm);
    }

	public List<VUtilisateurDetailler> searchUtilisateursEtatConge(String idDirection, String searchTerm) {
        return vUtilisateurDetaillerRepository.findBySearchEtatConge(idDirection, searchTerm);
    }

	public Page<VUtilisateurDetailler> getDetailUtilisateurs(int etat, int typeUtilisateur, Pageable pageable) {
        return vUtilisateurDetaillerRepository.findAllExcludingEtatAndType(etat, typeUtilisateur, pageable);
    }

	public Page<VUtilisateurDetailler> getDetailUtilisateursParDirection(int etat, int typeUtilisateur, String idDirection, Pageable pageable) {
        return vUtilisateurDetaillerRepository.findAllExcludingEtatAndTypeParDirection(etat, typeUtilisateur, idDirection, pageable);
    }
}
