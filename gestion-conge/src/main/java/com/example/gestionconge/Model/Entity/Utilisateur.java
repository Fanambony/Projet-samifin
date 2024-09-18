package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.List;
import java.util.List;
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
	@Column(name = "genre")
	Integer genre;
	@Column(name = "type_utilisateur")
	Integer typeUtilisateur;
	@Column(name = "etat")
	Integer etat;
	@Column(name = "image")
	Byte[] image;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DetailUtilisateur> detailUtilisateurs;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<SoldeUtilisateur> soldeUtilisateurs;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<Famille> familles;
	@ManyToOne
	@JoinColumn(name = "genre", insertable = false, updatable = false)
	Genre genre;
	@ManyToOne
	@JoinColumn(name = "etat", insertable = false, updatable = false)
	Etat etat_utilisateur;
	@ManyToOne
	@JoinColumn(name = "type_utilisateur", insertable = false, updatable = false)
	Type type_utilisateur;

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
	public Integer getGenre(){
		return this.genre;
	}
	public void setGenre(Integer genre){
		this.genre = genre;
	}
	public Integer getTypeUtilisateur(){
		return this.typeUtilisateur;
	}
	public void setTypeUtilisateur(Integer typeUtilisateur){
		this.typeUtilisateur = typeUtilisateur;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public Byte[] getImage(){
		return this.image;
	}
	public void setImage(Byte[] image){
		this.image = image;
	}
	public List<DetailUtilisateur> getDetailUtilisateurs(){
		return this.detailUtilisateurs;
	}
	public void setDetailUtilisateurs(List<DetailUtilisateur> detailUtilisateurs){
		this.detailUtilisateurs = detailUtilisateurs;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}
	public List<SoldeUtilisateur> getSoldeUtilisateurs(){
		return this.soldeUtilisateurs;
	}
	public void setSoldeUtilisateurs(List<SoldeUtilisateur> soldeUtilisateurs){
		this.soldeUtilisateurs = soldeUtilisateurs;
	}
	public List<Famille> getFamilles(){
		return this.familles;
	}
	public void setFamilles(List<Famille> familles){
		this.familles = familles;
	}
	public Genre getGenre(){
		return this.genre;
	}
	public void setGenre(Genre genre){
		this.genre = genre;
	}
	public Etat getEtat_utilisateur(){
		return this.etat_utilisateur;
	}
	public void setEtat_utilisateur(Etat etat_utilisateur){
		this.etat_utilisateur = etat_utilisateur;
	}
	public Type getType_utilisateur(){
		return this.type_utilisateur;
	}
	public void setType_utilisateur(Type type_utilisateur){
		this.type_utilisateur = type_utilisateur;
	}

    //CONSTRUCTORS

 	public Utilisateur(){}
	public Utilisateur(String id, String nom, String prenom, Date dateNaissance, Integer genre, Integer typeUtilisateur, Integer etat, Byte[] image, List<DetailUtilisateur> detailUtilisateurs, List<DemandeConge> demandeConges, List<SoldeUtilisateur> soldeUtilisateurs, List<Famille> familles, Genre genre, Etat etat_utilisateur, Type type_utilisateur){
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setGenre(genre);
		setTypeUtilisateur(typeUtilisateur);
		setEtat(etat);
		setImage(image);
		setDetailUtilisateurs(detailUtilisateurs);
		setDemandeConges(demandeConges);
		setSoldeUtilisateurs(soldeUtilisateurs);
		setFamilles(familles);
		setGenre(genre);
		setEtat_utilisateur(etat_utilisateur);
		setType_utilisateur(type_utilisateur);
	}

}
