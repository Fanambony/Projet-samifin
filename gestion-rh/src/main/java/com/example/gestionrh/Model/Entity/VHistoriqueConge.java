package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "v_historique_conge")
public class VHistoriqueConge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_ligne")
	Long numLigne;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "solde_disponible")
	Double soldeDisponible;
	@Column(name = "nombre_jours_pris")
	BigDecimal nombreJoursPris;
	@Column(name = "solde_restant")
	Double soldeRestant;

    //SETTERS AND GETTERS

	public Long getNumLigne(){
		return this.numLigne;
	}
	public void setNumLigne(Long numLigne){
		this.numLigne = numLigne;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
	}
	public Double getSoldeDisponible(){
		return this.soldeDisponible;
	}
	public void setSoldeDisponible(Double soldeDisponible){
		this.soldeDisponible = soldeDisponible;
	}
	public BigDecimal getNombreJoursPris(){
		return this.nombreJoursPris;
	}
	public void setNombreJoursPris(BigDecimal nombreJoursPris){
		this.nombreJoursPris = nombreJoursPris;
	}
	public Double getSoldeRestant(){
		return this.soldeRestant;
	}
	public void setSoldeRestant(Double soldeRestant){
		this.soldeRestant = soldeRestant;
	}

    //CONSTRUCTORS

 	public VHistoriqueConge(){}
	public VHistoriqueConge(Long numLigne, String idUtilisateur, String idTypeConge, Double soldeDisponible, BigDecimal nombreJoursPris, Double soldeRestant){
		setNumLigne(numLigne);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setSoldeDisponible(soldeDisponible);
		setNombreJoursPris(nombreJoursPris);
		setSoldeRestant(soldeRestant);
	}

}
