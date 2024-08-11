package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "solde_conge")
public class SoldeConge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "annee")
	Integer annee;
	@Column(name = "solde_entre")
	Double soldeEntre;
	@Column(name = "solde_sortie")
	Double soldeSortie;
	@Column(name = "date_mise_a_jour")
	Date dateMiseAJour;
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
	public Double getSoldeEntre(){
		return this.soldeEntre;
	}
	public void setSoldeEntre(Double soldeEntre){
		this.soldeEntre = soldeEntre;
	}
	public Double getSoldeSortie(){
		return this.soldeSortie;
	}
	public void setSoldeSortie(Double soldeSortie){
		this.soldeSortie = soldeSortie;
	}
	public Date getDateMiseAJour(){
		return this.dateMiseAJour;
	}
	public void setDateMiseAJour(Date dateMiseAJour){
		this.dateMiseAJour = dateMiseAJour;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}

    //CONSTRUCTORS

 	public SoldeConge(){}
	public SoldeConge(String id, String idUtilisateur, Integer annee, Double soldeEntre, Double soldeSortie, Date dateMiseAJour, Utilisateur utilisateur){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setAnnee(annee);
		setSoldeEntre(soldeEntre);
		setSoldeSortie(soldeSortie);
		setDateMiseAJour(dateMiseAJour);
		setUtilisateur(utilisateur);
	}

}
