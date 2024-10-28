package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "v_soldes_utilisateur")
public class VSoldesUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_ligne")
	Long numLigne;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "nom_type_conge")
	String nomTypeConge;
	@Column(name = "solde_calcule")
	Double soldeCalcule;

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
	public String getNomTypeConge(){
		return this.nomTypeConge;
	}
	public void setNomTypeConge(String nomTypeConge){
		this.nomTypeConge = nomTypeConge;
	}
	public Double getSoldeCalcule(){
		return this.soldeCalcule;
	}
	public void setSoldeCalcule(Double soldeCalcule){
		this.soldeCalcule = soldeCalcule;
	}

    //CONSTRUCTORS

 	public VSoldesUtilisateur(){}
	public VSoldesUtilisateur(Long numLigne, String idUtilisateur, String idTypeConge, String nomTypeConge, Double soldeCalcule){
		setNumLigne(numLigne);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setNomTypeConge(nomTypeConge);
		setSoldeCalcule(soldeCalcule);
	}

}
