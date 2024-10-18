package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.LocaliteServiceRepository;
import com.example.gestionrh.Model.Entity.LocaliteService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocaliteServiceService {

	private final LocaliteServiceRepository localiteServiceRepository;

	public LocaliteServiceService(LocaliteServiceRepository localiteServiceRepository) {this.localiteServiceRepository = localiteServiceRepository;}



	/* -- READ ONE -- */
	public Optional<LocaliteService> getOne(Object id) { return localiteServiceRepository.findById(id); }

	/* -- READ -- */
	public List<LocaliteService> getAll() { return localiteServiceRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(LocaliteService LocaliteService) {  localiteServiceRepository.save(LocaliteService); }

	/* -- DELETE -- */
	public void delete(Object id) {  localiteServiceRepository.deleteById(id); }

}
