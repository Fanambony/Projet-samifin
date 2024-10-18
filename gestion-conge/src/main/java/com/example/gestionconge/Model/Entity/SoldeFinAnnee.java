package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "solde_fin_annee")
public class SoldeFinAnnee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "annee")
	Integer annee;
	@Column(name = "solde")
	Double solde;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public Integer getAnnee(){
		return this.annee;
	}
	public void setAnnee(Integer annee){
		this.annee = annee;
	}
	public Double getSolde(){
		return this.solde;
	}
	public void setSolde(Double solde){
		this.solde = solde;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}

    //CONSTRUCTORS

 	public SoldeFinAnnee(){}
	public SoldeFinAnnee(String id, String idUtilisateur, Integer annee, Double solde, Utilisateur utilisateur){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setAnnee(annee);
		setSolde(solde);
		setUtilisateur(utilisateur);
	}

}
