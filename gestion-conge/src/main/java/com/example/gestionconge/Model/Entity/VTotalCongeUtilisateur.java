package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "v_total_conge_utilisateur")
public class VTotalCongeUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_ligne")
	Long numLigne;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "solde")
	Double solde;

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
	public Double getSolde(){
		return this.solde;
	}
	public void setSolde(Double solde){
		this.solde = solde;
	}

    //CONSTRUCTORS

 	public VTotalCongeUtilisateur(){}
	public VTotalCongeUtilisateur(Long numLigne, String idUtilisateur, String idTypeConge, Double solde){
		setNumLigne(numLigne);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setSolde(solde);
	}

}
