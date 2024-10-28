package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import java.util.List;
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
	@Column(name = "est_annuler")
	Boolean estAnnuler;
	@Column(name = "motif_annulation")
	String motifAnnulation;
	@JsonIgnore
	@OneToMany(mappedBy = "demande_conge", cascade = CascadeType.ALL)
	List<Notification> notifications;
	@ManyToOne
	@JoinColumn(name = "id_type_conge", insertable = false, updatable = false)
	Type type_conge;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "fin_absence", insertable = false, updatable = false)
	Type type_absence;
	@ManyToOne
	@JoinColumn(name = "debut_absence", insertable = false, updatable = false)
	Type type_absence;
	@ManyToOne
	@JoinColumn(name = "etat_demande", insertable = false, updatable = false)
	Etat etat_demande;
	@ManyToOne
	@JoinColumn(name = "id_validateur", insertable = false, updatable = false)
	Utilisateur utilisateur;

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
	public Boolean getEstAnnuler(){
		return this.estAnnuler;
	}
	public void setEstAnnuler(Boolean estAnnuler){
		this.estAnnuler = estAnnuler;
	}
	public String getMotifAnnulation(){
		return this.motifAnnulation;
	}
	public void setMotifAnnulation(String motifAnnulation){
		this.motifAnnulation = motifAnnulation;
	}
	public List<Notification> getNotifications(){
		return this.notifications;
	}
	public void setNotifications(List<Notification> notifications){
		this.notifications = notifications;
	}
	public Type getType_conge(){
		return this.type_conge;
	}
	public void setType_conge(Type type_conge){
		this.type_conge = type_conge;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public Type getType_absence(){
		return this.type_absence;
	}
	public void setType_absence(Type type_absence){
		this.type_absence = type_absence;
	}
	public Type getType_absence(){
		return this.type_absence;
	}
	public void setType_absence(Type type_absence){
		this.type_absence = type_absence;
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

    //CONSTRUCTORS

 	public DemandeConge(){}
	public DemandeConge(String id, String idTypeConge, String idUtilisateur, Date dateDemande, Date dateDebut, Integer debutAbsence, Date dateFin, Integer finAbsence, String commentaire, Integer etatDemande, String idValidateur, Boolean estAnnuler, String motifAnnulation, List<Notification> notifications, Type type_conge, Utilisateur utilisateur, Type type_absence, Type type_absence, Etat etat_demande, Utilisateur utilisateur){
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
		setIdValidateur(idValidateur);
		setEstAnnuler(estAnnuler);
		setMotifAnnulation(motifAnnulation);
		setNotifications(notifications);
		setType_conge(type_conge);
		setUtilisateur(utilisateur);
		setType_absence(type_absence);
		setType_absence(type_absence);
		setEtat_demande(etat_demande);
		setUtilisateur(utilisateur);
	}

}
