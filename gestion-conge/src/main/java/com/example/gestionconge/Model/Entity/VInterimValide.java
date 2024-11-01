package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "v_interim_valide")
public class VInterimValide {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_interim_utilisateur")
	String idInterimUtilisateur;
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
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "etat_demande")
	Integer etatDemande;
	@Column(name = "est_annuler")
	Boolean estAnnuler;

    //SETTERS AND GETTERS

	public String getIdInterimUtilisateur(){
		return this.idInterimUtilisateur;
	}
	public void setIdInterimUtilisateur(String idInterimUtilisateur){
		this.idInterimUtilisateur = idInterimUtilisateur;
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
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
	}
	public Integer getEtatDemande(){
		return this.etatDemande;
	}
	public void setEtatDemande(Integer etatDemande){
		this.etatDemande = etatDemande;
	}
	public Boolean getEstAnnuler(){
		return this.estAnnuler;
	}
	public void setEstAnnuler(Boolean estAnnuler){
		this.estAnnuler = estAnnuler;
	}

    //CONSTRUCTORS

 	public VInterimValide(){}
	public VInterimValide(String idInterimUtilisateur, String idUtilisateur, Date dateDebut, Date dateFin, Integer etatInterim, Integer sonEtat, String idDemande, String idTypeConge, Integer etatDemande, Boolean estAnnuler){
		setIdInterimUtilisateur(idInterimUtilisateur);
		setIdUtilisateur(idUtilisateur);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setEtatInterim(etatInterim);
		setSonEtat(sonEtat);
		setIdDemande(idDemande);
		setIdTypeConge(idTypeConge);
		setEtatDemande(etatDemande);
		setEstAnnuler(estAnnuler);
	}

}
