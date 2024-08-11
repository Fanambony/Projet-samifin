package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.PeriodeAbsenceRepository;
import com.example.gestionconge.Model.Entity.PeriodeAbsence;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class PeriodeAbsenceService {

	private final PeriodeAbsenceRepository periodeAbsenceRepository;

	public PeriodeAbsenceService(PeriodeAbsenceRepository periodeAbsenceRepository) {this.periodeAbsenceRepository = periodeAbsenceRepository;}



	/* -- READ ONE -- */
	public Optional<PeriodeAbsence> getOne(Object id) { return periodeAbsenceRepository.findById(id); }

	/* -- READ -- */
	public List<PeriodeAbsence> getAll() { return periodeAbsenceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(PeriodeAbsence PeriodeAbsence) {  periodeAbsenceRepository.save(PeriodeAbsence); }

	/* -- DELETE -- */
	public void delete(Object id) {  periodeAbsenceRepository.deleteById(id); }

}
