package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "v_etat_demande")
public class VEtatDemande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_demande_conge")
	String idDemandeConge;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "id_direction")
	String idDirection;
	@Column(name = "id_type_utilisateur")
	Integer idTypeUtilisateur;
	@Column(name = "id_etat_demande")
	Integer idEtatDemande;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "etat_utilisateur")
	Integer etatUtilisateur;
	@Column(name = "nom_utilisateur")
	String nomUtilisateur;
	@Column(name = "prenom_utilisateur")
	String prenomUtilisateur;
	@Column(name = "type_utilisateur")
	String typeUtilisateur;
	@Column(name = "type_conge")
	String typeConge;
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
	@Column(name = "etat_demande")
	String etatDemande;
	@Column(name = "commentaire")
	String commentaire;
	@Column(name = "nombre_jours_conge")
	Double nombreJoursConge;

	public String getIdDemandeConge(){
		return this.idDemandeConge;
	}
	public void setIdDemandeConge(String idDemandeConge){
		this.idDemandeConge = idDemandeConge;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public String getIdDirection(){
		return this.idDirection;
	}
	public void setIdDirection(String idDirection){
		this.idDirection = idDirection;
	}
	public Integer getIdTypeUtilisateur(){
		return this.idTypeUtilisateur;
	}
	public void setIdTypeUtilisateur(Integer idTypeUtilisateur){
		this.idTypeUtilisateur = idTypeUtilisateur;
	}
	public Integer getIdEtatDemande(){
		return this.idEtatDemande;
	}
	public void setIdEtatDemande(Integer idEtatDemande){
		this.idEtatDemande = idEtatDemande;
	}
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
	}
	public Integer getEtatUtilisateur(){
		return this.etatUtilisateur;
	}
	public void setEtatUtilisateur(Integer etatUtilisateur){
		this.etatUtilisateur = etatUtilisateur;
	}
	public String getNomUtilisateur(){
		return this.nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur){
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPrenomUtilisateur(){
		return this.prenomUtilisateur;
	}
	public void setPrenomUtilisateur(String prenomUtilisateur){
		this.prenomUtilisateur = prenomUtilisateur;
	}
	public String getTypeUtilisateur(){
		return this.typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur){
		this.typeUtilisateur = typeUtilisateur;
	}
	public String getTypeConge(){
		return this.typeConge;
	}
	public void setTypeConge(String typeConge){
		this.typeConge = typeConge;
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
	public String getEtatDemande(){
		return this.etatDemande;
	}
	public void setEtatDemande(String etatDemande){
		this.etatDemande = etatDemande;
	}
	public String getCommentaire(){
		return this.commentaire;
	}
	public void setCommentaire(String commentaire){
		this.commentaire = commentaire;
	}
	public Double getNombreJoursConge(){
		return this.nombreJoursConge;
	}
	public void setNombreJoursConge(Double nombreJoursConge){
		this.nombreJoursConge = nombreJoursConge;
	}

 	public VEtatDemande(){}
	public VEtatDemande(String idDemandeConge, String idUtilisateur, String idDirection, Integer idTypeUtilisateur, Integer idEtatDemande, String idTypeConge, Integer etatUtilisateur, String nomUtilisateur, String prenomUtilisateur, String typeUtilisateur, String typeConge, Date dateDemande, Date dateDebut, String debutAbsence, Date dateFin, String finAbsence, String etatDemande, String commentaire, Double nombreJoursConge){
		setIdDemandeConge(idDemandeConge);
		setIdUtilisateur(idUtilisateur);
		setIdDirection(idDirection);
		setIdTypeUtilisateur(idTypeUtilisateur);
		setIdEtatDemande(idEtatDemande);
		setIdTypeConge(idTypeConge);
		setEtatUtilisateur(etatUtilisateur);
		setNomUtilisateur(nomUtilisateur);
		setPrenomUtilisateur(prenomUtilisateur);
		setTypeUtilisateur(typeUtilisateur);
		setTypeConge(typeConge);
		setDateDemande(dateDemande);
		setDateDebut(dateDebut);
		setDebutAbsence(debutAbsence);
		setDateFin(dateFin);
		setFinAbsence(finAbsence);
		setEtatDemande(etatDemande);
		setCommentaire(commentaire);
		setNombreJoursConge(nombreJoursConge);
	}
}