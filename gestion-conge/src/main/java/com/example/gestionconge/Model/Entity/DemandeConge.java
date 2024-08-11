package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "demande_conge")
public class DemandeConge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "date_demande")
	Date dateDemande;
	@Column(name = "date_debut")
	Date dateDebut;
	@Column(name = "debut_absence")
	String debutAbsence;
	@Column(name = "date_fin")
	Date dateFin;
	@Column(name = "fin_absence")
	String finAbsence;
	@Column(name = "commentaire")
	String commentaire;
	@Column(name = "etat_demande")
	String etatDemande;
	@ManyToOne
	@JoinColumn(name = "id_type_conge", insertable = false, updatable = false)
	Type type_conge;
	@ManyToOne
	@JoinColumn(name = "etat_demande", insertable = false, updatable = false)
	Etat etat_demande;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "debut_absence", insertable = false, updatable = false)
	Periode periode_absence;
	@ManyToOne
	@JoinColumn(name = "fin_absence", insertable = false, updatable = false)
	Periode periode_absence;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public Date getDateDemande(){
		return this.dateDemande;
	}
	public void setDateDemande(Date dateDemande){
		this.dateDemande = dateDemande;
	}
	public Date getDateDebut(){
		return this.dateDebut;
	}
	public void setDateDebut(Date dateDebut){
		this.dateDebut = dateDebut;
	}
	public String getDebutAbsence(){
		return this.debutAbsence;
	}
	public void setDebutAbsence(String debutAbsence){
		this.debutAbsence = debutAbsence;
	}
	public Date getDateFin(){
		return this.dateFin;
	}
	public void setDateFin(Date dateFin){
		this.dateFin = dateFin;
	}
	public String getFinAbsence(){
		return this.finAbsence;
	}
	public void setFinAbsence(String finAbsence){
		this.finAbsence = finAbsence;
	}
	public String getCommentaire(){
		return this.commentaire;
	}
	public void setCommentaire(String commentaire){
		this.commentaire = commentaire;
	}
	public String getEtatDemande(){
		return this.etatDemande;
	}
	public void setEtatDemande(String etatDemande){
		this.etatDemande = etatDemande;
	}
	public Type getType_conge(){
		return this.type_conge;
	}
	public void setType_conge(Type type_conge){
		this.type_conge = type_conge;
	}
	public Etat getEtat_demande(){
		return this.etat_demande;
	}
	public void setEtat_demande(Etat etat_demande){
		this.etat_demande = etat_demande;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public Periode getPeriode_absence(){
		return this.periode_absence;
	}
	public void setPeriode_absence(Periode periode_absence){
		this.periode_absence = periode_absence;
	}
	public Periode getPeriode_absence(){
		return this.periode_absence;
	}
	public void setPeriode_absence(Periode periode_absence){
		this.periode_absence = periode_absence;
	}

    //CONSTRUCTORS

 	public DemandeConge(){}
	public DemandeConge(String id, String idTypeConge, String idUtilisateur, Date dateDemande, Date dateDebut, String debutAbsence, Date dateFin, String finAbsence, String commentaire, String etatDemande, Type type_conge, Etat etat_demande, Utilisateur utilisateur, Periode periode_absence, Periode periode_absence){
		setId(id);
		setIdTypeConge(idTypeConge);
		setIdUtilisateur(idUtilisateur);
		setDateDemande(dateDemande);
		setDateDebut(dateDebut);
		setDebutAbsence(debutAbsence);
		setDateFin(dateFin);
		setFinAbsence(finAbsence);
		setCommentaire(commentaire);
		setEtatDemande(etatDemande);
		setType_conge(type_conge);
		setEtat_demande(etat_demande);
		setUtilisateur(utilisateur);
		setPeriode_absence(periode_absence);
		setPeriode_absence(periode_absence);
	}

}
