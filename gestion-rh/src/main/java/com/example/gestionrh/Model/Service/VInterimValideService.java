package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.UtilisateurRepository;
import com.example.gestionrh.Context.VInterimValideRepository;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Entity.VInterimValide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
public class VInterimValideService {

	@Autowired
	private VInterimValideRepository vInterimValideRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	/* -- READ ONE -- */
	public Optional<VInterimValide> getOne(Object id) { return vInterimValideRepository.findById(id); }

	/* -- READ -- */
	public List<VInterimValide> getAll() { return vInterimValideRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(VInterimValide VInterimValide) {  vInterimValideRepository.save(VInterimValide); }

	/* -- DELETE -- */
	public void delete(Object id) {  vInterimValideRepository.deleteById(id); }

	@Transactional
	public void verifierEtMettreAJourInterim() {
		List<VInterimValide> interimsValides = vInterimValideRepository.findAll();
		LocalDate today = LocalDate.now();

		for (VInterimValide interim : interimsValides) {
			Utilisateur utilisateur = utilisateurRepository.findById(interim.getIdUtilisateur())
				.orElse(null);

			if (utilisateur != null) {
				// Convert `Date` to `LocalDate`
				LocalDate dateDebut = interim.getDateDebut().toLocalDate();
				LocalDate dateFin = interim.getDateFin().toLocalDate();

				if (!today.isBefore(dateDebut) && !today.isAfter(dateFin)) {
					// Update with `etat_interim` if current date is within the range
					utilisateur.setTypeUtilisateur(interim.getEtatInterim());
				} else if (today.isAfter(dateFin)) {
					// Reset with `son_etat` after the interim period ends
					utilisateur.setTypeUtilisateur(interim.getSonEtat());
				}
				utilisateurRepository.save(utilisateur);
			}
		}
	}

}