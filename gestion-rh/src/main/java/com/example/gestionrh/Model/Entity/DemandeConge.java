package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;


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
	@Column(name = "etat")
	Integer etat;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_type_conge", insertable = false, updatable = false)
	TypeConge type_conge;

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
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public TypeConge getType_conge(){
		return this.type_conge;
	}
	public void setType_conge(TypeConge type_conge){
		this.type_conge = type_conge;
	}

    //CONSTRUCTORS

 	public DemandeConge(){}
	public DemandeConge(String id, String idTypeConge, String idUtilisateur, Date dateDebut, String debutAbsence, Date dateFin, String finAbsence, String commentaire, Integer etat, Utilisateur utilisateur, TypeConge type_conge){
		setId(id);
		setIdTypeConge(idTypeConge);
		setIdUtilisateur(idUtilisateur);
		setDateDebut(dateDebut);
		setDebutAbsence(debutAbsence);
		setDateFin(dateFin);
		setFinAbsence(finAbsence);
		setCommentaire(commentaire);
		setEtat(etat);
		setUtilisateur(utilisateur);
		setType_conge(type_conge);
	}

}
