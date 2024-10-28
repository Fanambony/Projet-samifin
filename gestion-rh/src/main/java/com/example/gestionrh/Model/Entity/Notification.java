package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "message")
	String message;
	@Column(name = "date_creation")
	Date dateCreation;
	@Column(name = "type_notification")
	String typeNotification;
	@Column(name = "expediteur")
	String expediteur;
	@Column(name = "id_demande")
	String idDemande;
	@JsonIgnore
	@OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
	List<NotificationDestinataire> notificationDestinataires;
	@ManyToOne
	@JoinColumn(name = "expediteur", insertable = false, updatable = false)
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
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public Date getDateCreation(){
		return this.dateCreation;
	}
	public void setDateCreation(Date dateCreation){
		this.dateCreation = dateCreation;
	}
	public String getTypeNotification(){
		return this.typeNotification;
	}
	public void setTypeNotification(String typeNotification){
		this.typeNotification = typeNotification;
	}
	public String getExpediteur(){
		return this.expediteur;
	}
	public void setExpediteur(String expediteur){
		this.expediteur = expediteur;
	}
	public String getIdDemande(){
		return this.idDemande;
	}
	public void setIdDemande(String idDemande){
		this.idDemande = idDemande;
	}
	public List<NotificationDestinataire> getNotificationDestinataires(){
		return this.notificationDestinataires;
	}
	public void setNotificationDestinataires(List<NotificationDestinataire> notificationDestinataires){
		this.notificationDestinataires = notificationDestinataires;
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

 	public Notification(){}
	public Notification(String id, String message, Date dateCreation, String typeNotification, String expediteur, String idDemande, List<NotificationDestinataire> notificationDestinataires, Utilisateur utilisateur, DemandeConge demande_conge){
		setId(id);
		setMessage(message);
		setDateCreation(dateCreation);
		setTypeNotification(typeNotification);
		setExpediteur(expediteur);
		setIdDemande(idDemande);
		setNotificationDestinataires(notificationDestinataires);
		setUtilisateur(utilisateur);
		setDemande_conge(demande_conge);
	}
	public Notification(String message, Date dateCreation, String typeNotification, String expediteur, String idDemande){
		setMessage(message);
		setDateCreation(dateCreation);
		setTypeNotification(typeNotification);
		setExpediteur(expediteur);
		setIdDemande(idDemande);
	}

}
