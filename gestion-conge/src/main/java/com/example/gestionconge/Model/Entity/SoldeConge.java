package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "solde_conge")
public class SoldeConge {

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
	@Column(name = "solde_entre")
	Double soldeEntre;
	@Column(name = "solde_sortie")
	Double soldeSortie;
	@Column(name = "date_demande")
	Date dateDemande;
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
	public Date getDateDemande(){
		return this.dateDemande;
	}
	public void setDateDemande(Date dateDemande){
		this.dateDemande = dateDemande;
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

 	public SoldeConge(){}
	public SoldeConge(String id, String idUtilisateur, String idTypeConge, Integer annee, Double soldeEntre, Double soldeSortie, Date dateDemande, Utilisateur utilisateur, Type type_conge){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setIdTypeConge(idTypeConge);
		setAnnee(annee);
		setSoldeEntre(soldeEntre);
		setSoldeSortie(soldeSortie);
		setDateDemande(dateDemande);
		setUtilisateur(utilisateur);
		setType_conge(type_conge);
	}

}
