package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "solde_entre")
public class SoldeEntre {

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
	@Column(name = "droit")
	Double droit;
	@Column(name = "reliquat")
	Double reliquat;
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
	public Double getDroit(){
		return this.droit;
	}
	public void setDroit(Double droit){
		this.droit = droit;
	}
	public Double getReliquat(){
		return this.reliquat;
	}
	public void setReliquat(Double reliquat){
		this.reliquat = reliquat;
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

 	public SoldeEntre(){}
	public SoldeEntre(String id, String idUtilisateur, String idTypeConge, Integer annee, Double droit, Double reliquat, Utilisateur utilisateur, Type type_conge){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setAnnee(annee);
		setDroit(droit);
		setReliquat(reliquat);
		setUtilisateur(utilisateur);
		setType_conge(type_conge);
	}

}
