package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.CorpsAppartenanceRepository;
import com.example.gestionrh.Model.Entity.CorpsAppartenance;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CorpsAppartenanceService {

	private final CorpsAppartenanceRepository corpsAppartenanceRepository;

	public CorpsAppartenanceService(CorpsAppartenanceRepository corpsAppartenanceRepository) {this.corpsAppartenanceRepository = corpsAppartenanceRepository;}



	/* -- READ ONE -- */
	public Optional<CorpsAppartenance> getOne(Object id) { return corpsAppartenanceRepository.findById(id); }

	/* -- READ -- */
	public List<CorpsAppartenance> getAll() { return corpsAppartenanceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(CorpsAppartenance CorpsAppartenance) {  corpsAppartenanceRepository.save(CorpsAppartenance); }

	/* -- DELETE -- */
	public void delete(Object id) {  corpsAppartenanceRepository.deleteById(id); }

}
