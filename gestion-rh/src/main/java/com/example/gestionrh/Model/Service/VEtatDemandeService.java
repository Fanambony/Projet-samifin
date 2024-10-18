package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VEtatDemandeRepository;
import com.example.gestionrh.Model.Entity.VEtatDemande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;



@Service
public class VEtatDemandeService {

	@Autowired
	private VEtatDemandeRepository vEtatDemandeRepository;

	/* -- READ ONE -- */
	public Optional<VEtatDemande> getOne(Object id) { return vEtatDemandeRepository.findById(id); }

	/* -- READ -- */
	public List<VEtatDemande> getAll() { return vEtatDemandeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VEtatDemande VEtatDemande) {  vEtatDemandeRepository.save(VEtatDemande); }

	/* -- DELETE -- */
	public void delete(Object id) {  vEtatDemandeRepository.deleteById(id); }

	public Page<VEtatDemande> getByIdUtilisateur(String idUtilisateur, Pageable pageable) {
        return vEtatDemandeRepository.findByIdUtilisateur(idUtilisateur, pageable);
    }

	public List<VEtatDemande> demandeCongeParidDirectionParTypeUtilisateur(String idDirection, int etatUtilisateur, int etatemande) {
		return vEtatDemandeRepository.findByIdDirectionAndEtatUtilisateurAndIdEtatDemande(idDirection, etatUtilisateur, etatemande);
	}

	public List<VEtatDemande> demandeCongeParTypeUtilisateur(int etatUtilisateur, int etatemande) {
		return vEtatDemandeRepository.findByEtatUtilisateurAndIdEtatDemande(etatUtilisateur, etatemande);
	}

	// prendre demande valider la date debut est superieur a la date aujourd'hui qui doit etre annuler
	public Page<VEtatDemande> findByIdEtatDemandeAndDateDebutAfter(int etatDemande, Pageable pageable) {
		return vEtatDemandeRepository.findByIdEtatDemandeAndDateDebutAfter(etatDemande, pageable);
	}

	public List<VEtatDemande> demandeValiderParUtilisateur(int idEtatDemande) {
		return vEtatDemandeRepository.findByIdEtatDemande(idEtatDemande);
	}

	// public List<VEtatDemande> demandeValiderParUtilisateur(String idUtilisateur, int idEtatDemande) {
	// 	return vEtatDemandeRepository.findByIdUttilisateurAndIdEtatDemande(idUtilisateur, idEtatDemande);
	// }
}
