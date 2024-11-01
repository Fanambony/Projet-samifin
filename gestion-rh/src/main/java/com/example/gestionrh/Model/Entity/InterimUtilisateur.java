package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;


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
	int etatInterim;
	@Column(name = "son_etat")
	int sonEtat;
	@Column(name = "id_demande")
	String idDemande;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_demande", insertable = false, updatable = false)
	DemandeConge demande_conge;

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
	public int getEtatInterim(){
		return this.etatInterim;
	}
	public void setEtatInterim(int etatInterim){
		this.etatInterim = etatInterim;
	}
	public int getSonEtat(){
		return this.sonEtat;
	}
	public void setSonEtat(int sonEtat){
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
	public DemandeConge getDemande_conge(){
		return this.demande_conge;
	}
	public void setDemande_conge(DemandeConge demande_conge){
		this.demande_conge = demande_conge;
	}

    //CONSTRUCTORS

 	public InterimUtilisateur(){}
	
	public InterimUtilisateur(String id, String idUtilisateur, Date dateDebut, Date dateFin, int etatInterim,
            int sonEtat, String idDemande, Utilisateur utilisateur, DemandeConge demande_conge) {
        setId(idUtilisateur);
        setIdUtilisateur(idUtilisateur);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setEtatInterim(etatInterim);
        setSonEtat(sonEtat);
		setIdDemande(idDemande);
        setUtilisateur(utilisateur);
		setDemande_conge(demande_conge);
    }
	public InterimUtilisateur(String idUtilisateur, Date dateDebut, Date dateFin, int etatInterim, int sonEtat, String idDemande){
		setIdUtilisateur(idUtilisateur);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setEtatInterim(etatInterim);
		setSonEtat(sonEtat);
		setIdDemande(idDemande);
	}
}