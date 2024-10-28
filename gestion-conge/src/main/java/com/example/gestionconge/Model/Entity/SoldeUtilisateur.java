package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "solde_utilisateur")
public class SoldeUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "annee")
	Integer annee;
	@Column(name = "solde")
	Double solde;
	@Column(name = "date_generer")
	Date dateGenerer;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_type_conge", insertable = false, updatable = false)
	Type type_conge;

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
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
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
	public Date getDateGenerer(){
		return this.dateGenerer;
	}
	public void setDateGenerer(Date dateGenerer){
		this.dateGenerer = dateGenerer;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public Type getType_conge(){
		return this.type_conge;
	}
	public void setType_conge(Type type_conge){
		this.type_conge = type_conge;
	}

    //CONSTRUCTORS

 	public SoldeUtilisateur(){}
	public SoldeUtilisateur(String id, String idUtilisateur, String idTypeConge, Integer annee, Double solde, Date dateGenerer, Utilisateur utilisateur, Type type_conge){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setAnnee(annee);
		setSolde(solde);
		setDateGenerer(dateGenerer);
		setUtilisateur(utilisateur);
		setType_conge(type_conge);
	}

}
