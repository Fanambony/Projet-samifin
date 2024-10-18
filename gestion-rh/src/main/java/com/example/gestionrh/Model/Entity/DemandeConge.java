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
	@Column(name = "date_demande")
	Date dateDemande;
	@Column(name = "date_debut")
	Date dateDebut;
	@Column(name = "debut_absence")
	Integer debutAbsence;
	@Column(name = "date_fin")
	Date dateFin;
	@Column(name = "fin_absence")
	Integer finAbsence;
	@Column(name = "commentaire")
	String commentaire;
	@Column(name = "etat_demande")
	Integer etatDemande;
	@Column(name = "id_validateur")
	String idValidateur;
	@ManyToOne
	@JoinColumn(name = "id_type_conge", insertable = false, updatable = false)
	TypeConge type_conge;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "fin_absence", insertable = false, updatable = false)
	TypeAbsence fin_type_absence;
	@ManyToOne
	@JoinColumn(name = "debut_absence", insertable = false, updatable = false)
	TypeAbsence type_absence;
	@ManyToOne
	@JoinColumn(name = "etat_demande", insertable = false, updatable = false)
	EtatDemande etat_demande;
	@ManyToOne
	@JoinColumn(name = "id_validateur", insertable = false, updatable = false)
	Utilisateur validateur;

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
	public Integer getDebutAbsence(){
		return this.debutAbsence;
	}
	public void setDebutAbsence(Integer debutAbsence){
		this.debutAbsence = debutAbsence;
	}
	public Date getDateFin(){
		return this.dateFin;
	}
	public void setDateFin(Date dateFin){
		this.dateFin = dateFin;
	}
	public Integer getFinAbsence(){
		return this.finAbsence;
	}
	public void setFinAbsence(Integer finAbsence){
		this.finAbsence = finAbsence;
	}
	public String getCommentaire(){
		return this.commentaire;
	}
	public void setCommentaire(String commentaire){
		this.commentaire = commentaire;
	}
	public Integer getEtatDemande(){
		return this.etatDemande;
	}
	public void setEtatDemande(Integer etatDemande){
		this.etatDemande = etatDemande;
	}
	public String getIdValidateur(){
		return this.idValidateur;
	}
	public void setIdValidateur(String idValidateur){
		this.idValidateur = idValidateur;
	}
	public TypeConge getType_conge(){
		return this.type_conge;
	}
	public void setType_conge(TypeConge type_conge){
		this.type_conge = type_conge;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public TypeAbsence getFin_type_absence(){
		return this.fin_type_absence;
	}
	public void setFin_type_absence(TypeAbsence fin_type_absence){
		this.fin_type_absence = fin_type_absence;
	}
	public TypeAbsence getType_absence(){
		return this.type_absence;
	}
	public void setType_absence(TypeAbsence type_absence){
		this.type_absence = type_absence;
	}
	public EtatDemande getEtat_demande(){
		return this.etat_demande;
	}
	public void setEtat_demande(EtatDemande etat_demande){
		this.etat_demande = etat_demande;
	}
	public Utilisateur getValidateur(){
		return this.validateur;
	}
	public void setValidateur(Utilisateur validateur){
		this.validateur = validateur;
	}

 	public DemandeConge(){}
	public DemandeConge(String id, String idTypeConge, String idUtilisateur, Date dateDemande, Date dateDebut, Integer debutAbsence, Date dateFin, Integer finAbsence, String commentaire, Integer etatDemande, String idValidateur, TypeConge type_conge, Utilisateur utilisateur, TypeAbsence fin_type_absence, TypeAbsence type_absence, EtatDemande etat_demande, Utilisateur validateur){
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
		setUtilisateur(utilisateur);
		setType_absence(type_absence);
		setType_absence(type_absence);
		setEtat_demande(etat_demande);
		setValidateur(validateur);
	}

	public DemandeConge(String idTypeConge, String idUtilisateur, Date dateDemande, Date dateDebut, Integer debutAbsence, Date dateFin, Integer finAbsence, String commentaire, Integer etatDemande){
		setIdTypeConge(idTypeConge);
		setIdUtilisateur(idUtilisateur);
		setDateDemande(dateDemande);
		setDateDebut(dateDebut);
		setDebutAbsence(debutAbsence);
		setDateFin(dateFin);
		setFinAbsence(finAbsence);
		setCommentaire(commentaire);
		setEtatDemande(etatDemande);
	}

	// public DemandeConge(String id, String idTypeConge, String idUtilisateur, Date dateDemande, Date dateDebut, Integer debutAbsence, Date dateFin, Integer finAbsence, String commentaire, Integer etatDemande, String idValidateur){
	// 	setId(id);
	// 	setIdTypeConge(idTypeConge);
	// 	setIdUtilisateur(idUtilisateur);
	// 	setDateDemande(dateDemande);
	// 	setDateDebut(dateDebut);
	// 	setDebutAbsence(debutAbsence);
	// 	setDateFin(dateFin);
	// 	setFinAbsence(finAbsence);
	// 	setCommentaire(commentaire);
	// 	setEtatDemande(etatDemande);
	// }

	// public DemandeConge(String id, Integer etatDemande){
	// 	setId(id);
	// 	setEtatDemande(etatDemande);
	// }
}