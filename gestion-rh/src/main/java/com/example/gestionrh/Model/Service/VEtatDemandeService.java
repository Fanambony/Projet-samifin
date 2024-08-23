package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VEtatDemandeRepository;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class VEtatDemandeService {

	private final VEtatDemandeRepository vEtatDemandeRepository;

	public VEtatDemandeService(VEtatDemandeRepository vEtatDemandeRepository) {this.vEtatDemandeRepository = vEtatDemandeRepository;}



	/* -- READ ONE -- */
	public Optional<VEtatDemande> getOne(Object id) { return vEtatDemandeRepository.findById(id); }

	/* -- READ -- */
	public List<VEtatDemande> getAll() { return vEtatDemandeRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VEtatDemande VEtatDemande) {  vEtatDemandeRepository.save(VEtatDemande); }

	/* -- DELETE -- */
	public void delete(Object id) {  vEtatDemandeRepository.deleteById(id); }

	public List<VEtatDemande> getByIdUtilisateur(String idUtilisateur) {
		List<VEtatDemande> etatDemandes = vEtatDemandeRepository.findByIdUtilisateur(idUtilisateur);
		return etatDemandes;
	}

	public List<VEtatDemande> demandeCongeParidDirectionParTypeUtilisateur(String idDirection, int etatUtilisateur, int etatemande) {
		List<VEtatDemande> etatDemandes = vEtatDemandeRepository.findByIdDirectionAndEtatUtilisateurAndIdEtatDemande(idDirection, etatUtilisateur, etatemande);
		return etatDemandes;
	}

	public List<VEtatDemande> demandeCongeParTypeUtilisateur(int etatUtilisateur, int etatemande) {
		List<VEtatDemande> etatDemandes = vEtatDemandeRepository.findByEtatUtilisateurAndIdEtatDemande(etatUtilisateur, etatemande);
		return etatDemandes;
	}
}
