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
	@Column(name = "matricule")
	Integer matricule;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	Date dateNaissance;
	@Column(name = "email")
	String email;
	@Column(name = "mdp")
	String mdp;
	@Column(name = "date_entre")
	Date dateEntre;
	@Column(name = "id_fonction")
	String idFonction;
	@Column(name = "etat")
	Integer etat;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;
	@ManyToOne
	@JoinColumn(name = "id_fonction", insertable = false, updatable = false)
	Fonction fonction;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public Integer getMatricule(){
		return this.matricule;
	}
	public void setMatricule(Integer matricule){
		this.matricule = matricule;
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
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getMdp(){
		return this.mdp;
	}
	public void setMdp(String mdp){
		this.mdp = mdp;
	}
	public Date getDateEntre(){
		return this.dateEntre;
	}
	public void setDateEntre(Date dateEntre){
		this.dateEntre = dateEntre;
	}
	public String getIdFonction(){
		return this.idFonction;
	}
	public void setIdFonction(String idFonction){
		this.idFonction = idFonction;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}
	public Fonction getFonction(){
		return this.fonction;
	}
	public void setFonction(Fonction fonction){
		this.fonction = fonction;
	}

    //CONSTRUCTORS

 	public Utilisateur(){}
	public Utilisateur(String id, Integer matricule, String nom, String prenom, Date dateNaissance, String email, String mdp, Date dateEntre, String idFonction, Integer etat, List<DemandeConge> demandeConges, Fonction fonction){
		setId(id);
		setMatricule(matricule);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setEmail(email);
		setMdp(mdp);
		setDateEntre(dateEntre);
		setIdFonction(idFonction);
		setEtat(etat);
		setDemandeConges(demandeConges);
		setFonction(fonction);
	}

}
