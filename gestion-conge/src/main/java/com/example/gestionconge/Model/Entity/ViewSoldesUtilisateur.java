package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "view_soldes_utilisateur")
public class ViewSoldesUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "nom_type_conge")
	String nomTypeConge;
	@Column(name = "solde_calculé")
	Double soldeCalculé;

    //SETTERS AND GETTERS

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
	public Double getSoldeCalculé(){
		return this.soldeCalculé;
	}
	public void setSoldeCalculé(Double soldeCalculé){
		this.soldeCalculé = soldeCalculé;
	}

    //CONSTRUCTORS

 	public ViewSoldesUtilisateur(){}
	public ViewSoldesUtilisateur(String idUtilisateur, String idTypeConge, String nomTypeConge, Double soldeCalculé){
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setNomTypeConge(nomTypeConge);
		setSoldeCalculé(soldeCalculé);
	}

}
