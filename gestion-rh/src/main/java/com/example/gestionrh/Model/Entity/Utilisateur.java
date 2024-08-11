package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	Date dateNaissance;
	@Column(name = "sexe")
	String sexe;
	@Column(name = "etat")
	String etat;
	@Column(name = "type_utilisateur")
	String typeUtilisateur;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DetailUtilisateur> detailUtilisateurs;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<SoldeConge> soldeConges;
	@ManyToOne
	@JoinColumn(name = "type_utilisateur", insertable = false, updatable = false)
	TypeUtilisateur type_utilisateur;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
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
	public String getSexe(){
		return this.sexe;
	}
	public void setSexe(String sexe){
		this.sexe = sexe;
	}
	public String getEtat(){
		return this.etat;
	}
	public void setEtat(String etat){
		this.etat = etat;
	}
	public String getTypeUtilisateur(){
		return this.typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur){
		this.typeUtilisateur = typeUtilisateur;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}
	public List<DetailUtilisateur> getDetailUtilisateurs(){
		return this.detailUtilisateurs;
	}
	public void setDetailUtilisateurs(List<DetailUtilisateur> detailUtilisateurs){
		this.detailUtilisateurs = detailUtilisateurs;
	}
	public List<SoldeConge> getSoldeConges(){
		return this.soldeConges;
	}
	public void setSoldeConges(List<SoldeConge> soldeConges){
		this.soldeConges = soldeConges;
	}
	public TypeUtilisateur getType_utilisateur(){
		return this.type_utilisateur;
	}
	public void setType_utilisateur(TypeUtilisateur type_utilisateur){
		this.type_utilisateur = type_utilisateur;
	}

    //CONSTRUCTORS

 	public Utilisateur(){}
	public Utilisateur(String id, String nom, String prenom, Date dateNaissance, String sexe, String etat, String typeUtilisateur, List<DemandeConge> demandeConges, List<DetailUtilisateur> detailUtilisateurs, List<SoldeConge> soldeConges, TypeUtilisateur type_utilisateur){
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setSexe(sexe);
		setEtat(etat);
		setTypeUtilisateur(typeUtilisateur);
		setDemandeConges(demandeConges);
		setDetailUtilisateurs(detailUtilisateurs);
		setSoldeConges(soldeConges);
		setType_utilisateur(type_utilisateur);
	}
}
