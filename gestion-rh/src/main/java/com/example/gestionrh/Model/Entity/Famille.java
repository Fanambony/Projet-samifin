package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "famille")
public class Famille {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_employe")
	String idEmploye;
	@Column(name = "id_filiation")
	String idFiliation;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	Date dateNaissance;
	@ManyToOne
	@JoinColumn(name = "id_employe", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_filiation", insertable = false, updatable = false)
	Filiation filiation;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getIdEmploye(){
		return this.idEmploye;
	}
	public void setIdEmploye(String idEmploye){
		this.idEmploye = idEmploye;
	}
	public String getIdFiliation(){
		return this.idFiliation;
	}
	public void setIdFiliation(String idFiliation){
		this.idFiliation = idFiliation;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public Date getDateNaissance(){
		return this.dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance){
		this.dateNaissance = dateNaissance;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public Filiation getFiliation(){
		return this.filiation;
	}
	public void setFiliation(Filiation filiation){
		this.filiation = filiation;
	}

    //CONSTRUCTORS

 	public Famille(){}
	public Famille(String id, String idEmploye, String idFiliation, String nom, String prenom, Date dateNaissance, Utilisateur utilisateur, Filiation filiation){
		setId(id);
		setIdEmploye(idEmploye);
		setIdFiliation(idFiliation);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setUtilisateur(utilisateur);
		setFiliation(filiation);
	}
	public Famille(String idEmploye, String idFiliation, String nom, String prenom, Date dateNaissance){
		setIdEmploye(idEmploye);
		setIdFiliation(idFiliation);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
	}
}