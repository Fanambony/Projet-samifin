package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.CorpsAppartenanceRepository;
import com.example.gestionrh.Model.Entity.CorpsAppartenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CorpsAppartenanceService {

	@Autowired
	private CorpsAppartenanceRepository corpsAppartenanceRepository;

	/* -- READ ONE -- */
	public Optional<CorpsAppartenance> getOne(Object id) { return corpsAppartenanceRepository.findById(id); }

	/* -- READ -- */
	public List<CorpsAppartenance> getAll() { return corpsAppartenanceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(CorpsAppartenance CorpsAppartenance) {  corpsAppartenanceRepository.save(CorpsAppartenance); }

	/* -- DELETE -- */
	public void delete(Object id) {  corpsAppartenanceRepository.deleteById(id); }

}
