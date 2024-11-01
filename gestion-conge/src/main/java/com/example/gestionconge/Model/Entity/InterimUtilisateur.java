package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "interim_utilisateur")
public class InterimUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "date_debut")
	Date dateDebut;
	@Column(name = "date_fin")
	Date dateFin;
	@Column(name = "etat_interim")
	Integer etatInterim;
	@Column(name = "son_etat")
	Integer sonEtat;
	@Column(name = "id_demande")
	String idDemande;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_demande", insertable = false, updatable = false)
	Demande demande_conge;

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
	public Date getDateDebut(){
		return this.dateDebut;
	}
	public void setDateDebut(Date dateDebut){
		this.dateDebut = dateDebut;
	}
	public Date getDateFin(){
		return this.dateFin;
	}
	public void setDateFin(Date dateFin){
		this.dateFin = dateFin;
	}
	public Integer getEtatInterim(){
		return this.etatInterim;
	}
	public void setEtatInterim(Integer etatInterim){
		this.etatInterim = etatInterim;
	}
	public Integer getSonEtat(){
		return this.sonEtat;
	}
	public void setSonEtat(Integer sonEtat){
		this.sonEtat = sonEtat;
	}
	public String getIdDemande(){
		return this.idDemande;
	}
	public void setIdDemande(String idDemande){
		this.idDemande = idDemande;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public Demande getDemande_conge(){
		return this.demande_conge;
	}
	public void setDemande_conge(Demande demande_conge){
		this.demande_conge = demande_conge;
	}

    //CONSTRUCTORS

 	public InterimUtilisateur(){}
	public InterimUtilisateur(String id, String idUtilisateur, Date dateDebut, Date dateFin, Integer etatInterim, Integer sonEtat, String idDemande, Utilisateur utilisateur, Demande demande_conge){
		setId(id);
		setIdUtilisateur(idUtilisateur);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setEtatInterim(etatInterim);
		setSonEtat(sonEtat);
		setIdDemande(idDemande);
		setUtilisateur(utilisateur);
		setDemande_conge(demande_conge);
	}

}
