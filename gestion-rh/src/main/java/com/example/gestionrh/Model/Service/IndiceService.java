package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.IndiceRepository;
import com.example.gestionrh.Model.Entity.Indice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IndiceService {

	@Autowired
	private IndiceRepository indiceRepository;

	/* -- READ ONE -- */
	public Optional<Indice> getOne(Object id) { return indiceRepository.findById(id); }

	/* -- READ -- */
	public List<Indice> getAll() { return indiceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Indice Indice) {  indiceRepository.save(Indice); }

	/* -- DELETE -- */
	public void delete(Object id) {  indiceRepository.deleteById(id); }

}
