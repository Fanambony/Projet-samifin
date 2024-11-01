package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.VEtatDemandeRepository;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



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

	public List<VEtatDemande> demandeCongeParidDirectionParTypeUtilisateur(String idDirection, int etatUtilisateur, int etatDemande) {
		return vEtatDemandeRepository.findByIdDirectionAndEtatUtilisateurAndIdEtatDemande(idDirection, etatUtilisateur, etatDemande);
	}

	public List<VEtatDemande> demandeCongeParTypeUtilisateur(int etatUtilisateur, int etatemande) {
		return vEtatDemandeRepository.findByEtatUtilisateurAndIdEtatDemande(etatUtilisateur, etatemande);
	}

	// prendre demande valider la date debut est superieur a la date aujourd'hui qui doit etre annuler
	public Page<VEtatDemande> findByIdEtatDemandeAndDateDebutAfter(int etatDemande, Pageable pageable) {
		return vEtatDemandeRepository.findByIdEtatDemande(etatDemande, pageable);
	}

	public List<VEtatDemande> demandeValiderParUtilisateur(int idEtatDemande) {
		return vEtatDemandeRepository.findByIdEtatDemande(idEtatDemande);
	}

	public List<VEtatDemande> demandeValiderPourUtilisateur(String idUtilisateur, int idEtatDemande) {
		return vEtatDemandeRepository.findByIdUtilisateurAndIdEtatDemande(idUtilisateur, idEtatDemande);
	}

	// public List<VEtatDemande> demandeValiderParUtilisateur(String idUtilisateur, int idEtatDemande) {
	// 	return vEtatDemandeRepository.findByIdUttilisateurAndIdEtatDemande(idUtilisateur, idEtatDemande);
	// }

	// public List<VEtatDemande> searchByUserAndTerm(String idUtilisateur, String searchTerm) {
	// 	// Implémenter la logique de recherche, par exemple en recherchant dans le type de congé ou d'autres champs
	// 	return vEtatDemandeRepository.findByUtilisateurAndSearchTerm(idUtilisateur, searchTerm);
	// }

	public List<VEtatDemande> searchByUserAndTerm(String idUtilisateur, String searchTerm) {
		// Récupérer les résultats bruts de la base de données
		List<VEtatDemande> resultats = vEtatDemandeRepository.findByUtilisateurAndSearchTerm(idUtilisateur, searchTerm);
		
		// Filtrer les résultats si le searchTerm inclut un format de date
		return resultats.stream()
			.filter(demande -> {
				String formattedDate = DateUtil.formatDate(demande.getDateDemande());
				return demande.getTypeConge().toLowerCase().contains(searchTerm.toLowerCase()) || 
					   demande.getEtatDemande().toLowerCase().contains(searchTerm.toLowerCase()) || 
					   demande.getNombreJoursConge().toString().contains(searchTerm) || 
					   formattedDate.toLowerCase().contains(searchTerm.toLowerCase());
			})
			.collect(Collectors.toList());
	}

	public List<VEtatDemande> findCongeAnnuler(int etatDemande, String searchTerm) {
        return vEtatDemandeRepository.findBySearchCongeAnnuler(etatDemande, searchTerm);
    }
	
	public VEtatDemande getByDemande(String idDemande) {
		return vEtatDemandeRepository.findByIdDemandeConge(idDemande);
	}

	public List<VEtatDemande> searchByNameAndSurname(String searchTerm) {
		// Utiliser uniquement la requête sans filtrage supplémentaire
		return vEtatDemandeRepository.findByNameAndSurname(searchTerm);
	}
	
	public List<VEtatDemande> getDetailByIdUtilisateur(String idUtilisateur) {
		return vEtatDemandeRepository.findByIdUtilisateur(idUtilisateur);
	}
	
}
