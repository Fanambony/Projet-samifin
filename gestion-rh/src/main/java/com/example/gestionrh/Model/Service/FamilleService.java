package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.FamilleRepository;
import com.example.gestionrh.Model.Entity.Famille;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class FamilleService {

	@Autowired
	private FamilleRepository familleRepository;

	/* -- READ ONE -- */
	public Optional<Famille> getOne(Object id) { return familleRepository.findById(id); }

	/* -- READ -- */
	public List<Famille> getAll() { return familleRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Famille Famille) {  familleRepository.save(Famille); }

	/* -- DELETE -- */
	public void delete(Object id) {  familleRepository.deleteById(id); }

	public List<Famille> findByIdEmploye(String idEmploye) {
		return familleRepository.findByIdEmploye(idEmploye);
	}

	public List<Famille> findFamillesByAgentId(String agentId) {
        return familleRepository.findByIdEmploye(agentId);
    }

	

	// public List<Famille> findByIdEmploye(String agentId) {
    //     // Implémentez la logique pour récupérer les familles associées à l'agent
    //     // Par exemple, une requête JPA ou JDBC
    //     List<Famille> familles = familleRepository.findByIdEmploye(agentId);
        
    //     if (familles.isEmpty()) {
    //         System.out.println("Aucune famille trouvée pour l'agent avec ID: " + agentId);
    //     } else {
    //         System.out.println("Familles trouvées pour l'agent avec ID: " + agentId + " : " + familles);
    //     }
        
    //     return familles;
    // }
}